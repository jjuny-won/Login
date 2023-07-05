package com.work1.login.controller;

import com.work1.login.dto.MemberDTO;
import com.work1.login.entity.MemberEntity;
import com.work1.login.repository.MemberRepository;
import com.work1.login.service.MemberService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.synth.SynthMenuBarUI;
import java.util.Optional;

@Controller
@RequiredArgsConstructor    //생성자 주입 - 자동적으로 service 의 것들 사용할 수 있게됨
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    //memberDTO 에 담겨서 옴
    // 중간에 DTO 타입으로 전해주면
    // html name과 DTO 가 동일하면 각각 setter 동시에 해줌
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("memberDTO =" + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        //사용자가 입력한 값은 memberDTO 로 받음
        System.out.println("memberDTO =" + memberDTO);
        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null) {
            //로그인 성공
            if(memberDTO.isMemberStatus()) {
                System.out.println("이미 로그인했습니다");
                session.setAttribute("loginId", loginResult.getMemberId());
                System.out.println(session.getAttribute("loginId"));
                return "alert";
            }else{
                System.out.println("로그인 성공");
                session.setAttribute("loginId", loginResult.getMemberId());
                System.out.println(session.getAttribute("loginId"));
                return "main";
            }
        } else {
            return "login";
        }
    }


    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        String memberId = (String) session.getAttribute("loginId");
        System.out.println(memberId);
        if (memberId != null) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setMemberId(memberId);
            memberService.logout(memberDTO);
            session.invalidate();
        }
        return "index";
    }
}

