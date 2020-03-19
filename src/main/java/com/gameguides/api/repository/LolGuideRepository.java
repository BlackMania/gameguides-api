package com.gameguides.api.repository;

import com.gameguides.api.models.LolGuide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface LolGuideRepository extends JpaRepository<LolGuide, Long> {

    Page<LolGuide> findAll(Pageable pageable);

    LolGuide findOneByUuid(UUID uuid);
}
