package com.keyin.golfclubapi.controller;

import com.keyin.golfclubapi.entity.Member;
import com.keyin.golfclubapi.repository.MemberRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // ✅ Get all members
    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // ✅ Add new member
    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    // ✅ Search by name
    @GetMapping("/search/byName")
    public List<Member> searchByName(@RequestParam String name) {
        return memberRepository.findByName(name);
    }

    // ✅ Search by email
    @GetMapping("/search/byEmail")
    public List<Member> searchByEmail(@RequestParam String email) {
        return memberRepository.findByEmail(email);
    }

    // ✅ Search by phone number
    @GetMapping("/search/byPhone")
    public List<Member> searchByPhone(@RequestParam String phone) {
        return memberRepository.findByPhoneNumber(phone);
    }

    // ✅ Search by start date of membership
    @GetMapping("/search/byStartDate")
    public List<Member> searchByStartDate(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return memberRepository.findByStartDate(localDate);
    }
}
