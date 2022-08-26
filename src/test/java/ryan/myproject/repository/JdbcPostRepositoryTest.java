package ryan.myproject.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ryan.myproject.entity.Member;
import ryan.myproject.entity.Post;
import ryan.myproject.service.MemberService;

import java.util.Random;

@SpringBootTest
class JdbcPostRepositoryTest {

    Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
    Random random = new Random();

    private final PostRepository postRepository;
    private final MemberService memberService;

    @Autowired
    public JdbcPostRepositoryTest(PostRepository postRepository, MemberService memberService) {
        this.postRepository = postRepository;
        this.memberService = memberService;
    }

//    @Test
//    void addPostTest() {
//        Member member = memberService.signUp("newMemberName", "123123");
//        Post post = postRepository.addPost(member.getMemberId(), "myPostTitle", "postBody123123");
//        log.info("{}", post);
//    }

    @Test
    void showPostsTest(){
        Member member = memberService.signUp("new" + random.nextInt(), "123123");
        log.info("{}",postRepository.getPosts(member.getMemberId()));


    }
}