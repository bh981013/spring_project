package ryan.myproject.repository;

import ryan.myproject.entity.Member;

import java.util.List;

public interface MemberRepository {
    public Member findMemberById(Long memberId);

    public Member findMemberByName(String memberName);
    public Member findMemberByAll(String memberName, String password);
    public boolean checkExistByAll(String memberName, String password);
    public boolean checkExistByName(String memberName);
    public Member saveMember(String memberName, String password);
    public void deleteMember(String memberName);
    public List<Member> findAllMember();

}
