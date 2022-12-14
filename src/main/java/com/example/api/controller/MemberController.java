package com.example.api.controller;

import com.example.api.dto.CommonDto;
import com.example.api.dto.MemberDto;
import com.example.persist.entity.MemberEntity;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> join(@RequestBody MemberDto.Request.Join request) throws Exception {
        String jwt = memberService.join(request);
        return ResponseEntity.ok(new CommonDto.JwtResponse(jwt));
    }

    @PostMapping("/login/email")
    public ResponseEntity<?> loginByEmail(@RequestBody MemberDto.Request.Login request) throws Exception {
        String jwt = memberService.loginByEmail(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new CommonDto.JwtResponse(jwt));
    }

    @PostMapping("/login/phone")
    public ResponseEntity<?> loginByPhone(@RequestBody MemberDto.Request.Login request) throws Exception {
        String jwt = memberService.loginByPhone(request.getPhone(), request.getPassword());
        return ResponseEntity.ok(new CommonDto.JwtResponse(jwt));
    }

    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(Long id, @RequestBody MemberDto.Request.PasswordChange request) throws Exception {
        String jwt = memberService.changePassword(id, request);
        return ResponseEntity.ok(new CommonDto.JwtResponse(jwt));
    }

    @GetMapping("/info")
    public ResponseEntity<?> viewInfo(Long id) throws Exception {
        MemberEntity member = memberService.memberFrom(id);
        return ResponseEntity.ok(new MemberDto.Response.Info(member));
    }

}
