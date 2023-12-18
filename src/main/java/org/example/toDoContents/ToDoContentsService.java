package org.example.toDoContents;

import java.util.List;

public class ToDoContentsService {
    ToDoContentsRepository toDoContentsRepository;
    public ToDoContentsService() {
        toDoContentsRepository = new ToDoContentsRepository();
    }
    public void createContent(int listId,String content) {
        toDoContentsRepository.createContent(listId, content);
    }

    public List<ToDoContents> listContent(int id) {
        return toDoContentsRepository.listContent(id);
    }

    public ToDoContents findById(int id) {
        return toDoContentsRepository.findById(id);
    }
    public ToDoContents findByModifyId(int listId, int modifyId){
        return toDoContentsRepository.findByModifyId(listId,modifyId);
    }

    public void toDoContentsModify(int modifyContentId, String content) {
        toDoContentsRepository.toDoContentsModify(modifyContentId, content);
    }
}
