package ryan.myproject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ryan.myproject.entity.Member;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcMemberRepository implements MemberRepository{



    private final JdbcTemplate template;
    private final RowMapper<Member> memberRowMapper = (resultSet, rowNum) -> {
        Member member = new Member();
        member.setMemberId(resultSet.getLong("member_id"));
        member.setMemberName(resultSet.getString("member_name"));
        member.setPassword(resultSet.getString("password"));
        return member;
    };

    @Autowired
    public JdbcMemberRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member findMemberById(Long memberId) {
        String sql = "select * from member where member_id = ?";
        return template.queryForObject(
                sql,
                memberRowMapper,
                memberId);
    }

    @Override
    public Member findMemberByName(String memberName) {
        String sql = "select * from member where member_name = ?";
        return template.queryForObject(
                sql,
                memberRowMapper,
                memberName);
    }

    @Override
    public Member findMemberByAll(String memberName, String password) {
        String sql = "select * from member where member_name = ? and password = ?";
        return template.queryForObject(sql, memberRowMapper, memberName, password);
    }

    @Override
    public Member saveMember(String memberName, String password) {
        String sql = "insert into member (member_name, password) values (?,?)";
        template.update(sql, memberName, password);
        return findMemberByAll(memberName, password);
    }

    @Override
    public void deleteMember(String memberName) {
        String sql = "delete from member where member_name = ?";
        template.update(sql, memberName);
    }

    @Override
    public List<Member> findAllMember() {
        String sql = "select * from member";
        List<Member> members = template.query(sql, memberRowMapper);
        return members;
    }
    @Override
    public boolean checkExistByAll(String memberName, String password) {
        String sql = "select count(*) from member where member_name = ? and password = ?";
        Integer count = template.queryForObject(sql, Integer.class, memberName, password);
        if(count >0) return true;
        else return false;
    }

    @Override
    public boolean checkExistByName(String memberName) {
        String sql = "select count(*) from member where member_name = ?";
        int count = template.queryForObject(sql, Integer.class, memberName);
        if(count >0) return true;
        else return false;
    }
}
