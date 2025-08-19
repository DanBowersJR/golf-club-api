package com.keyin.golfclubapi.repository;

import com.keyin.golfclubapi.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    // ✅ Search tournaments by start date
    List<Tournament> findByStartDate(LocalDate startDate);

    // ✅ Search tournaments by location
    List<Tournament> findByLocation(String location);

    // ✅ Fetch a tournament and its members by tournament ID
    @Query("SELECT t FROM Tournament t LEFT JOIN FETCH t.members WHERE t.id = :tournamentId")
    Tournament findTournamentWithMembers(Long tournamentId);
}
