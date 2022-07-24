package TileSet;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    private ArrayList<Meeple> playerMeeples = new ArrayList<>();
    private int playerNumber, playerScore, aiType, meepleChance;
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
                meeple.updateMeeple();
                if(meeple.isCompleted())
                {
                    int scored = meeple.scoreMeeple();
                    System.out.println("Player " + playerNumber + " scored " + scored + " points!");
                    playerScore += scored;
                    meeple.freeMeeple();
                }
            }
        }
    }
    public void finalMeeples()
    {
        for (Meeple meeple:playerMeeples) {
            if(meeple.hasTile())
            {
                int scored = meeple.scoreMeeple();
                System.out.println("Player " + playerNumber + " scored " + scored + " points!");
                playerScore += scored;
                meeple.freeMeeple();
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

    public void setAiType(int aiType) {
        this.aiType = aiType;
    }

    public int getAiType() {
        return aiType;
    }

    public void printMeepleLocations()
    {
        for(Meeple meeple: playerMeeples)
        {
            System.out.println(meeple + " is located at " + meeple.returnTile());
        }
    }

    public int getMeepleChance() {
        return meepleChance;
    }

    public void setMeepleChance(int meepleChance) {
        this.meepleChance = meepleChance;
    }
}

