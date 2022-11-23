package com.example.persist.entity;


import java.util.Objects;

public class MemberEntity {
    private Long id;
    private String email;
    private String phone;
    private String identifierKor;
    private String age;
    private String password;

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
