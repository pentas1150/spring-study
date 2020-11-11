package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class MemberController {
    private final MemberService memberService;

    //생성자 주입(DI) -> 이게 제일 Best
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //세터 주입(DI)
    //    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
    @GetMapping("/members")
    public String showAllMemebers(Model model) {
        List<Member> members = memberService.findMembers();
        String[] membersNames = members.stream().map(Member::getName).toArray(String[]::new);

        model.addAttribute("membersNames", membersNames);

        return "members/showMembersNames";
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
}
