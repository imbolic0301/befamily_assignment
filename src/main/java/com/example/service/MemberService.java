package com.example.service;

import com.example.api.dto.MemberDto;
import com.example.persist.entity.MemberEntity;
import com.example.persist.repo.MemberJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private MemberJpaRepo memberRepo;


    public void join(MemberDto.Request.Join request) {

    }

    public void changePassword(MemberDto.Request.PasswordChange request) {

    }

    public void loginByEmail(String email, String password) {

    }

    public void loginByPhone(String phone, String password) {

    }

    public MemberEntity viewInfo(Long id) {
        return null;
    }

}
