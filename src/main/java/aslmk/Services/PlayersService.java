package aslmk.Services;

import aslmk.Exceptions.PlayerSaveFailedException;
import aslmk.Models.Player;

import java.sql.SQLException;

public interface PlayersService {
    Player findByName(String name) throws SQLException;
    Player createPlayer(String playerName) throws PlayerSaveFailedException;
    String getPlayerNameById(int id);
    Player getPlayerById(int playerId);
}
