package ryan.myproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ryan.myproject.entity.Member;
import ryan.myproject.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository repository;

    public Member login(String memberName, String password){
        boolean exists = repository.checkExistByAll(memberName, password);
        if (exists){
            Member member = repository.findMemberByAll(memberName, password);
            return member;
        }
        else{
            return null;
        }
    }

    public Member signUp(String memberName, String password){
        boolean exists = repository.checkExistByName(memberName);
        if(exists){
            return null;
        }
        else{
            return repository.saveMember(memberName, password);
        }
    }



}