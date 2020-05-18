package com.gameguides.api.controllers;

import com.gameguides.api.repository.AccountRepository;
import com.gameguides.api.services.authorisation.ScopeValidator;
import com.gameguides.api.services.utils.DTOConverter;
import com.gameguides.api.DTO.LolGuideDTO;
import com.gameguides.api.models.LolGuide;
import com.gameguides.api.repository.LolGuideRepository;
import com.gameguides.api.services.authentication.AuthHandler;
import com.gameguides.api.services.utils.JWTDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gg/lol")
public class LolGuideController {
    @Autowired
    private LolGuideRepository lolGuideRepository;

    @Autowired
    private AuthHandler authHandler;

    @Autowired
    private ScopeValidator scopeValidator;

    @Autowired
    private AccountRepository accountRepository;

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
    public ResponseEntity<Object> addGuide(@RequestHeader("Authorization") String authToken, @RequestBody LolGuideDTO guide) {
        authToken = authToken.replace("Bearer ", "");
        if (authHandler.validateTokenAuthAttempt(authToken)) {
            LolGuide newGuide = DTOConverter.convertToLolGuide(guide);
            newGuide.uuid =  UUID.randomUUID();
            newGuide.madeby = accountRepository.findOneByUuid(UUID.fromString(JWTDecoder.getClientIdFromPayload(authToken)));
            lolGuideRepository.save(newGuide);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/guides/update/{id}")
    public ResponseEntity<Object> updateGuide(@RequestHeader("Authorization") String authToken, @PathVariable("id") String id, @RequestBody LolGuideDTO guide) {
        authToken = authToken.replace("Bearer ", "");
        if (scopeValidator.isOwner(authToken, id)) {
            if (lolGuideRepository.findOneByUuid(guide.uuid) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            LolGuide existingGuide = lolGuideRepository.findOneByUuid(guide.uuid);
            existingGuide.skills = guide.skills;
            existingGuide.runeset = guide.runeset;
            lolGuideRepository.save(existingGuide);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/guides/delete/{id}")
    public ResponseEntity<Object> deleteGuide(@RequestHeader("Authorization") String authToken, @PathVariable("id") String id)
    {
        authToken = authToken.replace("Bearer ", "");
        if (scopeValidator.isOwner(authToken, id)) {
            if (lolGuideRepository.findOneByUuid(UUID.fromString(id)) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            LolGuide existingGuide = lolGuideRepository.findOneByUuid(UUID.fromString(id));
            lolGuideRepository.delete(existingGuide);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
