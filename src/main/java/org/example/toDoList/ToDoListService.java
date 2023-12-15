package org.example.toDoList;

import org.example.member.Member;

import java.util.List;

public class ToDoListService {
    ToDoListRepository toDoListRepository;

    public ToDoListService() {
        toDoListRepository = new ToDoListRepository();
    }

    public void create(String title, String briefDescription) {
        toDoListRepository.create(title, briefDescription);
    }

    public ToDoList memberFindByTitle(String title) {
        return toDoListRepository.toDoListFindByTitle(title);
    }

    public List<ToDoList> list() {
        return toDoListRepository.list();
    }
}

