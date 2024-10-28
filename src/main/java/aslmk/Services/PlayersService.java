package aslmk.Services;

import aslmk.Exceptions.PlayerSaveFailedException;
import aslmk.Models.Player;

import java.sql.SQLException;

public interface PlayersService {
    Player findByName(String name) throws SQLException;
    void createPlayer(Player player) throws PlayerSaveFailedException;
    String getPlayerNameById(int id);
    Player getPlayerById(int playerId);
}
