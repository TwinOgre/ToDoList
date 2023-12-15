package org.example.member;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Member {
    private int id;
    private int successCount;
    private String userId;
    private String password;
    private String regDate;
    private String updateDate;

    public Member(Map<String, Object> row) {
        this.id = (int) row.get("id");
        this.userId = (String) row.get("userId");
        this.password = (String) row.get("password");
        this.regDate = row.get("regDate").toString();
        this.updateDate = row.get("updateDate").toString();
    }

}
