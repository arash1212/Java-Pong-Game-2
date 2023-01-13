/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package settings;

/**
 *
 * @author Arash
 */
public class GameStateManager {

    private int player1Score, player2Score;
    public boolean shouldUpdateUI;

    private static final GameStateManager instance = new GameStateManager();

    public static GameStateManager getInstance() {
        return instance;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void addPlayer1Score() {
        player1Score++;
        shouldUpdateUI = true;
    }

    public void addPlayer2Score() {
        player2Score++;
        shouldUpdateUI = true;
    }
}
