package aslmk.services;

import aslmk.exceptions.PlayerSaveFailedException;
import aslmk.models.Player;

public interface PlayersService {
    Player createPlayerIfNotExists(String playerName) throws PlayerSaveFailedException;
}
