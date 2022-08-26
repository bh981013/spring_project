package ryan.myproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ryan.myproject.entity.Member;
import ryan.myproject.repository.MemberRepository;
import ryan.myproject.service.MemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final MemberRepository memberRepository;

    @GetMapping("/home/{memberId}")
    public String home(@PathVariable Long memberId, Model model){
        log.info("go home");
        model.addAttribute("memberId", memberId);
        return "home";
    }

}
