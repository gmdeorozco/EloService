package com.catholic.eloservice.entities;

import org.springframework.beans.factory.annotation.Autowired;

import com.catholic.eloservice.services.EloFormatter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;



@Data
abstract class Player {
    @Autowired @JsonIgnore
    EloFormatter eloFormatter;

    String oldElo;
    String newElo;
    
    public String getEloChange(){
        return ( eloFormatter.format( Double.valueOf(newElo) - Double.valueOf(oldElo) ) );
    }

}
