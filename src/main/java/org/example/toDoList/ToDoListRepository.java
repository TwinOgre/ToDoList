package org.example.toDoList;

import org.example.Container;
import org.example.member.Member;
import org.example.member.MemberController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToDoListRepository {
    MemberController memberController;

    public ToDoListRepository() {
        memberController = new MemberController();
    }

    public void create(String title, String briefDescription) {
        Container.setResetByCreateId(1);
        String sql = String.format("INSERT INTO toDoList SET memberId = %d, toDoTitle = '%s', toDoExplain = '%s', regDate = now(), updateDate = now(), executionStatus = TRUE;", Container.getLoginedMember().getId(), title, briefDescription);
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
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows("select * from `toDoList`");
        return findByRows(toDoListList, rows);
    }

    public List<ToDoList> myList() {
        List<ToDoList> toDoListMyList = new ArrayList<>();
        String sql = String.format("select * from `toDoList` WHERE memberId = %d", Container.getLoginedMember().getId());
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);

        return findByRows(toDoListMyList, rows);
    }

    public List<ToDoList> toDoList() {
        List<ToDoList> toDoListToDoList = new ArrayList<>();

        String sql = String.format("SELECT * FROM toDoList WHERE memberId = %d and executionStatus = TRUE", Container.getLoginedMember().getId());
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);

        return findByRows(toDoListToDoList, rows);
    }

    public List<ToDoList> findByRows(List<ToDoList> toDoListList, List<Map<String, Object>> rows) {
        for (Map<String, Object> row : rows) {
            ToDoList toDoList = new ToDoList(row);
            toDoListList.add(toDoList);
        }
        return toDoListList;
    }

    public List<ToDoList> completeList() {
        List<ToDoList> toDoListToDoList = new ArrayList<>();
        String sql = String.format("SELECT * FROM toDoList WHERE memberId = %d and executionStatus = FALSE", Container.getLoginedMember().getId());
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);

        return findByRows(toDoListToDoList, rows);
    }

    public ToDoList toDoListFindById(int id) {
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows("SELECT * FROM toDoList");
        for (Map<String, Object> row : rows) {
            ToDoList toDoList = new ToDoList(row);
            if (id == toDoList.getId()) {
                return toDoList;
            }
        }
        return null;
    }

    // 로그인한 유저의 ToDoList 찾기(고유ID와 userId둘다 받기)
    public ToDoList toDoListFindByIdAndUserId(int memberId, int id) {
        String sql = String.format("SELECT * FROM toDoList WHERE memberId = %d", memberId);
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            ToDoList toDoList = new ToDoList(row);
            if (id == toDoList.getId()) {
                return toDoList;
            }
        }
        return null;
    }

    public void modify(int id, String title, String toDoExplain) {
        String sql = String.format("UPDATE toDoList SET toDoTitle = '%s', toDoExplain = '%s' WHERE id = %d", title, toDoExplain, id);
        Container.getDbConnection().update(sql);
    }

    public void delete(int deleteId) {
        String sql = String.format("DELETE FROM toDoList WHERE id = %d", deleteId);
        Container.getDbConnection().delete(sql);
    }

    public void complete(int completeId) {
        Member member = memberController.memberFindById(Container.getLoginedMember().getId());
        int completeCount = member.getCompleteCount();
        completeCount++;
        String sql = String.format("UPDATE toDoList SET executionStatus = FALSE WHERE id = %d", completeId);
        Container.getDbConnection().update(sql);

        String sqlForCountPlus = String.format("UPDATE `member` SET completeCount = %d WHERE id = %d", completeCount, member.getId());
        Container.getDbConnection().update(sqlForCountPlus);
        Container.setLoginedMember(member);
    }
}