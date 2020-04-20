package com.gameguides.api.controllers;

import com.gameguides.api.models.LolGuide;
import com.gameguides.api.repository.LolGuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gg/lol")
public class LolGuideController {
    @Autowired
    private LolGuideRepository lolGuideRepository;

    @GetMapping("/guides")
    public List<LolGuide> getGuides(@Param("page") int page, @Param("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return lolGuideRepository.findAll(pageable).toList();
    }

    @GetMapping("/guides/{id}")
    public LolGuide getGuideByUUID(@PathVariable("id") String id)
    {
        return lolGuideRepository.findOneByUuid(UUID.fromString(id));
    }

    @PostMapping("/guides/add")
    public void addGuide(@RequestBody LolGuide guide)
    {
        lolGuideRepository.save(guide);
    }

}
