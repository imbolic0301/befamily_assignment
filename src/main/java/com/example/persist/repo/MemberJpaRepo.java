package com.example.persist.repo;

import com.example.persist.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberJpaRepo extends JpaRepository<MemberEntity, Long> {

    @Query("SELECT m FROM MemberEntity m WHERE m.email = :email")
    MemberEntity findByEmail(@Param("email") String email);

    @Query("SELECT m FROM MemberEntity m WHERE m.phone = :phone")
    MemberEntity findByPhone(@Param("phone") String phone);

}
