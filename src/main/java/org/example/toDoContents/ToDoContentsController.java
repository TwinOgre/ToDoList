package org.example.toDoContents;

import java.util.List;

public class ToDoContentsController {
    ToDoContentsService toDoContentsService;

    public ToDoContentsController(){
        toDoContentsService = new ToDoContentsService();
    }
    public void createContent(int listId,String content) {
        toDoContentsService.createContent(listId, content);
    }

    public List<ToDoContents> listContent(int id) {
        return toDoContentsService.listContent(id);
    }


}