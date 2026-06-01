package com.example.demo;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    private String nationality;
    private String position;
    private int appearances;
    private int minPlayed;
    private int goals;
    private int assists;
    private int shots;
    private int shotsOnTarget;
    private int bigChancesMissed;
    private int touches;
    private int passesAttempted;
    private int passesCompleted;
    private double passCompPercentage;
    private int crosses;
    private int fThirdPasses;
    private int succfThirdPasses;
    private int carries;
    private int progCarries;
    private int carriesEWGoal;
    private int carriesEWAssist;
    private int carriesEWShot;
    private int carriesEWChance;
    private int possessionWon;
    private int dispossessed;
    private int interceptions;
    private int clearances;
    private int blocks;
    private int tackles;
    private int groundDuels;
    private int groundDuelsWon;
    private int aerialDuels;
    private int aerialDuelsWon;
    private int yellowCards;
    private int redCards;
    private int fouls;

    public Player (){
    }

    public Player(String name, Team team, String nationality, String position, int appearances, int minPlayed,
                  int goals, int assists, int shots, int shotsOnTarget, int bigChancesMissed, int touches, int passesAttempted,
                  int passesCompleted, double passCompPercentage, int crosses, int fThirdPasses, int succfThirdPasses, int carries, int progCarries,
                  int carriesEWGoal, int carriesEWAssist, int carriesEWShot, int carriesEWChance, int possessionWon, int dispossessed,
                  int interceptions, int clearances, int blocks, int tackles, int groundDuels, int groundDuelsWon, int aerialDuels, int aerialDuelsWon ,int yellowCards,
                  int redCards, int fouls) {
        this.name = name;
        this.team = team;
        this.nationality = nationality;
        this.position = position;
        this.appearances = appearances;
        this.minPlayed = minPlayed;
        this.goals = goals;
        this.assists = assists;
        this.shots = shots;
        this.shotsOnTarget = shotsOnTarget;
        this.bigChancesMissed = bigChancesMissed;
        this.touches = touches;
        this.passesAttempted = passesAttempted;
        this.passesCompleted = passesCompleted;
        this.passCompPercentage = passCompPercentage;
        this.crosses = crosses;
        this.fThirdPasses =  fThirdPasses;
        this.succfThirdPasses = succfThirdPasses;
        this.carries = carries;
        this.progCarries = progCarries;
        this.carriesEWGoal = carriesEWGoal;
        this.carriesEWAssist = carriesEWAssist;
        this.carriesEWShot= carriesEWShot;
        this.carriesEWChance = carriesEWChance;
        this.possessionWon = possessionWon;
        this.dispossessed = dispossessed;
        this.interceptions = interceptions;
        this.clearances = clearances;
        this.blocks = blocks;
        this.tackles = tackles;
        this.groundDuels = groundDuels;
        this.groundDuelsWon = groundDuelsWon;
        this.aerialDuels = aerialDuels;
        this.aerialDuelsWon = aerialDuelsWon;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.fouls = fouls;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPosition() {
        return position;
    }

    public int getAppearances() {
        return appearances;
    }

    public int getMinPlayed() {
        return minPlayed;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getShots() {
        return shots;
    }

    public int getShotsOnTarget() {
        return shotsOnTarget;
    }

    public int getBigChancesMissed() {
        return bigChancesMissed;
    }

    public int getTouches() {
        return touches;
    }

    public int getPassesAttempted() {
        return passesAttempted;
    }

    public int getPassesCompleted() {
        return passesCompleted;
    }

    public double getPassCompPercentage() {
        return passCompPercentage;
    }

    public int getCrosses() {
        return crosses;
    }

    public int getfThirdPasses() {
        return fThirdPasses;
    }

    public int getSuccfThirdPasses() {
        return succfThirdPasses;
    }

    public int getCarries() {
        return carries;
    }

    public int getProgCarries() {
        return progCarries;
    }

    public int getCarriesEWGoal() {
        return carriesEWGoal;
    }

    public int getCarriesEWAssist() {
        return carriesEWAssist;
    }

    public int getCarriesEWShot() {
        return carriesEWShot;
    }

    public int getCarriesEWChance() {
        return carriesEWChance;
    }

    public int getPossessionWon() {
        return possessionWon;
    }

    public int getDispossessed() {
        return dispossessed;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public int getClearances() {
        return clearances;
    }

    public int getBlocks() {
        return blocks;
    }

    public int getTackles() {
        return tackles;
    }

    public int getGroundDuels() {
        return groundDuels;
    }

    public int getGroundDuelsWon() {
        return groundDuelsWon;
    }

    public int getAerialDuels() {
        return aerialDuels;
    }

    public int getAerialDuelsWon() {
        return aerialDuelsWon;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public int getFouls() {
        return fouls;
    }
}


