package com.example.demo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

@Service
public class DataLoader{
    private Map<String, Team> teamsDatabase = new HashMap<>();
    private List<Player> playersDatabase = new ArrayList<>();

    @PostConstruct
    public void loadData(){
        try{
            Reader teamReader = new InputStreamReader(getClass().getResourceAsStream("/teams.csv"));
            Iterable<CSVRecord> teamRecords = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build().parse(teamReader);

            for (CSVRecord record:teamRecords){
                int rank = Integer.parseInt(record.get("Rk"));
                String name = record.get("Squad");
                int matchesPlayed = Integer.parseInt(record.get("MP"));
                int wins =  Integer.parseInt(record.get("W"));
                int draws = Integer.parseInt(record.get("D"));
                int losses = Integer.parseInt(record.get("L"));
                int gf = Integer.parseInt(record.get("GF"));
                int ga = Integer.parseInt(record.get("GA"));
                int gd = Integer.parseInt(record.get("GD"));
                int points = Integer.parseInt(record.get("Pts"));
                double ptsPerMatch = Double.parseDouble(record.get("Pts/MP"));
                double xg = Double.parseDouble(record.get("xG"));
                double xga =  Double.parseDouble(record.get("xGA"));
                double xgd = Double.parseDouble(record.get("xGD"));
                double xgdPer90m = Double.parseDouble(record.get("xGD/90"));
                int attendance = Integer.parseInt(record.get("Attendance"));
                List<String> topScorer = parseListSafe(record.get("Top Team Scorer"));
                String goalkeeper = record.get("Goalkeeper");

                Team team = new Team(name, rank, matchesPlayed, wins, draws, losses, gf, ga, gd, points, ptsPerMatch, xg,
                        xga, xgd, xgdPer90m, attendance, topScorer, goalkeeper);
                teamsDatabase.put(name, team);
            }
            System.out.println("Teams loaded:" + teamsDatabase.size());


            Reader playerReader = new InputStreamReader(getClass().getResourceAsStream("/players.csv"));
            Iterable<CSVRecord> playerRecords = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build().parse(playerReader);

            for (CSVRecord record:playerRecords){
                String name = record.get("Player Name");

                String teamName = record.get("Club");
                Team team = teamsDatabase.get(teamName);    // JOIN

                String nationality = record.get("Nationality");
                String position = record.get("Position");

                int appearances = parseIntSafe(record.get("Appearances"));
                int minPlayed = parseIntSafe(record.get("Minutes"));
                int goals = parseIntSafe(record.get("Goals"));
                int assists =  parseIntSafe(record.get("Assists"));
                int shots = parseIntSafe(record.get("Shots"));
                int shotsOnTarget = parseIntSafe(record.get("Shots On Target"));
                int touches = parseIntSafe(record.get("Touches"));
                int passesAttempted = parseIntSafe(record.get("Passes"));
                int passesCompleted = parseIntSafe(record.get("Successful Passes"));
                double passCompPercentage = parsePercentageSafe(record.get("Passes%"));
                int carries = parseIntSafe(record.get("Carries"));
                int progCarries = parseIntSafe(record.get("Progressive Carries"));
                int interceptions = parseIntSafe(record.get("Interceptions"));
                int clearances = parseIntSafe(record.get("Clearances"));
                int blocks  = parseIntSafe(record.get("Blocks"));
                int tackles = parseIntSafe(record.get("Tackles"));
                int yellowCards =  parseIntSafe(record.get("Yellow Cards"));
                int redCards =  parseIntSafe(record.get("Red Cards"));
                int fouls = parseIntSafe(record.get("Fouls"));

                Player player = new Player(name, team, nationality, position, appearances, minPlayed,
                        goals, assists, shots, shotsOnTarget, touches, passesAttempted, passesCompleted,
                        passCompPercentage, carries, progCarries, interceptions, clearances, blocks,
                        tackles, yellowCards, redCards, fouls);

                playersDatabase.add(player);
            }
            System.out.println("Players loaded:" + playersDatabase.size());
        }
        catch (Exception e){
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // HELPERS

    private List<String> parseListSafe(String value) {
        if (value == null || value.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(value.split(",")).map(String::trim).toList();
    }

    private int parseIntSafe(String value){
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(value.trim());
    }

    private double parseDoubleSafe(String value){
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(value.trim().replace(",", "."));
    }

    private double parsePercentageSafe(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(value.replace("%", "").trim());
    }

    // GETTERS

    public Map<String, Team> getTeamsDatabase() {
        return teamsDatabase;
    }

    public List<Player> getPlayersDatabase() {
        return playersDatabase;
    }
}
