package com.keyin.golfclubapi.controller;

import com.keyin.golfclubapi.entity.Member;
import com.keyin.golfclubapi.entity.Tournament;
import com.keyin.golfclubapi.repository.MemberRepository;
import com.keyin.golfclubapi.repository.TournamentRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentRepository tournamentRepository;
    private final MemberRepository memberRepository;

    public TournamentController(TournamentRepository tournamentRepository, MemberRepository memberRepository) {
        this.tournamentRepository = tournamentRepository;
        this.memberRepository = memberRepository;
    }

    // ✅ Get all tournaments
    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    // ✅ Add new tournament
    @PostMapping
    public Tournament addTournament(@RequestBody Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    // ✅ Search tournaments by location
    @GetMapping("/search/byLocation")
    public List<Tournament> searchByLocation(@RequestParam String location) {
        return tournamentRepository.findByLocation(location);
    }

    // ✅ Search tournaments by start date
    @GetMapping("/search/byStartDate")
    public List<Tournament> searchByStartDate(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return tournamentRepository.findByStartDate(localDate);
    }

    // ✅ Get tournament + members by ID
    @GetMapping("/{id}/members")
    public List<Member> getTournamentMembers(@PathVariable Long id) {
        Tournament tournament = tournamentRepository.findTournamentWithMembers(id);
        return tournament != null ? List.copyOf(tournament.getMembers()) : List.of();
    }

    // ✅ Add a member to a tournament
    @PostMapping("/{tournamentId}/addMember/{memberId}")
    public Tournament addMemberToTournament(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        Optional<Tournament> tournamentOpt = tournamentRepository.findById(tournamentId);
        Optional<Member> memberOpt = memberRepository.findById(memberId);

        if (tournamentOpt.isPresent() && memberOpt.isPresent()) {
            Tournament tournament = tournamentOpt.get();
            tournament.getMembers().add(memberOpt.get());
            return tournamentRepository.save(tournament);
        } else {
            throw new RuntimeException("Tournament or Member not found!");
        }
    }
}
