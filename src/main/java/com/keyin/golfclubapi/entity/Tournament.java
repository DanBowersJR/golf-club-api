package com.keyin.golfclubapi.entity;

import jakarta.persistence.*;   // ✅ use jakarta
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private double entryFee;
    private double cashPrizeAmount;

    // 🔄 Participating Members
    @ManyToMany
    @JoinTable(
            name = "tournament_members",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> members = new HashSet<>();

    // --- Constructors ---
    public Tournament() {
    }

    public Tournament(LocalDate startDate, LocalDate endDate, String location, double entryFee, double cashPrizeAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrizeAmount = cashPrizeAmount;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public double getEntryFee() {
        return entryFee;
    }
    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public double getCashPrizeAmount() {
        return cashPrizeAmount;
    }
    public void setCashPrizeAmount(double cashPrizeAmount) {
        this.cashPrizeAmount = cashPrizeAmount;
    }

    public Set<Member> getMembers() {
        return members;
    }
    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
