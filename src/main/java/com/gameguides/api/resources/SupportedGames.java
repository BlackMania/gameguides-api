package com.gameguides.api.resources;

import com.gameguides.api.models.SupportedGame;
import com.gameguides.api.repository.SupportedGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupportedGames {

    @Autowired
    private SupportedGameRepository supportedGameRepository;

    @GetMapping("/supportedgames")
    public List<SupportedGame> supportedGames() {
        return supportedGameRepository.findAll();
    }

}
