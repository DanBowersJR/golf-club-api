package com.keyin.golfclubapi.repository;

import com.keyin.golfclubapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // ✅ Search by name
    List<Member> findByName(String name);

    // ✅ Search by email
    List<Member> findByEmail(String email);

    // ✅ Search by phone number
    List<Member> findByPhoneNumber(String phoneNumber);

    // ✅ Search by membership start date
    List<Member> findByStartDate(LocalDate startDate);
}
