package org.example.member;

import org.example.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    public void join(String userId, String password) {
        String sql = String.format("INSERT INTO `member` SET userId = '%s', password = '%s', successCount = 0, regDate = now(), updateDate = now();", userId, password);
        Container.getDbConnection().insert(sql);
    }


    public void login(Member checkedMember) {
        Container.setLoginedMember(checkedMember);
    }

    public Member memberFindByUserId(String userId) {
        List<Map<String, Object>> rows = Container.getDbConnection().selectRows("select * from `member`");
        for (Map<String, Object> row : rows) {
            Member member = new Member(row);
            if (userId.equals(member.getUserId())) {
                return member;
            }
        }
        return null;
    }

    public void logout() {
        Container.setLoginedMember(null);
    }
}
