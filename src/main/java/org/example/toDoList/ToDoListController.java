package org.example.toDoList;

import org.example.Container;
import org.example.toDoContents.ToDoContentsController;

import java.util.ArrayList;
import java.util.List;

public class ToDoListController {
    ToDoListService toDoListService;
    ToDoContentsController toDoContentsController;

    public ToDoListController() {
        toDoListService = new ToDoListService();
        toDoContentsController = new ToDoContentsController();
    }

    public void create() {
        System.out.print("제목: ");
        String title = Container.getScanner().nextLine();
        System.out.print("간략설명: ");
        String briefDescription = Container.getScanner().nextLine();

        toDoListService.create(title, briefDescription);
        // 지금 만든 글 호출
        ToDoList toDoList = toDoListService.memberFindByTitle(title);

        System.out.print("상세 항목을 작성하시겠습니까?");
        String yesOrNo = Container.getScanner().nextLine().trim();
        if (yesOrNo.equals("예")) {
            System.out.println("세부항목의 작성이 끝나면 \"끝\"이라고 입력해주세요.");
            while (true) {
                System.out.print("세부항목: ");
                String content = Container.getScanner().nextLine();
                int listId = toDoList.getId();
                if (content.equals("끝")) {
                    break;
                }
                toDoContentsController.createContent(listId, content);
                System.out.println("세부항목이 등록 되었습니다.");
            }
            System.out.println(title + "이(가) 등록 되었습니다.");
        } else if (yesOrNo.equals("아니오")) {
            System.out.println(title + "이(가) 등록 되었습니다.");
        }

    }

    public void list() {
        List<ToDoList> toDoListList = toDoListService.list();
        System.out.println("id / 제목 / 간략설명 / 등록일 / 수정일 / 실행여부");
        System.out.println("- 상세항목");
        System.out.println("==================================================");
        for(int i = 0;i <  toDoListList.size(); i++){
            ToDoList tDL = toDoListList.get(i);
            if(tDL.isExecutionStatus()==true){
                System.out.printf("%d  %s  %s  %s  %s  %s\n",tDL.getId(),tDL.getToDoTitle(),tDL.getToDoExplain(),tDL.getRegDate(),tDL.getUpdateDate(),"[○]" );
            } else if (tDL.isExecutionStatus() == false) {
                System.out.printf("%d  %s  %s  %s  %s  %s\n",tDL.getId(),tDL.getToDoTitle(),tDL.getToDoExplain(),tDL.getRegDate(),tDL.getUpdateDate(),"[●]" );
            }

        }

    }
}
