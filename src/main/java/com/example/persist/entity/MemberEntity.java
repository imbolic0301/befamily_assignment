package com.example.persist.entity;


import com.example.constant.EnvConstants;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(
        name = "dtb_member",
        indexes = {
                @Index(name = "access_key_index", columnList = "access_key"),
                @Index(name = "email_uk", columnList = "email", unique = true),
                @Index(name = "phone_uk", columnList = "phone", unique = true)
        }
)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString // TODO - 개발 완료시 삭제할 것
public class MemberEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "identifier_kor", nullable = false)
    private String identifierKor;

    @Column(name = "age", nullable = false)
    private String age;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "access_key", nullable = false)
    private String accessKey;

    @Column(name = "expire_datetime")
    private LocalDateTime expireDateTime;

    @Column(name = "last_verified_datetime")
    private LocalDateTime lastVerifiedTime;

    @Builder
    public MemberEntity(String phone, String email, String identifierKor, String age, String password) {
        this.phone = phone;
        this.email = email;
        this.identifierKor = identifierKor;
        this.age = age;
        this.password = password;
    }

    public Long id() {
        return id;
    }

    public String email() {
        return email;
    }

    public String phone() {
        return phone;
    }

    public String identifierKor() {
        return identifierKor;
    }

    public String age() {
        return age;
    }

    public String password() {
        return password;
    }

    public String accessKey() {
        return accessKey;
    }

    public LocalDateTime expireDateTime() {
        return expireDateTime;
    }

    public LocalDateTime lastVerifiedTime() {
        return lastVerifiedTime;
    }

    public String changePassword(String oldPassword, String newPassword) throws Exception {
        if(!this.password().equals(oldPassword)) throw new Exception("invalid try");
        this.password = newPassword;
        return refreshAccessKey();
    }

    public String loginByEmail(String email, String password) throws Exception {
        if(this.email.equals(email) && this.password.equals(password)) {
            return refreshAccessKey();
        } else {
            throw new Exception("not valid login");
        }
    }

    public String loginByPhone(String phone, String password) throws Exception {
        if(this.phone.equals(phone) && this.password.equals(password)) {
            return refreshAccessKey();
        } else {
            throw new Exception("not valid login");
        }
    }

    public String createAccessKeyForNew() throws Exception {
        if(this.id != null) throw new Exception("not valid operation");
        return refreshAccessKey();
    }

    private String refreshAccessKey() throws Exception {
        this.accessKey = UUID.randomUUID().toString();
        this.lastVerifiedTime = LocalDateTime.now();
        this.expireDateTime = LocalDateTime.now().plusSeconds(EnvConstants.SESSION_LIVE_SECONDS);
        return accessKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberEntity)) return false;
        MemberEntity that = (MemberEntity) o;
        return id().equals(that.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id());
    }

}
