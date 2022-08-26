package ryan.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ryan.myproject.entity.Member;
import ryan.myproject.service.MemberService;
import ryan.myproject.service.SearchService;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search/member/{memberId}")
    public String searchMemberForm(){
        return "search/search-member";
    }

    @PostMapping("/search/member/{memberId}")
    public String searchMember(@RequestParam String searchMemberName, Model model){
        Member searchMember = searchService.searchMember(searchMemberName);
        if (searchMember == null) return "/search/search-error";
        model.addAttribute("searchMember", searchMember);
        return "/search/search-member-result";
    }
}
