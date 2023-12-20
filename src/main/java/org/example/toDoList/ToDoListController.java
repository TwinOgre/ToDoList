package org.example.toDoList;

import org.example.Container;
import org.example.member.MemberController;
import org.example.toDoContents.ToDoContents;
import org.example.toDoContents.ToDoContentsService;

import java.util.InputMismatchException;
import java.util.List;

public class ToDoListController {
    ToDoListService toDoListService;
    ToDoContentsService toDoContentsService;
    MemberController memberController;

    public ToDoListController() {
        toDoListService = new ToDoListService();
        // toDoContentsService로 바로 넘기기
        toDoContentsService = new ToDoContentsService();
        memberController = new MemberController();
    }

    public void create() {
        memberController.loginCheck();
        System.out.print("제목: ");
        String title = Container.getScanner().nextLine();
        System.out.print("간략설명: ");
        String briefDescription = Container.getScanner().nextLine();

        toDoListService.create(title, briefDescription);
        ToDoList toDoList = toDoListService.memberFindByTitle(title,briefDescription);

        System.out.print("상세 항목을 작성하시겠습니까?");
        String yesOrNo = Container.getScanner().nextLine().trim();
        if (yesOrNo.equals("예") || yesOrNo.equals("네")) {
            System.out.println("세부항목의 작성이 끝나면 \"끝\"이라고 입력해주세요.");

            while (true) {
                System.out.print("세부항목: ");
                String content = Container.getScanner().nextLine();
                int listId = toDoList.getId();
                if (content.equals("끝") || content.equals("끝 ") || content.equals(" 끝")) {
                    break;
                }
                toDoContentsService.createContent(listId, content);
                System.out.println("세부항목이 등록 되었습니다.");
            }
            System.out.println(title + "이(가) 등록 되었습니다.");
        } else if (yesOrNo.equals("아니오") || yesOrNo.equals("아니")) {
            System.out.println(title + "이(가) 등록 되었습니다.");
        }

    }

    public void list() {
        memberController.loginCheck();
        List<ToDoList> toDoListList = toDoListService.list();
        System.out.println("실행여부 / id / 제목 / 간략설명 / 등록일 / 수정일 / ");
        System.out.println("==================================================");
        for (int i = 0; i < toDoListList.size(); i++) {
            ToDoList tDL = toDoListList.get(i);
            if (tDL.isExecutionStatus() == true) {
                String regDate = Container.formatDate(tDL.getRegDate());
                String updateDate = Container.formatDate(tDL.getUpdateDate());
                System.out.printf("%s  %d  %s  %s  %s  %s\n", "[❌]", tDL.getId(), tDL.getToDoTitle(), tDL.getToDoExplain(), regDate, updateDate);
            } else if (tDL.isExecutionStatus() == false) {
                String regDate = Container.formatDate(tDL.getRegDate());
                String updateDate = Container.formatDate(tDL.getUpdateDate());
                System.out.printf("%s  %d  %s  %s  %s  %s\n", "[✅]", tDL.getId(), tDL.getToDoTitle(), tDL.getToDoExplain(), regDate, updateDate);
            }
        }
    }

    public void modify() {
        memberController.loginCheck();
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

        ToDoContents toDoContents = toDoContentsService.findById(modifyId);
        if (toDoContents == null) {
            System.out.println(modifyId + "번 글이 수정되었습니다.");
            return;
        }
        System.out.print("상세 항목을 수정하시겠습니까?");
        String yesOrNo = Container.getScanner().nextLine().trim();

        if (yesOrNo.equals("예") || yesOrNo.equals("네")) {
            List<ToDoContents> toDoContentsList = toDoContentsService.listContent(modifyId);
            for (int i = 0; i < toDoContentsList.size(); i++) {
                int modifyContentId = toDoContentsList.get(i).getId();
                System.out.print("세부항목: ");
                String content = Container.getScanner().nextLine();
                toDoContentsService.toDoContentsModify(modifyContentId, content);
            }
            System.out.println(modifyId + "번 글이 수정되었습니다.");
        } else if (yesOrNo.equals("아니오") || yesOrNo.equals("아니")) {
            System.out.println(modifyId + "번 글이 수정되었습니다.");
        }
    }

    public void myList() {
        memberController.loginCheck();
        List<ToDoList> toDoListList = toDoListService.myList();

        System.out.println("id / 제목 / 간략설명 / 등록일 / 수정일 / 실행여부");
        System.out.println("- 상세항목");
        System.out.println("==================================================");
        for (int i = 0; i < toDoListList.size(); i++) {
            ToDoList tDL = toDoListList.get(i);
            if (tDL.isExecutionStatus() == true) {
                this.inCompleteListPrinter(tDL);

            } else if (tDL.isExecutionStatus() == false) {
                this.completeListPrinter(tDL);
            }
        }
    }

