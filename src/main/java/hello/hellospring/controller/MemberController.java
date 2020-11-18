package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    @GetMapping("/member/{id}")
    public String getMember(@PathVariable("id") Long memberId) {
        Optional<Member> member = memberService.findOne(memberId);
        member.ifPresent(m -> System.out.println("findOne : " + m.getName()));

        List<Member> members = memberService.findMembers();
        System.out.println("members size : " + members.size());
        members.forEach(m -> System.out.println("findAll : " + m.getName()));

        return "redirect:/";
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

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
//        Member[] membersArr = members.toArray(Member[]::new);
//        String[] membersNames = members.stream().map(Member::getName).toArray(String[]::new);
//        model.addAttribute("membersNames", membersNames);

        return "members/memberList";
    }

}
