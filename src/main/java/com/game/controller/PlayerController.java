package com.game.controller;

import com.game.entity.Player;
import com.game.entity.SearchFilters;
import com.game.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerController {
    private PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping("/rest/players")
    public ResponseEntity<List<Player>> getPlayers(SearchFilters searchFilters) {
        return new ResponseEntity<>(service.getPlayers(searchFilters), HttpStatus.OK);
    }

    @GetMapping("/rest/players/count")
    public ResponseEntity<Long> getPlayersCount(SearchFilters searchFilters) {
        return new ResponseEntity<>(service.getPlayersCount(searchFilters), HttpStatus.OK);
    }

    @PostMapping("/rest/players")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        if (player.isCanCreate()) {
            return new ResponseEntity<>(service.createPlayer(player), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/rest/players/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable(name = "id") long id) {
        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player player = service.getPlayer(id);
        return player == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/rest/players/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable(name ="id") long id, @RequestBody Player player) {
        if (id <= 0 || !player.isValidExperience() || !player.isValidBirthDay()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (service.isExist(id)) {
            return new ResponseEntity<>(service.updatePlayer(id, player), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/rest/players/{id}")
    public ResponseEntity deletePlayer(@PathVariable(name = "id") long id) {
        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return service.deletePlayer(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
