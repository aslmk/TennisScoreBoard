package aslmk.Services;

import aslmk.Exceptions.PlayerSaveFailedException;
import aslmk.Models.Player;

import java.sql.SQLException;

public interface PlayersService {
    Player createPlayerIfNotExists(String playerName) throws PlayerSaveFailedException;
    String getPlayerNameById(int id);
    Player getPlayerById(int playerId);
}
