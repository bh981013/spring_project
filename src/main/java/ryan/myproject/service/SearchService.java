package ryan.myproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ryan.myproject.entity.Member;
import ryan.myproject.repository.MemberRepository;
import ryan.myproject.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    public Member searchMember(String memberName){
        log.info("search memberName = {}", memberName);
        return memberRepository.checkExistByName(memberName)?
        memberRepository.findMemberByName(memberName) : null;
    }
}
