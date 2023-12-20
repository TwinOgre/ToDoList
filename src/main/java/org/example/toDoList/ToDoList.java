package org.example.toDoList;

import lombok.Getter;
import lombok.Setter;
import org.example.Container;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ToDoList {
    private int id;
    private int memberId;
    private String toDoTitle;
    private String toDoExplain;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String userId;
    private boolean executionStatus;

    ToDoList(Map<String, Object> row) {

        this.id = (int) row.get("id");
        this.memberId = (int) row.get("memberId");
        this.toDoTitle = (String) row.get("toDoTitle");
        this.toDoExplain = (String) row.get("toDoExplain");
        this.regDate =(LocalDateTime) row.get("regDate");
        this.updateDate =(LocalDateTime) row.get("updateDate");
        this.userId = (String)row.get("userId");
        this.executionStatus = (boolean) row.get("executionStatus");
    }
}
