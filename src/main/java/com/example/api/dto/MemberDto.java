package com.example.api.dto;

import com.example.persist.entity.MemberEntity;
import lombok.Getter;

public class MemberDto {

    public static class Request {

        @Getter
        public static class Join {
            private String email;
            private String phone;
            private String identifierKor;
            private String age;
            private String password;
        }

        @Getter
        public static class Login {
            private String email;
            private String phone;
            private String password;
        }

        @Getter
        public static class TempPasswordChange {
            private Long id;
//            private String email;
            private String oldPassword;
            private String newPassword;
        }

    }

    public static class Response {
        @Getter
        public static class Info {

            public Info(MemberEntity entity) {
                this.email = entity.email();
                this.age = entity.age();
                this.phone = entity.phone();
            }

            private final String email;
            private final String phone;
            private final String age;
        }
    }

}
