package aslmk.Services;

import aslmk.Models.Player;

public interface PlayersService {
    Player findByName(String name);
    void save(Player player);
    void createPlayer(Player player);
}
