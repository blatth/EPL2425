package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.List;

@RestController
public class Controller{
    @Autowired
    private DataLoader dataLoader;

    // Teams endpoint
    @GetMapping("/teams")
    public Collection<Team> getAllTeams() {
        return dataLoader.getTeamsDatabase().values();
    }

    // Specific team endpoint
    @GetMapping("/teams/{name}")
    public Team getTeamByName(@PathVariable String name) {
        return dataLoader.getTeamsDatabase().get(name);
    }

    // Players endpoiunt
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return dataLoader.getPlayersDatabase();
    }
}
