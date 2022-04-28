package TileSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    ArrayList<Meeple> playerMeeples = new ArrayList<>();
    int playerNumber;

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


}

