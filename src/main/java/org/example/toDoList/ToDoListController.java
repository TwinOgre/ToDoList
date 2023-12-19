package org.example.toDoList;

import org.example.Container;
import org.example.member.MemberController;
import org.example.toDoContents.ToDoContents;
import org.example.toDoContents.ToDoContentsController;

import java.util.ArrayList;
import java.util.List;

public class ToDoListController {
    ToDoListService toDoListService;
    ToDoContentsController toDoContentsController;
    MemberController memberController;

    public ToDoListController() {
        toDoListService = new ToDoListService();
        toDoContentsController = new ToDoContentsController();
        memberController = new MemberController();
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
        if (yesOrNo.equals("예") || yesOrNo.equals("네")) {
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
        } else if (yesOrNo.equals("아니오") || yesOrNo.equals("아니")) {
            System.out.println(title + "이(가) 등록 되었습니다.");
        }

    }

    public void list() {
        List<ToDoList> toDoListList = toDoListService.list();
        System.out.println("id / 제목 / 간략설명 / 등록일 / 수정일 / 실행여부");
        System.out.println("==================================================");
        for (int i = 0; i < toDoListList.size(); i++) {
            ToDoList tDL = toDoListList.get(i);
            if (tDL.isExecutionStatus() == true) {
                System.out.printf("%d  %s  %s  %s  %s  %s\n", tDL.getId(), tDL.getToDoTitle(), tDL.getToDoExplain(), tDL.getRegDate(), tDL.getUpdateDate(), "[❌]");
            } else if (tDL.isExecutionStatus() == false) {
                System.out.printf("%d  %s  %s  %s  %s  %s\n", tDL.getId(), tDL.getToDoTitle(), tDL.getToDoExplain(), tDL.getRegDate(), tDL.getUpdateDate(), "[⭕]");
            }
        }
    }

    public void modify() {
        myList();
        System.out.printf("수정할 ID번호를 입력해주세요)  ");
        int modifyId = Container.getScanner().nextInt();
        ToDoList toDoList = toDoListService.toDoListFindById(modifyId);
        if (toDoList == null) {
            System.out.println(modifyId + "번 글은 존재하지 않습니다.");
            Container.getScanner().nextLine();
            return;
        }
        Container.getScanner().nextLine();
        System.out.print("제목: ");
        String title = Container.getScanner().nextLine();
        System.out.print("간략설명: ");
        String toDoExplain = Container.getScanner().nextLine();
        toDoListService.modify(toDoList.getId(), title, toDoExplain);

        // 세부항목이 있는 지 확인
        ToDoContents toDoContents = toDoContentsController.findById(modifyId);
        if (toDoContents == null) {
            System.out.println(modifyId + "번 글이 수정되었습니다.");
            return;
        }
        System.out.print("상세 항목을 수정하시겠습니까?");
        String yesOrNo = Container.getScanner().nextLine().trim();

        if (yesOrNo.equals("예") || yesOrNo.equals("네")) {
            List<ToDoContents> toDoContentsList = toDoContentsController.listContent(modifyId);
            for (int i = 0; i < toDoContentsList.size(); i++) {
                int modifyContentId = toDoContentsList.get(i).getId();
                System.out.print("세부항목: ");
                String content = Container.getScanner().nextLine();
                toDoContentsController.toDoContentsModify(modifyContentId, content);
            }
            System.out.println(modifyId + "번 글이 수정되었습니다.");
        } else if (yesOrNo.equals("아니오") || yesOrNo.equals("아니")) {
            System.out.println(modifyId + "번 글이 수정되었습니다.");
        }
    }

    public void myList() {
        List<ToDoList> toDoListList = toDoListService.myList();
        System.out.println("id / 제목 / 간략설명 / 등록일 / 수정일 / 실행여부");
        System.out.println("- 상세항목");
        // 세부항목 추가 필요
        System.out.println("==================================================");
        for (int i = 0; i < toDoListList.size(); i++) {
            ToDoList tDL = toDoListList.get(i);
            if (tDL.isExecutionStatus() == true) {
                System.out.printf("%d  %s  %s  %s  %s  %s\n", tDL.getId(), tDL.getToDoTitle(), tDL.getToDoExplain(), tDL.getRegDate(), tDL.getUpdateDate(), "[❌]");
                toDoContentsController.printContents(tDL.getId());

            } else if (tDL.isExecutionStatus() == false) {
                System.out.printf("%d  %s  %s  %s  %s  %s\n", tDL.getId(), tDL.getToDoTitle(), tDL.getToDoExplain(), tDL.getRegDate(), tDL.getUpdateDate(), "[⭕]");
                toDoContentsController.printContents(tDL.getId());
            }
        }
    }

    public void toDoList() {
        List<ToDoList> toDoListList = toDoListService.toDoList();
        System.out.println("id / 제목 / 간략설명 / 등록일 / 수정일 / 실행여부");
        System.out.println("- 상세항목");
        System.out.println("==================================================");
        for (int i = 0; i < toDoListList.size(); i++) {
            ToDoList tDL = toDoListList.get(i);
            System.out.printf("%d  %s  %s  %s  %s  %s\n", tDL.getId(), tDL.getToDoTitle(), tDL.getToDoExplain(), tDL.getRegDate(), tDL.getUpdateDate(), "[❌]");
            toDoContentsController.printContents(tDL.getId());
        }
    }

    public void completeList() {
        List<ToDoList> toDoListList = toDoListService.completeList();
        System.out.println("id / 제목 / 간략설명 / 등록일 / 수정일 / 실행여부");
        System.out.println("- 상세항목");
        System.out.println("==================================================");
        for (int i = 0; i < toDoListList.size(); i++) {
            ToDoList tDL = toDoListList.get(i);
            System.out.printf("%d  %s  %s  %s  %s  %s\n", tDL.getId(), tDL.getToDoTitle(), tDL.getToDoExplain(), tDL.getRegDate(), tDL.getUpdateDate(), "[⭕]");
            toDoContentsController.printContents(tDL.getId());
        }
    }

    public void delete() {
        myList();
        System.out.printf("삭제할 ID번호를 입력해주세요)  ");
        int deleteId = Container.getScanner().nextInt();
        ToDoList toDoList = toDoListService.toDoListFindById(deleteId);
        if (toDoList == null) {
            System.out.println(deleteId + "번 글은 존재하지 않습니다.");
            Container.getScanner().nextLine();
            return;
        }
        toDoListService.delete(deleteId);
        System.out.println(deleteId + "번 글이 삭제되었습니다.");
        Container.getScanner().nextLine();
    }

    public void complete() {
        toDoList();
        System.out.printf("완료할 ID번호를 입력해주세요)  ");
        int completeId = Container.getScanner().nextInt();
        ToDoList toDoList = toDoListService.toDoListFindById(completeId);
        if (toDoList == null) {
            System.out.println(completeId + "번 글은 존재하지 않습니다.");
            Container.getScanner().nextLine();
            return;
        }
        // 먼저 세부항목이 없는 경우 완료처리✅
        toDoListService.complete(completeId);
        // 완료횟수 가져오고 증가.✅
        System.out.println(completeId + "번 할일이 완료되었습니다. 할일을 "+ Container.getLoginedMember().getCompleteCount() + "번 완료했습니다. 축하합니다!");
        Container.getScanner().nextLine();
    }
}

