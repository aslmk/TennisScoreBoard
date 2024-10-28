package aslmk.Services.Impl;

import aslmk.DAO.PlayersDAO;
import aslmk.Exceptions.PlayerSaveFailedException;
import aslmk.Models.Player;
import aslmk.Services.PlayersService;

import java.sql.SQLException;

public class PlayersServiceImpl implements PlayersService {
    private PlayersDAO playersDAO = new PlayersDAO();

    @Override
    public Player findByName(String name) throws SQLException {
        return playersDAO.getPlayerByName(name);
    }

    @Override
    public void createPlayer(Player player) throws PlayerSaveFailedException {
        playersDAO.createPlayer(player);
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
