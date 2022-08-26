package ryan.myproject.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ryan.myproject.entity.Member;

import java.util.Random;

@SpringBootTest
class JdbcMemberRepositoryTest {

    Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
    MemberRepository memberRepository;
    Random random = new Random();

    @Autowired
    public JdbcMemberRepositoryTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    @DisplayName("find member by name and password")
    void findMemberByAll() {
        Member member = saveRandomMember();
        Member findMember = memberRepository.findMemberByAll(member.getMemberName(), member.getPassword());
        Assertions.assertThat(member.getMemberName()).isEqualTo(findMember.getMemberName());
        Assertions.assertThat(member.getPassword()).isEqualTo(findMember.getPassword());
        log.info("memberName={}, memberPassword={}", findMember.getMemberName(), findMember.getPassword());
    }

    @Test
    @DisplayName("find all members")
    void findAllMember(){
        memberRepository.findAllMember().forEach(member -> {log.info("{}", member);});
    }

    @Test
    @DisplayName("check existing member existence by name and password")
    void checkExistByAll(){
        Member member = saveRandomMember();
        boolean exist = memberRepository.checkExistByAll(member.getMemberName(), member.getPassword());
        log.info("{}", exist);
        Assertions.assertThat(exist).isTrue();
    }

    @Test
    @DisplayName("check not existing member existence by name and password")
    void checkExistByAllNotExists(){
        boolean exist = memberRepository.checkExistByAll("nullName", "nullPW");
        log.info("{}", exist);
        Assertions.assertThat(exist).isFalse();
    }


    Member saveRandomMember(){
        String memberName = "myMember" + random.nextInt();
        String password = String.valueOf(random.nextInt());
        Member member = memberRepository.saveMember(memberName, password);
        return member;
    }
}