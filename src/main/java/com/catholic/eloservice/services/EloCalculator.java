package com.catholic.eloservice.services;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class EloCalculator {

    private static final Logger log = LoggerFactory.getLogger( EloCalculator.class );

    @Autowired
    EloFormatter eloFormatter;
    
    public ArrayList<String> getfinalElo(int K, double eloFirstPlayer, double eloSecondPlayer,
             int numberOfGamesFirstPlayer, int numberOfGamesSecondPlayer,
             int whoWon , String decimalFormat){       

        ArrayList<String>result =   new ArrayList<>();
        double transformedFirst = Math.pow(10, (eloFirstPlayer/400));
        double transformedSecond = Math.pow(10, (eloSecondPlayer/400));
        double expectedScoreFirst = transformedFirst/(transformedFirst+transformedSecond);
        double expectedSecoreSecond = transformedSecond/(transformedFirst+transformedSecond);
   
       boolean shouldChangeFirst = numberOfGamesSecondPlayer > 10 ? true : false;
       boolean shouldChangeSecond = numberOfGamesFirstPlayer > 10 ? true : false;

       double newEloFirst = eloFirstPlayer;
       double newEloSecond = eloSecondPlayer;

        switch(whoWon){
            case 0 :  {
                newEloFirst = shouldChangeFirst ? eloFirstPlayer + K * (0.5 - expectedScoreFirst) : eloFirstPlayer;
                newEloSecond = shouldChangeSecond ? eloSecondPlayer + K * (0.5 - expectedSecoreSecond) : eloSecondPlayer;
            }   break;

            case 1:{
                newEloFirst = shouldChangeFirst ? eloFirstPlayer + K * (1 - expectedScoreFirst) : eloFirstPlayer;
                newEloSecond = shouldChangeSecond ? eloSecondPlayer + K * (0 - expectedSecoreSecond) : eloSecondPlayer;
            } break;

            case 2: {
                newEloFirst = shouldChangeFirst ? eloFirstPlayer + K * (0 - expectedScoreFirst) : eloFirstPlayer;
                newEloSecond = shouldChangeSecond ? eloSecondPlayer + K * (1 - expectedSecoreSecond) : eloSecondPlayer;
            }

            
        }

        result.add( eloFormatter.format( newEloFirst ));
        result.add( eloFormatter.format( newEloSecond ));

        return result;

    }
}
