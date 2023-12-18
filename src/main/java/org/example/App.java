package org.example;

import org.example.DB.DBConnection;
import org.example.member.MemberController;
import org.example.toDoList.ToDoListController;


public class App {
    ToDoListController toDoListController;
    MemberController memberController;
    public App() {
        DBConnection.DB_NAME = "projectToDo";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";
        Container.getDbConnection().connect();

        toDoListController = new ToDoListController();
        memberController = new MemberController();
    }

    public void run() {
        System.out.println("=== ToDoList 실행 ===");
        while(true){
            System.out.print("명령) ");
            String command = Container.getScanner().nextLine().trim();
            switch (command){
                case "종료":
                    return;
                case "회원가입":
                    memberController.join();
                    break;
                case "로그인":
                    memberController.login();
                    break;
                case "로그아웃":
                    memberController.logout();
                    break;
                case "등록":
                    toDoListController.create();
                    break;
                case "목록":
                    toDoListController.list();
                    break;
                case "수정":
                    toDoListController.modify();
                    break;
                case "삭제":
                    toDoListController.delete();
                    break;
                case "내목록":
                    toDoListController.myList();
                    break;
                case "할일목록":
                    toDoListController.toDoList();
                    break;
                case "완료목록":
                    toDoListController.completeList();
                    break;
                case "완료":
                    break;
            }
        }
    }

}
