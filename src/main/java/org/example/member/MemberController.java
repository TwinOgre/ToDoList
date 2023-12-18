package org.example.member;

import org.example.Container;

public class MemberController {
    MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    public void join() {
        System.out.print("아이디: ");
        String userId = Container.getScanner().nextLine();
        while (true) {
            System.out.print("비밀번호: ");
            String password = Container.getScanner().nextLine();
            System.out.print("비밀번호 확인: ");
            String passwordConfirm = Container.getScanner().nextLine();

            if (password.equals(passwordConfirm)) {
                memberService.join(userId, password);
                System.out.println(userId + "님 가입을 축하드립니다.");
                break;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        }
    }

    public void login() {
        if (Container.getLoginedMember() != null) {
            System.out.println("현재 로그인 상태입니다.");
            return;
        }

        Member checkedMember = null;

        System.out.printf("아이디 : ");
        String userId = Container.getScanner().nextLine().trim();
        System.out.printf("비밀번호 : ");
        String password = Container.getScanner().nextLine().trim();

        checkedMember = memberService.memberFindByUserId(userId);


        if (checkedMember == null) {
            System.out.println("해당 회원이 존재하지 않습니다.");
            return;
        } else if (!checkedMember.getPassword().equals(password)) {
            System.out.println("비밀번호가 일치 하지 않습니다.");
            return;
        }

        this.memberService.login(checkedMember);

        System.out.println(checkedMember.getUserId() + "님 환영합니다.");
    }

    public void logout() {
        if (Container.getLoginedMember() == null) {
            System.out.println("로그인 중이 아닙니다.");
            return;
        }
        memberService.logout();
        System.out.println("로그아웃 되었습니다.");
    }
}
