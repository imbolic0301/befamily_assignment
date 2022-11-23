package com.example.service;

import com.example.api.dto.MemberDto;
import com.example.persist.entity.MemberEntity;
import com.example.persist.repo.MemberJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepo memberRepo;

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void join(MemberDto.Request.Join request) throws Exception {
        MemberEntity newMember = from(request);

        if(memberRepo.findByEmail(newMember.email()) != null) {
            throw new Exception("duplicated email");
        } else if(memberRepo.findByPhone(newMember.phone()) != null) {
            throw new Exception("duplicated phone");
        } else {
            memberRepo.save(newMember);
        }
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void changePassword(MemberDto.Request.PasswordChange request) {

    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void loginByEmail(String email, String password) {

    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void loginByPhone(String phone, String password) {

    }

    private MemberEntity from(MemberDto.Request.Join request) {
        return MemberEntity.builder()
                .phone(request.getPhone())
                .email(request.getEmail())
                .identifierKor(request.getIdentifierKor())
                .age(request.getAge())
                .password(request.getPassword())
                .build();
    }

    public MemberEntity viewInfo(Long id) {
        return null;
    }

}
