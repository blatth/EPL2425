package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private TeamRepo teamRepository;

    @Autowired
    private PlayerRepo playerRepository;

    // Teams endpoint
    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Team específico endpoint
    @GetMapping("/teams/{name}")
    public Team getTeamByName(@PathVariable String name) {
        return teamRepository.findById(name).orElse(null); // .orElse(null) es por si se busca un equipo inexistente
    }

    // Players endpoint
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}