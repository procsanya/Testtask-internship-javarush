package com.game.service.impl;

import com.game.entity.Player;
import com.game.entity.SearchFilters;
import com.game.repository.PlayerRepository;
import com.game.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository repository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Player> getPlayers(SearchFilters searchFilters) {
        return repository.findAll(searchFilters.getSpecification(), searchFilters.getPageable()).getContent();
    }

    public long getPlayersCount(SearchFilters searchFilters) {
        return repository.count(searchFilters.getSpecification());
    }

    @Override
    public Player createPlayer(Player player) {
        player.updateExperience();
        return repository.save(player);
    }

    @Override
    public Player getPlayer(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Player updatePlayer(long id, Player player) {
        player.setId(id);
        player.update(repository.findById(id).get());
        return repository.save(player);
    }

    @Override
    public boolean deletePlayer(long id) {
        if (isExist(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean isExist(long id) {
        return repository.existsById(id);
    }
}
