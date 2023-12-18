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


    public ToDoContents findById(int id) {
        return toDoContentsService.findById(id);
    }
    public ToDoContents findByModifyId(int listId, int modifyId){
        return toDoContentsService.findByModifyId(listId,modifyId);
    }
    public void toDoContentsModify(int modifyContentId, String content){
         toDoContentsService.toDoContentsModify(modifyContentId, content);
    }
}
