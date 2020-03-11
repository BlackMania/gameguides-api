package com.gameguides.api.resources;

import com.gameguides.api.models.Guide;
import com.gameguides.api.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gg/lol")
public class GuideEndpoint {
    @Autowired
    private GuideRepository guideRepository;

    @GetMapping("/guides")
    public List<Guide> getGuides(@Param("page") int page, @Param("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return guideRepository.findAll(pageable).toList();
    }
}
