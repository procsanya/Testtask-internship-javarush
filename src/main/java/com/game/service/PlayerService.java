package com.game.service;

import com.game.entity.Player;
import com.game.entity.SearchFilters;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayers(SearchFilters searchFilters);
    long getPlayersCount(SearchFilters searchFilters);
    Player createPlayer(Player player);
    Player getPlayer(long id);
    Player updatePlayer(long id, Player player);
    boolean deletePlayer(long id);
    boolean isExist(long id);
}
