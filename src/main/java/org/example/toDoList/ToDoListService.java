package org.example.toDoList;

import java.util.List;

public class ToDoListService {
    ToDoListRepository toDoListRepository;

    public ToDoListService() {
        toDoListRepository = new ToDoListRepository();
    }

    public void create(String title, String briefDescription) {
        toDoListRepository.create(title, briefDescription);
    }

    public ToDoList memberFindByTitle(String title, String explain) {
        return toDoListRepository.toDoListFindByTitleAndExplain(title,explain);
    }

    public List<ToDoList> list() {
        return toDoListRepository.list();
    }

    public List<ToDoList> myList() {
        return toDoListRepository.myList();
    }

    public List<ToDoList> toDoList() {
        return toDoListRepository.toDoList();
    }

    public List<ToDoList> completeList() {
        return toDoListRepository.completeList();
    }
    public ToDoList toDoListFindById(int id){
        return toDoListRepository.toDoListFindById(id);
    }

    public void modify(int id, String title, String toDoExplain) {
        toDoListRepository.modify(id, title, toDoExplain);
    }

    public void delete(int deleteId) {
        toDoListRepository.delete(deleteId);
    }

    public void complete(int completeId) {
        toDoListRepository.complete(completeId);
    }
    public ToDoList toDoListFindByIdAndUserId(int memberId, int id){
        return toDoListRepository.toDoListFindByIdAndUserId(memberId,id);
    }
}

