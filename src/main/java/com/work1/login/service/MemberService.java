package com.work1.login.service;

import com.work1.login.dto.MemberDTO;
import com.work1.login.entity.MemberEntity;
import com.work1.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        //repository의 save 메소드 호출(조건, Entity 객체 넘겨줘야함)
        //1. dto -> Entity 변환
        //2. save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
        1.회원이 입력한 아이디로 DB에서 조회
        2.DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호 맞는지 확인
         */
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());

        if (byMemberId.isPresent()) {
            //해당 이메일을 가진 회원 정보가 있다
            MemberEntity memberEntity = byMemberId.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                //비밀번호 일치 -> dto 리턴


                memberDTO.setMemberStatus(true);
                memberRepository.setStatusTrue(memberEntity.getMemberId());
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                //비밀번호 불일치
                return null;
            }
        } else {
            //조회 결과 없음
            return null;
        }
    }

    public void logout(MemberDTO memberDTO){
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());
        if (byMemberId.isPresent()){
            MemberEntity memberEntity = byMemberId.get();
            memberRepository.setStatusFalse(memberEntity.getMemberId());
            memberRepository.save(memberEntity); // 변경된 상태를 저장
        }

    }

}




