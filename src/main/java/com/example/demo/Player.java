package com.example.demo;
import java.util.List;

public class Player {
    private String name;
    private Team team;
    private String nationality;
    private String position;
    private int appearances;
    private int minPlayed;
    private int goals;
    private int assists;
    private int shots;
    private int shotsOnTarget;
    private int touches;
    private int passesAttempted;
    private int passesCompleted;
    private double passCompPercentage;
    private int carries;
    private int progCarries;
    private int interceptions;
    private int clearances;
    private int blocks;
    private int tackles;
    private int yellowCards;
    private int redCards;
    private int fouls;

    public Player(String name, Team team, String nationality, String position, int appearances, int minPlayed,
                  int goals, int assists, int shots, int shotsOnTarget, int touches, int passesAttempted,
                  int passesCompleted, double passCompPercentage, int carries, int progCarries,
                  int interceptions, int clearances, int blocks, int tackles, int yellowCards,
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
        this.touches = touches;
        this.passesAttempted = passesAttempted;
        this.passesCompleted = passesCompleted;
        this.passCompPercentage = passCompPercentage;
        this.carries = carries;
        this.progCarries = progCarries;
        this.interceptions = interceptions;
        this.clearances = clearances;
        this.blocks = blocks;
        this.tackles = tackles;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.fouls = fouls;
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

    public int getCarries() {
        return carries;
    }

    public int getProgCarries() {
        return progCarries;
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


