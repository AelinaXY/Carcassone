package com.company;

import java.util.HashMap;
import java.util.List;

import com.company.controllers.Runner;

import TileSet.Player;

public class Main {

    public static void main(String[] args) {


        HashMap<Integer,Double> outputValueHashMap = new HashMap<>();

        List<Player> output = Runner.playerlessRunner(4);

        for(Player player : output)
        {
            outputValueHashMap.put(player.getPlayerNumber(),Double.valueOf(player.getPlayerScore()));
        }

        // System.out.println(outputValueHashMap);


        for(int i = 1; i < 1000; i++)
        {
            output = Runner.playerlessRunner(4);

            for(Player player : output)
            {
                outputValueHashMap.put(player.getPlayerNumber(),Runner.calculateAverage(outputValueHashMap.get(player.getPlayerNumber()), Double.valueOf(player.getPlayerScore()), i));
            }

        }

        System.out.println(outputValueHashMap);
    }
}