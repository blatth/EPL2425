package com.example.demo;

import java.util.List;

public class Team{
    private String name;
    private int rank;
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int losses;
    private int gf; // goals for
    private int ga; // goals against
    private int gd; // gf-ga
    private int points;
    private double ptsPerMatch;
    private double xg;
    private double xga;
    private double xgd;
    private double xgdPer90m;
    private int attendance;
    private List<String> topScorer;
    private String goalkeeper;

    // --------------------------

    public Team(String name, int rank, int matchesPlayed, int wins, int draws, int losses,
                int gf, int ga, int gd, int points, double ptsPerMatch, double xg,
                double xga, double xgd, double xgdPer90m, int attendance,
                List<String> topScorer, String goalkeeper) {
        this.name = name;
        this.rank = rank;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.gf = gf;
        this.ga = ga;
        this.gd = gd;
        this.points = points;
        this.ptsPerMatch = ptsPerMatch;
        this.xg = xg;
        this.xga = xga;
        this.xgd = xgd;
        this.xgdPer90m = xgdPer90m;
        this.attendance = attendance;
        this.topScorer = topScorer;
        this.goalkeeper = goalkeeper;
    }

    public int getRank() {return rank;}
    public String getName(){return name;}
    public int getMatchesPlayed() {return matchesPlayed;}
    public int getWins() {return wins;}
    public int getDraws() {return draws;}
    public int getLosses() {return losses;}
    public int getGa() {return ga;}
    public int getGf() {return gf;}
    public String getGoalkeeper() {return goalkeeper;}
    public List<String> getTopScorer() {return topScorer;}
    public int getAttendance() {return attendance;}
    public double getXgdPer90m() {return xgdPer90m;}
    public double getXgd() {return xgd;}
    public double getXga() {return xga;}
    public double getXg() {return xg;}
    public double getPtsPerMatch() {return ptsPerMatch;}
    public int getPoints() {return points;}
    public int getGd() {return gd;}
}


