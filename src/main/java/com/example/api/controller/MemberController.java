package com.example.api.controller;

import com.example.api.dto.CommonDto;
import com.example.api.dto.MemberDto;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> join(@RequestBody MemberDto.Request.Join request) throws Exception {
        memberService.join(request);
        return ResponseEntity.ok(CommonDto.successResponse.get());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto.Request.Login request) {
        return null;
    }

    @PatchMapping("/password/temp")
    public ResponseEntity<?> login(@RequestBody MemberDto.Request.TempPasswordChange request) throws Exception {
        memberService.changePassword(request);
        return ResponseEntity.ok(CommonDto.successResponse.get());
    }

}
