package org.example.toDoContents;

public class ToDoContentsService {
    ToDoContentsRepository toDoContentsRepository;
    public ToDoContentsService() {
        toDoContentsRepository = new ToDoContentsRepository();
    }
    public void createContent(int listId,String content) {
        toDoContentsRepository.createContent(listId, content);
    }
}
