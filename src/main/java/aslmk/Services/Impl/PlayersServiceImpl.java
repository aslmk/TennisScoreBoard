package aslmk.Services.Impl;

import aslmk.DAO.PlayersDAO;
import aslmk.Exceptions.PlayerSaveFailedException;
import aslmk.Models.Player;
import aslmk.Services.PlayersService;

import java.sql.SQLException;

public class PlayersServiceImpl implements PlayersService {
    private PlayersDAO playersDAO = new PlayersDAO();

    public Player createPlayerIfNotExists(String playerName) throws PlayerSaveFailedException {
        Player player = new Player(playerName.toUpperCase());
        Player existingPlayer = playersDAO.getPlayerByName(playerName);
        if (existingPlayer == null) {
            playersDAO.createPlayer(player);
        } else {
            player = existingPlayer;
        }
        return player;
    }
    @Override
    public String getPlayerNameById(int playerId) {
        return playersDAO.getPlayerById(playerId).getName();
    }
    @Override
    public Player getPlayerById(int playerId) {
        return playersDAO.getPlayerById(playerId);
    }
}
