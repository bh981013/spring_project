package ryan.myproject.entity;


import lombok.Data;

@Data
public class Member {
    Long memberId;
    String memberName;
    String password;

    public Member(){

    }

    public Member(Long memberId, String memberName, String password) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
    }
}
