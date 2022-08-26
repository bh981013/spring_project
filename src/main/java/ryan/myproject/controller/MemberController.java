package ryan.myproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ryan.myproject.entity.Member;
import ryan.myproject.service.MemberService;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    ObjectMapper objectMapper = new ObjectMapper();



    @GetMapping("/login")
    public String loginForm(){
        return "login/login-form";
    }

    @PostMapping("/login")
    public String tryLogin(String memberName, String password, RedirectAttributes ra, Model model){
        log.info("try login");
        Member member = memberService.login(memberName, password);
        if(member == null){
            return "login/login-error";
        }

        Map<String, Object> map = objectMapper.convertValue(member, Map.class);

        //ra.addAttribute에 Object를 value로 넣으면 동작하지 않음! 왜??
//        ra.addAttribute("memberName", member.getMemberName());
        ra.addAttribute("memberName", memberName);
        ra.addAttribute("password", password);
        ra.addAttribute("memberId", member.getMemberId());
        log.info("try login done");
        return "redirect:/login/{memberName}";
    }

    @GetMapping("/login/{memberName}")
    public String showSuccess(@PathVariable String memberName, @RequestParam String password, @RequestParam Long memberId, Model model){
        Member member = new Member(memberId, memberName, password);
        model.addAttribute("member", member);
        return "login/login-success";
    }

    @GetMapping("/sign-up")
    public String signUpForm(){
        return "login/sign-up";
    }

    @PostMapping("/sign-up")
    public String trySignUp(@RequestParam String memberName,@RequestParam String password){
        Member member = memberService.signUp(memberName, password);
        return member != null? "login/sign-up-success": "login/sign-up-error";
    }


}
