package TileSet;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    private ArrayList<Meeple> playerMeeples = new ArrayList<>();
    private int playerNumber, playerScore;
    private boolean ai;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        Collections.addAll(playerMeeples,new Meeple(this), new Meeple(this), new Meeple(this), new Meeple(this), new Meeple(this), new Meeple(this), new Meeple(this));

    }

    public Meeple getFirstFreeMeeple()
    {
        for (Meeple playerMeeple : playerMeeples) {
            if (!playerMeeple.hasTile()) {
                return playerMeeple;
            }
        }
        return null;
    }

    public void updateMeeples()
    {
        for (Meeple meeple:playerMeeples) {
            if(meeple.hasTile())
            {
 //
                if(meeple.isCompleted())
                {
                    //playerScore += meeple.score();
                    meeple.freeMeeple();
                }
            }
        }
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}

