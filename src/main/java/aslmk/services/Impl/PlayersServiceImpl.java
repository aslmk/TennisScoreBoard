package aslmk.services.Impl;

import aslmk.dao.PlayersDAO;
import aslmk.exceptions.PlayerAlreadyExistsException;
import aslmk.exceptions.PlayerSaveFailedException;
import aslmk.models.Player;
import aslmk.services.PlayersService;

public class PlayersServiceImpl implements PlayersService {
    private PlayersDAO playersDAO = new PlayersDAO();

    public Player createPlayerIfNotExists(String playerName) throws PlayerSaveFailedException {
        playerName = playerName.toUpperCase();
        Player newPlayer = new Player(playerName);
        try {
            playersDAO.createPlayer(newPlayer);
        } catch (PlayerAlreadyExistsException e) {
            newPlayer = playersDAO.getPlayerByName(playerName);
        }
        return newPlayer;
    }
    @Override
    public Player getPlayerById(int playerId) {
        return playersDAO.getPlayerById(playerId);
    }
}
