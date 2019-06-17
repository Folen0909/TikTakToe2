package com.turkalj.tiktaktoe.service;

import com.turkalj.tiktaktoe.dto.GameDTO;
import com.turkalj.tiktaktoe.model.Game;
import com.turkalj.tiktaktoe.model.GameGrid;
import com.turkalj.tiktaktoe.model.GameMove;
import com.turkalj.tiktaktoe.model.Player;
import com.turkalj.tiktaktoe.repository.GameRepository;
import com.turkalj.tiktaktoe.repository.GameStatusRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private GameStatusRepository gameStatusRepository;
    private PlayerService playerService;

    public GameServiceImpl(GameRepository gameRepository, GameStatusRepository gameStatusRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.gameStatusRepository = gameStatusRepository;
        this.playerService = playerService;
    }

    @Override
    public GameDTO findGameById(Long id) {
        return gameRepository.findGameById(id).convertToGameDTO();
    }

    private boolean setGameWinner(Game game, GameMove gameMove) {
        Player winner = playerService.findPlayerById(gameMove.getLastPlayerId());
        winner.setWins(winner.getWins() + 1L);
        playerService.updatePlayer(winner);
        game.setWinner(winner.getName());
        Player loser;
        if (game.getPlayerOne().getName().equals(winner.getName())) {
            loser = game.getPlayerTwo();
        }
        else {
            loser = game.getPlayerOne();
        }
        loser.setLosses(loser.getLosses() + 1L);
        playerService.updatePlayer(loser);
        game.setGameStatus(gameStatusRepository.findGameStatusById(2L));
        gameRepository.save(game);
        return true;
    }

    private void computerMove(Game game, char symbol) {

        Set<GameMove> gameMoves = game.getGameGrid().getGameMoves();
        Random random = new Random();
        List<GameMove> gameMovesEmpty = new ArrayList<>();
        for (long i = 1; i < 4L; i++) {
            for (long j = 1; j < 4L; j++) {
                boolean emptyMove = true;
                for (GameMove move : gameMoves) {
                    if (move.getRowNumber().equals(i) && move.getColumnNumber().equals(j)) {
                        emptyMove = false;
                        break;
                    }
                }
                if (emptyMove) {
                    GameMove newMove = new GameMove();
                    newMove.setRowNumber(i);
                    newMove.setColumnNumber(j);
                    gameMovesEmpty.add(newMove);
                }

            }
        }
        GameMove newMove = gameMovesEmpty.get(random.nextInt(gameMovesEmpty.size()-1));
        newMove.setGameId(game.getId());
        newMove.setSymbol(symbol);
        newMove.setLastPlayerId(1L);
        gameMoves.add(newMove);

        if (isGameOver(game, newMove)) {
            if (game.getPlayerOne().getName().equals("computer")) {
                gameRepository.save(game);
            }
        }
        gameRepository.save(game);
    }

    private boolean isGameOver(Game game, GameMove gameMove) {
        char[][] symbols = new char[3][3];
        Set<GameMove> gameMoves = game.getGameGrid().getGameMoves();
        char s = gameMove.getSymbol();
        int moveCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (GameMove move : gameMoves) {
                    if (move.getRowNumber() == i + 1 && move.getColumnNumber() == j + 1) {
                        symbols[i][j] = move.getSymbol();
                        moveCounter++;
                    }
                }
            }
        }
        for (int i = 0; true; i++) {
            if (symbols[gameMove.getRowNumber().intValue() - 1][i] != s)
                break;
            if (i == 2) {
                return setGameWinner(game, gameMove);
            }
        }
        for (int i = 0; true; i++) {
            if (symbols[i][gameMove.getColumnNumber().intValue() - 1] != s)
                break;
            if (i == 2) {
                return setGameWinner(game, gameMove);
            }
        }
        for (int i = 0; true; i++) {
            if (symbols[i][i] != s)
                break;
            if (i == 2) {
                return setGameWinner(game, gameMove);
            }
        }
        for (int i = 0; true; i++) {
            if (symbols[i][2 - i] != s)
                break;
            if (i == 2) {
                return setGameWinner(game, gameMove);
            }
        }
        if (moveCounter >= 9 && game.getGameStatus() != gameStatusRepository.findGameStatusById(2L)) {
            game.setWinner("No Winner");
            game.setGameStatus(gameStatusRepository.findGameStatusById(2L));
            Player draw = game.getPlayerOne();
            draw.setDraws(draw.getDraws() + 1L);
            playerService.updatePlayer(draw);
            draw = game.getPlayerTwo();
            draw.setDraws(draw.getDraws() + 1L);
            playerService.updatePlayer(draw);
            gameRepository.save(game);
            return true;
        }
        return false;
    }

    private Game newGame(String first, String second) throws Exception {
        if (first.equals(second)) {
            throw new Exception("Player required!!!!");
        }
        Game game = new Game();

        GameGrid gameGrid = new GameGrid();
        Set<GameMove> gameMoves = new HashSet<>();
        gameGrid.setGameMoves(gameMoves);
        game.setGameGrid(gameGrid);
        game.setWinner("");

        Player computer;
        Player player;
        if (playerService.findPlayerById(1L) == null) {
            computer = new Player("computer");
            playerService.createPlayer(computer);
        } else {
            computer = playerService.findPlayerById(1L);
        }
        if (first.equals("computer")) {
            if (playerService.findPlayerByName(second) != null) {
                player = playerService.findPlayerByName(second);
            } else {
                player = new Player(second);
                playerService.createPlayer(player);
            }
            game.setPlayerOne(computer);
            game.setPlayerTwo(player);
        } else {
            if (playerService.findPlayerByName(first) != null) {
                player = playerService.findPlayerByName(first);
            } else {
                player = new Player(first);
                playerService.createPlayer(player);
            }
            game.setPlayerOne(player);
            game.setPlayerTwo(computer);
        }

        game.setGameStatus(gameStatusRepository.findGameStatusById(1L));
        gameRepository.save(game);

        return game;
    }

    @Override
    public GameDTO createGame(String first, String second) throws Exception {
        Game game = newGame(first, second);
        return game.convertToGameDTO();
    }

    @Override
    public GameDTO playGameMove(Long id, Long row, Long column) throws Exception {
        Game game = gameRepository.findGameById(id);
        Set<GameMove> gameMoves = game.getGameGrid().getGameMoves();
        GameMove gameMove;

        if (game.getGameStatus().getId() == 2L)
            throw new Exception("Game finished!!!!!");
        if (row < 1 || row > 3 || column < 1 || column > 3)
            throw new Exception("Invalid move, out of bounds!!!");
        for (GameMove move : gameMoves) {
            if (move.getRowNumber().equals(row) && move.getColumnNumber().equals(column))
                throw new Exception("Invalid move, not empty!!!");
        }
        gameMove = new GameMove();
        gameMove.setRowNumber(row);
        gameMove.setColumnNumber(column);
        gameMove.setGameId(game.getId());
        gameMoves.add(gameMove);

        if (game.getPlayerOne().getName().equals("computer")) {
            gameMove.setSymbol('O');
            gameMove.setLastPlayerId(game.getPlayerTwo().getId());
            if (isGameOver(game, gameMove)) {
                if (!game.getWinner().equals(("No Winner"))) {
                    return game.convertToGameDTO();
                }
            }
            if (game.getGameStatus().getId() == 1L) {
                computerMove(game, 'X');
            }
        } else {
            gameMove.setSymbol('X');
            gameMove.setLastPlayerId(game.getPlayerOne().getId());
            if (isGameOver(game, gameMove)) {
                if (!game.getWinner().equals("No Winner")) {
                    return game.convertToGameDTO();
                }
            }
            if (game.getGameStatus().getId() == 1L) {
                computerMove(game, 'O');
            }

        }
        gameRepository.save(game);
        return game.convertToGameDTO();
    }

    @Override
    public void deleteGameById(Long id) {
        gameRepository.deleteGameById(id);
    }
}
