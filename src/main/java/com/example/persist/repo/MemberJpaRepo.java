package com.example.persist.repo;

import com.example.persist.entity.MemberEntity;

public interface MemberJpaRepo {
    void join(MemberEntity member);
    void changePassword(MemberEntity member);
}
