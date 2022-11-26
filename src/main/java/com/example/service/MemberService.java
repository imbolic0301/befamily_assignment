package com.example.service;

import com.example.api.dto.MemberDto;
import com.example.exception.GlobalException;
import com.example.persist.entity.MemberEntity;
import com.example.persist.repo.MemberJpaRepo;
import com.example.util.DecryptableEncryptor;
import com.example.util.JwtTokenProvider;
import com.example.util.OneWayEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepo memberRepo;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public MemberEntity memberFrom(Long id) throws Exception {
        return entityFrom(id);
    }

    @Transactional(readOnly = true)
    public boolean isValidAccessKey(String accessKey) throws Exception {
        MemberEntity exist = memberRepo.findByAccessKey(accessKey);
        if(exist == null || LocalDateTime.now().isAfter(exist.expireDateTime()))
            throw new GlobalException("not valid access key", HttpStatus.BAD_REQUEST);
        return true;
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String join(MemberDto.Request.Join request) throws Exception {
        MemberEntity newMember = from(request);
        String accessKey;
        if(memberRepo.findByEmail(newMember.email()) != null) {
            throw new GlobalException("duplicated email", HttpStatus.SERVICE_UNAVAILABLE);
        } else if(memberRepo.findByPhone(newMember.phone()) != null) {
            throw new GlobalException("duplicated phone", HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            accessKey = newMember.createAccessKeyForNew();
            memberRepo.save(newMember);
        }

        return jwtFromMember(newMember, accessKey);
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String changePassword(Long sessionId, MemberDto.Request.PasswordChange request) throws Exception {
        MemberEntity exist = entityFrom(sessionId);
        String accessKey = exist.changePassword(request.getOldPassword(), request.getNewPassword());
        memberRepo.save(exist);

        return jwtFromMember(exist, accessKey);
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String loginByEmail(String email, String password) throws Exception {
        String encrypt = DecryptableEncryptor.encrypt(email);
        MemberEntity exist = memberRepo.findByEmail(encrypt);
        if(exist == null) throw new GlobalException("not found member", HttpStatus.BAD_REQUEST);
        String accessKey = exist.loginByEmail(encrypt, password);
        memberRepo.save(exist);

        return jwtFromMember(exist, accessKey);
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String loginByPhone(String phone, String password) throws Exception {
        String encrypt = DecryptableEncryptor.encrypt(phone);
        MemberEntity exist = memberRepo.findByPhone(encrypt);
        if(exist == null) throw new GlobalException("not found member", HttpStatus.BAD_REQUEST);
        String accessKey = exist.loginByPhone(encrypt, password);
        memberRepo.save(exist);

        return jwtFromMember(exist, accessKey);
    }

    private MemberEntity entityFrom(Long id) throws Exception {
        Optional<MemberEntity> optionalEntity = memberRepo.findById(id);
        if(!optionalEntity.isPresent()) throw new GlobalException("not found member", HttpStatus.BAD_REQUEST);
        return optionalEntity.get();
    }

    private MemberEntity from(MemberDto.Request.Join request) throws Exception {
        return MemberEntity.builder()
                .phone(DecryptableEncryptor.encrypt(request.getPhone()))
                .email(DecryptableEncryptor.encrypt(request.getEmail()))
                .identifierKor(DecryptableEncryptor.encrypt(request.getIdentifierKor()))
                .age(DecryptableEncryptor.encrypt(request.getAge()))
                .password(OneWayEncryptor.hashFrom(request.getPassword()))
                .build();
    }

    private String jwtFromMember(MemberEntity member, String accessKey) throws Exception {
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("id", DecryptableEncryptor.encrypt(String.valueOf(member.id())));
        claimMap.put("email", member.email());
        claimMap.put("accessKey", accessKey);
        return jwtTokenProvider.createToken(claimMap);
    }

}
