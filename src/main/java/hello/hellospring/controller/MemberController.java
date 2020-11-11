package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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


}
