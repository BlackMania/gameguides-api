package com.gameguides.api.controllers;

import com.gameguides.api.repository.AccountRepository;
import com.gameguides.api.services.utils.DTOConverter;
import com.gameguides.api.DTO.LolGuideDTO;
import com.gameguides.api.models.LolGuide;
import com.gameguides.api.repository.LolGuideRepository;
import com.gameguides.api.services.authentication.AuthHandler;
import com.gameguides.api.services.utils.JWTDecoder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gg/lol")
public class LolGuideController {
    @Autowired
    private LolGuideRepository lolGuideRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthHandler authHandler;

    @GetMapping("/guides")
    public List<LolGuideDTO> getGuides(@Param("page") int page, @Param("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<LolGuide> guides = lolGuideRepository.findAll(pageable).toList();
        List<LolGuideDTO> guideDTOS = new ArrayList<>();
        for (LolGuide guide : guides) {
            guideDTOS.add(DTOConverter.convertToLolGuideDTO(guide));
        }
        return guideDTOS;
    }

    @GetMapping("/guides/{id}")
    public LolGuideDTO getGuideByUUID(@PathVariable("id") String id) {
        LolGuide guide = lolGuideRepository.findOneByUuid(UUID.fromString(id));
        return DTOConverter.convertToLolGuideDTO(guide);
    }

    @PostMapping("/guides/add")
    public ResponseEntity<Object> addGuide(@RequestHeader("Authorization") String authToken, @RequestBody LolGuide guide) {
        if (authHandler.validateTokenAuthAttempt(authToken)) {
            lolGuideRepository.save(guide);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/guides/update/{id}")
    public ResponseEntity<Object> updateGuide(@RequestHeader("Authorization") String authToken, @PathVariable("id") String id, @RequestBody LolGuideDTO guide) {
        authToken = authToken.replace("Bearer ", "");
        if (authHandler.validateTokenAuthAttempt(authToken)) {
            JSONObject decodedToken = JWTDecoder.decodeJWTPayLoad(authToken);
            if (decodedToken.getString("guides").contains(id)) {
                LolGuide newGuide = DTOConverter.convertToLolGuide(guide);
                LolGuide existingGuide = lolGuideRepository.findOneByUuid(newGuide.uuid);
                existingGuide.skills = newGuide.skills;
                existingGuide.runeset = newGuide.runeset;
                lolGuideRepository.save(existingGuide);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
