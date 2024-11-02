package aslmk.services.Impl;

import aslmk.dao.PlayersDAO;
import aslmk.exceptions.PlayerAlreadyExistsException;
import aslmk.exceptions.PlayerSaveFailedException;
import aslmk.models.Player;
import aslmk.services.PlayersService;

public class PlayersServiceImpl implements PlayersService {
    private PlayersDAO playersDAO = new PlayersDAO();

    public Player createPlayerIfNotExists(String playerName) throws PlayerSaveFailedException {
        Player player = new Player(playerName.toUpperCase());
        try {
            playersDAO.createPlayer(player);
        } catch (PlayerAlreadyExistsException e) {
            player = playersDAO.getPlayerByName(playerName);
        }
        return player;
    }
    @Override
    public Player getPlayerById(int playerId) {
        return playersDAO.getPlayerById(playerId);
    }
}
