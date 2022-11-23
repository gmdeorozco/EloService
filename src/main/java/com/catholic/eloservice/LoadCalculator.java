package com.catholic.eloservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.catholic.eloservice.services.EloCalculator;

@Configuration
public class LoadCalculator {
    @Autowired
	EloCalculator eloCalculator;

	private final Logger log =  LoggerFactory.getLogger( LoadCalculator.class );

	@Bean
    CommandLineRunner iniDatabase(){

                    return args -> {
                        log.warn("FIRST GAME : DRAW");
						eloCalculator.getfinalElo(32, 2400, 2000,0,"###");

                        log.warn("FIRST GAME : 1-0");
                        eloCalculator.getfinalElo(32, 2400, 2000,1,"###");

                        log.warn("FIRST GAME : 0-1");
                        eloCalculator.getfinalElo(32, 2400, 2000,2,"###");
					};
					

	}
}
