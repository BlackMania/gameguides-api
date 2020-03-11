package com.gameguides.api.repository;

import com.gameguides.api.models.Guide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GuideRepository extends JpaRepository<Guide, Long> {

    Page<Guide> findAll(Pageable pageable);
}
