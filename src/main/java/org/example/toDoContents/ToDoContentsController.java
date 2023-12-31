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
    public void toDoContentsModify(int modifyId, int modifyContentId, String content){
         toDoContentsService.toDoContentsModify(modifyId, modifyContentId, content);
    }
    public void printContents(int toDoListId){
        toDoContentsService.printContents(toDoListId);
    }

    public void completeContent(int listId, int resetByListId) {
        toDoContentsService.completeContent(listId, resetByListId);
    }
    public ToDoContents findByListIdAndResetId(int listId, int resetByListId){
        return toDoContentsService.findByListIdAndResetId(listId, resetByListId);
    }
}
