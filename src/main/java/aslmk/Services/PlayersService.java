package aslmk.Services;

import aslmk.Exceptions.PlayerSaveFailedException;
import aslmk.Models.Player;

import java.sql.SQLException;

public interface PlayersService {
    Player createPlayerIfNotExists(String playerName) throws PlayerSaveFailedException;
    Player getPlayerById(int playerId);
}
