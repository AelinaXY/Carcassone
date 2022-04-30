package TileSet;

public class Meeple {
    private Player owner;
    private Tile placedTile;

    public Meeple(Player owner) {
        this.owner = owner;
    }

    public boolean hasTile(){return placedTile != null;}

    public int oppositeDirection(int i)
    {
        return (i+2)%4;
    }

    public void addMeepleToTile(Tile tile, int direction)
    {
        switch(tile.getSide(direction)){
            case CITY:
                System.out.println("CITY SWITCH");
                break;

            case ROAD:
                if(tile.isRoadEnd())
                {
                    if (checkEndRoadPoints(tile.getConnect(direction), oppositeDirection(direction), 0) == 1)
                    {
                        System.out.println("COMPLETE ROAD SWTICH");
                    }
                    else
                    {
                        this.placedTile = tile;
                        tile.setMeeple(this);
                        System.out.println("ROAD NOT COMPLETE SWITCH");
                    }
                }

                else {
                    int total = 0;

                    if (tile.getWestSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getWestConnect(), 1, 0);
                    }

                    if (tile.getEastSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getEastConnect(), 3, 0);
                    }

                    if (tile.getSouthSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getSouthConnect(), 0, 0);
                    }

                    if (tile.getNorthSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getNorthConnect(), 2, 0);
                    }

                    if (total == 2) {
                        System.out.println("ROAD COMPLETE SWITCH");
                    } else {
                        this.placedTile = tile;
                        tile.setMeeple(this);
                        System.out.println("ROAD INCOMPLETE SWITCH");
                    }
                }
                break;

            case FIELD:
                System.out.println("FIELD SWITCH");
                break;
        }

    }

    private int checkEndRoadPoints(Tile tile, int sideCameFrom, int total)
    {
        if (tile == null) return total;

        if (tile.isRoadEnd()) return ++total;

        for(int i = 0; i<4; i++)
        {
            if(tile.getSide(i) == TYPE.ROAD && sideCameFrom != i)
            {
                total = checkEndRoadPoints(tile.getConnect(i), oppositeDirection(i), total);
            }
        }

        return total;

    }


}
