package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayers(String name, String title, Race race, Profession profession, Long after, Long before,
                        Boolean banned, Integer minExp, Integer maxExp, Integer minLevel, Integer maxLevel,
                        PlayerOrder order, Integer pageNumber, Integer pageSize);
    long getPlayersCount(String name, String title, Race race, Profession profession, Long after, Long before,
                          Boolean banned, Integer minExp, Integer maxExp, Integer minLevel, Integer maxLevel);
    Player createPlayer(Player player);
    Player getPlayer(long id);
    Player updatePlayer(long id, Player player);
    boolean deletePlayer(long id);
    boolean isExist(long id);
}
