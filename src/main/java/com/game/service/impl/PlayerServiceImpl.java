package com.game.service.impl;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerRepository;
import com.game.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service()
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository repository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository repository) {
        this.repository = repository;
    }



    @Override
    public List<Player> getPlayers(String name, String title, Race race, Profession profession,
                               Long after, Long before, Boolean banned, Integer minExp,
                               Integer maxExp, Integer minLevel, Integer maxLevel,
                               PlayerOrder order, Integer pageNumber, Integer pageSize) {
        if (order == null) {
            order = PlayerOrder.ID;
        }
        if (pageNumber == null) {
            pageNumber = 0;
        }
        if (pageSize == null) {
            pageSize = 3;
        }

        Date start = null, end = null;
        if (after != null) {
            start = new Date(after);
        }
        if (before != null) {
            end = new Date(before);
        }

        return repository.findAllBy(name, title, race, profession, start, end, minExp, maxExp, minLevel,
                 maxLevel, banned, PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())));
    }

    public long getPlayersCount(String name, String title, Race race, Profession profession, Long after, Long before,
                                Boolean banned, Integer minExp, Integer maxExp, Integer minLevel, Integer maxLevel) {
        Date start = null, end = null;
        if (after != null) {
            start = new Date(after);
        }
        if (before != null) {
            end = new Date(before);
        }

        return repository.countAllBy(name, title, race, profession, start, end, minExp, maxExp, minLevel, maxLevel, banned);
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
