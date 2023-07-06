package com.work1.login.repository;

import com.work1.login.entity.MemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

//어떤 Entity 받을 것이냐, id 의 타입
//반드시 Entity 객체로 넘어줘야함
public interface MemberRepository extends JpaRepository <MemberEntity, Long>{
    //아이디로 회원정보 조회
    //알아서 쿼리가 만들어짐
   // (select * from user where id=?)
    Optional<MemberEntity> findByMemberId(String Id);

    @Modifying
    @Transactional
    @Query("UPDATE MemberEntity m SET m.memberStatus = true WHERE m.memberId = :memberId")
    int setStatusTrue(@Param("memberId") String memberId);

    @Modifying
    @Transactional
    @Query("UPDATE MemberEntity m SET m.memberStatus = false WHERE m.memberId = :memberId")
    int setStatusFalse(@Param("memberId") String memberId);


}
