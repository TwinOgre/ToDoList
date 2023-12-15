package org.example.member;
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(){
        memberRepository = new MemberRepository();
    }
    public void join(String userId, String password) {
        memberRepository.join(userId,password);
    }


    public Member memberFindByUserId(String userId) {
        return memberRepository.memberFindByUserId(userId);
    }

    public void login(Member checkedMember) {
        memberRepository.login(checkedMember);
    }
}
