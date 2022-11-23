package com.example.api.controller;

import com.example.api.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @PostMapping
    public ResponseEntity<?> join(@RequestBody MemberDto.Request.Join request) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto.Request.Login request) {
        return null;
    }

    @PatchMapping("/password")
    public ResponseEntity<?> login(@RequestBody MemberDto.Request.PasswordChange request) {
        return null;
    }

}
