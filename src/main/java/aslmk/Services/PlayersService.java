package aslmk.Services;

import aslmk.Exceptions.PlayerSaveFailedException;
import aslmk.Models.Player;

public interface PlayersService {
    Player findByName(String name);
    void createPlayer(Player player) throws PlayerSaveFailedException;
    String getPlayerNameById(int id);
    Player getPlayerById(int playerId);
}
