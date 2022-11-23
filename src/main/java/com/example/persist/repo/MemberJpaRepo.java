package com.example.persist.repo;

import com.example.persist.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberJpaRepo extends JpaRepository<MemberEntity, Long> {
    void changePassword(MemberEntity member);
}
