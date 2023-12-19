package org.example.toDoContents;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ToDoContents {
    private int id;
    private int memberId;
    private int listId;
    private int resetByCreateId;
    private String content;
    private String regDate;
    private String updateDate;
    private boolean executionStatus;

    ToDoContents(Map<String, Object> row) {
        this.id = (int) row.get("id");
        this.memberId = (int) row.get("memberId");
        this.content = (String) row.get("content");
        this.listId = (int) row.get("listId");
        this.resetByCreateId = (int) row.get("resetByCreateId");
        this.regDate = row.get("regDate").toString();
        this.updateDate = row.get("updateDate").toString();
        this.executionStatus = (boolean) row.get("executionStatus");
    }
}
