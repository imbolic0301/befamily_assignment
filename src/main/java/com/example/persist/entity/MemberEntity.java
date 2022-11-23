package com.example.persist.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "dtb_member")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString // TODO - 개발 완료시 삭제할 것
public class MemberEntity {

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