    public void toDoList() {
        Container.setTryFlag(false);
        memberController.loginCheck();
        List<ToDoList> toDoListList = toDoListService.toDoList();
        if(toDoListList.size() == 0){
            System.out.println("내 할일목록이 없습니다.");
            return;
        }
        System.out.println("id / 제목 / 간략설명 / 등록일 / 수정일 / 실행여부");
        System.out.println("- 상세항목");
        System.out.println("==================================================");
        for (int i = 0; i < toDoListList.size(); i++) {
            ToDoList tDL = toDoListList.get(i);
            this.inCompleteListPrinter(tDL);
        }
        Container.setTryFlag(true);
    }

    public void completeList() {
        memberController.loginCheck();
        List<ToDoList> toDoListList = toDoListService.completeList();
        System.out.println("id / 제목 / 간략설명 / 등록일 / 수정일 / 실행여부");
        System.out.println("- 상세항목");
        System.out.println("==================================================");
        for (int i = 0; i < toDoListList.size(); i++) {
            ToDoList tDL = toDoListList.get(i);
            this.completeListPrinter(tDL);
        }
    }

    public void delete() {
        memberController.loginCheck();
        if(Container.isTryFlag() == false){
            return;
        }
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
        memberController.loginCheck();
        if(Container.isTryFlag() == false){
            return;
        }
        toDoList();
        if(Container.isTryFlag() == false){
            return;
        }

        System.out.printf("완료할 ID번호를 입력해주세요)  ");
        int completeId = 0;
        try {
            completeId = Container.getScanner().nextInt();
        } catch (InputMismatchException e) {
            System.out.println("정수만 입력해주세요.");
            Container.getScanner().nextLine();
            return;
        }

        ToDoList toDoList = toDoListService.toDoListFindByIdAndUserId(Container.getLoginedMember().getId(), completeId);
        if (toDoList == null) {
            System.out.println(completeId + "번 글은 존재하지 않습니다.");
            Container.getScanner().nextLine();
            return;
        } else if(toDoList.isExecutionStatus() == false){
            System.out.println(completeId + "번 글은 이미 완료된 상태입니다.");
            Container.getScanner().nextLine();
            return;
        }
        // 먼저 세부항목이 없는 경우 완료처리✅
        // 세부항목이 없는지 확인✅
        if (toDoContentsService.findById(completeId) == null) {
            toDoListService.complete(completeId);
            System.out.println(completeId + "번 할일이 완료되었습니다. 할일을 " + Container.getLoginedMember().getCompleteCount() + "번 완료했습니다. 축하합니다!");
            Container.getScanner().nextLine();
            return;
        }
        while (true) {
            // 상세항목이 전부 완료처리 되면 원글 완료처리후 종료(return)하기.
            List<ToDoContents> toDoContentsList = toDoContentsService.listContent(completeId);
            for (int i = 0; i < toDoContentsList.size(); i++) {
                if (toDoContentsList.get(i).isExecutionStatus() == true) {
                    break;
                } else if (toDoContentsList.get(toDoContentsList.size() - 1) == toDoContentsList.get(i) && toDoContentsList.get(i).isExecutionStatus() == false) {
                    toDoListService.complete(completeId);
                    System.out.println(completeId + "번 할일이 완료되었습니다. 할일을 " + Container.getLoginedMember().getCompleteCount() + "번 완료했습니다. 축하합니다!");

                    return;
                }

            }
            toDoContentsService.printContents(completeId);
            System.out.println("상세항목 완료를 중단하고 싶다면 \'-1\'을 입력해주세요.");
            System.out.print("완료할 상세 ID번호를 입력해주세요) ");

            int completeContentsId = Container.getScanner().nextInt();
            if (completeContentsId == -1) {
                System.out.println("완료처리를 중단합니다.");
                Container.getScanner().nextLine();
                return;
            }
            // 완료된 상세항목 접근시 다시 입력하게 하기.
            ToDoContents toDoContents = toDoContentsService.findByListIdAndResetId(completeId, completeContentsId);
            if (toDoContents.isExecutionStatus() == false) {
                System.out.println("❗" + completeContentsId + "번 상세항목은 이미 완료되었습니다.");
                Container.getScanner().nextLine();
                continue;
            }
            toDoContentsService.completeContent(completeId, completeContentsId);
            System.out.println(completeId + "번 글의 " + completeContentsId + "번 상세항목이 완료되었습니다.");
            Container.getScanner().nextLine();
        }
    }
    public void completeListPrinter(ToDoList tDL){
        String regDate = Container.formatDate(tDL.getRegDate());
        String updateDate = Container.formatDate(tDL.getUpdateDate());
        System.out.printf("%s  %d  %s  %s  %s  %s\n", "[✅]", tDL.getId(), tDL.getToDoTitle(), tDL.getToDoExplain(), regDate, updateDate);
        toDoContentsService.printContents(tDL.getId());
    }
    public void inCompleteListPrinter(ToDoList tDL){
        String regDate = Container.formatDate(tDL.getRegDate());
        String updateDate = Container.formatDate(tDL.getUpdateDate());
        System.out.printf("%s  %d  %s  %s  %s  %s\n", "[❌]", tDL.getId(), tDL.getToDoTitle(), tDL.getToDoExplain(), regDate, updateDate);
        toDoContentsService.printContents(tDL.getId());
    }
}

