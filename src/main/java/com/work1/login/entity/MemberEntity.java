package com.work1.login.entity;

import com.work1.login.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user")
public class MemberEntity {

    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name="idx")
    private Long idx;

    @Column(name="user_id",unique = true)
    private String memberId;

    @Column(name="user_email",unique = true)
    private String memberEmail;

    @Column(name="user_pw")
    private String memberPassword;

    @Column(name="status")
   public boolean memberStatus;


    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setIdx(memberDTO.getIdx());
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberStatus(memberDTO.isMemberStatus());
        return memberEntity;
    }


}
