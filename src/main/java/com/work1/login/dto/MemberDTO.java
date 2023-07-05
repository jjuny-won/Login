package com.work1.login.dto;

import com.work1.login.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동으로 만들어줌
@AllArgsConstructor
@ToString //toString 메서드 자동으로 만들어줌
public class MemberDTO {
    private Long idx;
    private String memberId;
    private String memberEmail;
    private String memberPassword;
    private boolean memberStatus;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setIdx(memberEntity.getIdx());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberStatus(memberEntity.isMemberStatus());
        return memberDTO;
    }
}
