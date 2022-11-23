package com.catholic.eloservice.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.catholic.eloservice.entities.EloResult;
import com.catholic.eloservice.entities.Player1;
import com.catholic.eloservice.entities.Player2;
import com.catholic.eloservice.services.EloCalculator;
import com.catholic.eloservice.services.EloFormatter;

@RestController
public class EloController {

    @Autowired
    EloCalculator eloCalculator;

    @Autowired
    EloResult eloResult;

    @Autowired
    Player1 player1;

    @Autowired
    Player2 player2;

    @Autowired
    EloFormatter eloFormatter;
    
    @GetMapping("/elo/{eloFirstPlayer}/{eloSecondPlayer}/{whoWon}")
    public EloResult getElo(@PathVariable("eloFirstPlayer") double eloFirstPlayer,
                            @PathVariable("eloSecondPlayer") double eloSecondPlayer,
                            @PathVariable("whoWon") int whoWon){
                
                ArrayList<String> eloCalculated = eloCalculator.getfinalElo(32, eloFirstPlayer, eloSecondPlayer, whoWon, "###");
                
                player1.setOldElo(eloFormatter.format(eloFirstPlayer));
                player1.setNewElo(eloCalculated.get(0));
                eloResult.setPlayer1(player1);
                
                
                player2.setOldElo(eloFormatter.format(eloSecondPlayer));
                player2.setNewElo(eloCalculated.get(1));
                eloResult.setPlayer2(player2);

                
                return eloResult;
    }
}