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
    public void printContents(int toDoListId){
        List<ToDoContents> toDoContentsList = this.listContent(toDoListId);
        for (int t = 0; t < toDoContentsList.size(); t++) {
            ToDoContents tDC = toDoContentsList.get(t);
            if (tDC.isExecutionStatus() == true) {
                System.out.printf("    %d. %s  %s\n",tDC.getResetByCreateId(), tDC.getContent(), "[❌]");
            } else if (tDC.isExecutionStatus() == false) {
                System.out.printf("    %d. %s  %s\n",tDC.getResetByCreateId(), tDC.getContent(), "[✅]");
            }
        }
    }

    public void completeContent(int listId, int resetByListId) {
        toDoContentsService.completeContent(listId, resetByListId);
    }
    public ToDoContents findByListIdAndResetId(int listId, int resetByListId){
        return toDoContentsService.findByListIdAndResetId(listId, resetByListId);
    }
}
