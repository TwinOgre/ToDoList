package org.example.toDoContents;

public class ToDoContentsController {
    ToDoContentsService toDoContentsService;

    public ToDoContentsController(){
        toDoContentsService = new ToDoContentsService();
    }
    public void createContent(int listId,String content) {
        toDoContentsService.createContent(listId, content);
    }
}
