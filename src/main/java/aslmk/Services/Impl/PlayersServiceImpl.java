package aslmk.Services.Impl;

import aslmk.DAO.PlayersDAO;
import aslmk.Models.Player;
import aslmk.Services.PlayersService;

public class PlayersServiceImpl implements PlayersService {
    private PlayersDAO playersDAO = new PlayersDAO();

    @Override
    public Player findByName(String name) {
        return playersDAO.getPlayerByName(name);
    }
    @Override
    public void save(Player player) {
        playersDAO.save(player);
    }

    @Override
    public void createPlayer(Player player) {
        playersDAO.createPlayer(player);
    }
}
