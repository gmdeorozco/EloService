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
    
    public ArrayList<String> getfinalElo(int K, double eloFirstPlayer, double eloSecondPlayer, int whoWon , String decimalFormat){       
        ArrayList<String>result =   new ArrayList<>();

        double transformedFirst = Math.pow(10, (eloFirstPlayer/400));

        double transformedSecond = Math.pow(10, (eloSecondPlayer/400));

        double expectedScoreFirst = transformedFirst/(transformedFirst+transformedSecond);
     

        double expectedSecoreSecond = transformedSecond/(transformedFirst+transformedSecond);
   
       

        switch(whoWon){
            case 0 :  {
                result.add( eloFormatter.format(eloFirstPlayer + K * (0.5 - expectedScoreFirst)));
                result.add( eloFormatter.format(eloSecondPlayer + K * (0.5 - expectedSecoreSecond)));
            }   break;

            case 1:{
                result.add(eloFormatter.format( eloFirstPlayer + K * (1 - expectedScoreFirst)));
                result.add( eloFormatter.format(eloSecondPlayer + K * (0 - expectedSecoreSecond)));
            } break;

            case 2:{
                result.add( eloFormatter.format(eloFirstPlayer + K * (0 - expectedScoreFirst)));
                result.add( eloFormatter.format(eloSecondPlayer + K * (1 - expectedSecoreSecond)));
            }
        }


        return result;

    }
}
