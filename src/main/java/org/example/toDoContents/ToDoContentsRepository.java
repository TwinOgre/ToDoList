package org.example.toDoContents;

import org.example.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToDoContentsRepository {

    public void createContent(int listId,String content) {

        String sql = String.format("INSERT INTO toDoContents SET memberId = %d, listId = %d, " +
                " content = '%s', regDate = now(), updateDate = now(), executionStatus = TRUE;",
                Container.getLoginedMember().getId(), listId ,content);
        Container.getDbConnection().insert(sql);
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
//    public ToDoContents ToDoContentsFindById(int id) {
//        List<ToDoContents> ToDoContentsList = this.listContent();
//        for (int i = 0; i < ToDoContentsList.size(); i++) {
//            if (id == ToDoContentsList.get(i).getId()) {
//                return ToDoContentsList.get(i);
//            }
//        }
//        return null;
//    }
}
