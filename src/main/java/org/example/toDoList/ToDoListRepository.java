package org.example.toDoList;

import org.example.Container;
import org.example.member.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToDoListRepository {
    public void create(String title, String briefDescription) {
        String sql = String.format("INSERT INTO toDoList SET memberId = %d, toDoTitle = '%s', toDoExplain = '%s', regDate = now(), updateDate = now(), executionStatus = TRUE;", Container.getLoginedMember().getId(),title,briefDescription);
        Container.getDbConnection().insert(sql);

    }
    public ToDoList toDoListFindByTitle(String title) {
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows("select * from `toDoList`");
        for (Map<String, Object> row : rows) {
            ToDoList toDoList = new ToDoList(row);
            if (title.equals(toDoList.getToDoTitle())) {
                return toDoList;
            }
        }
        return null;
    }

    public List<ToDoList> list() {
        List<ToDoList> toDoListList = new ArrayList<>();
        List<Map<String,Object>> rows = Container.getDbConnection().selectRows("select * from `toDoList`");
        for (Map<String, Object> row : rows) {
            ToDoList toDoList = new ToDoList(row);
            toDoListList.add(toDoList);
        }
        return toDoListList;
    }
}
