package org.example.toDoContents;

import org.example.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToDoContentsRepository {

    public void createContent(int listId, String content) {
        int resetByCreateId = Container.getResetByCreateId();
        String sql = String.format("INSERT INTO toDoContents SET memberId = %d, listId = %d, " +
                        "resetByCreateId = %d, content = '%s',  regDate = now(), updateDate = now(), executionStatus = TRUE;",
                Container.getLoginedMember().getId(), listId, resetByCreateId ,content);
        Container.getDbConnection().insert(sql);
        resetByCreateId++;
        Container.setResetByCreateId(resetByCreateId);
    }

    public List<ToDoContents> listContent(int id) {
        List<ToDoContents> ToDoContentsList = new ArrayList<>();
        String sql = String.format("SELECT * FROM toDoContents WHERE listId = %d", id);
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);

        for (Map<String, Object> row : rows) {
            ToDoContents toDoContents = new ToDoContents(row);

            ToDoContentsList.add(toDoContents);
        }
        return ToDoContentsList;
    }

    public ToDoContents findById(int id) {
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows("SELECT * FROM toDoContents");
        for (Map<String, Object> row : rows) {
            ToDoContents toDoContents = new ToDoContents(row);
            if (id == toDoContents.getListId()) {
                return toDoContents;
            }
        }
        return null;
    }
    public ToDoContents findByListIdAndResetId(int listId, int resetByListId){
        String sql = String.format("SELECT * FROM toDoContents WHERE listId = %d",listId);
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            ToDoContents toDoContents = new ToDoContents(row);
            if (resetByListId == toDoContents.getResetByCreateId()) {
                return toDoContents;
            }
        }
        return null;
    }

    public ToDoContents findByModifyId(int listId, int modifyId) {
        String sql = String.format("SELECT * FROM toDoContents WHERE listId = %d", listId);
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            ToDoContents toDoContents = new ToDoContents(row);
            if (modifyId == toDoContents.getListId()) {
                return toDoContents;
            }
        }
        return null;

    }

    public void toDoContentsModify(int modifyContentId, String content) {
        String sql = String.format("UPDATE toDoContents SET content = '%s' WHERE id = %d", content, modifyContentId);
        Container.getDbConnection().update(sql);
    }

    public void completeContent(int listId , int resetByListId) {
        String sql = String.format("UPDATE toDoContents SET executionStatus = FALSE WHERE listId = %d AND resetByCreateId =%d;",listId,resetByListId);
        Container.getDbConnection().update(sql);
    }
}
