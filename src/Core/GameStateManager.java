package Core;

public class GameStateManager {
    // This is meant to handle user states, such as the game running, them dying and
    // going to game over etc.
    private static GameStateManager instance;
    private GameState currentState;

    // honestly debating over singleton usage atm. But ill keep it for now.
    // read that java garbage collector is more smooth compared to C#
    private GameStateManager() {
        // Default state
        currentState = GameState.RUNNING;
    }

    public static synchronized GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState newState) {
        this.currentState = newState;
    }

/*  Currently not used, in any methods but will keep *just in case*
    public boolean isGameWon() {
        return currentState == GameState.VICTORY;
    }

    public boolean isGameLost() {
        return currentState == GameState.GAME_OVER;
    }
*/

}
