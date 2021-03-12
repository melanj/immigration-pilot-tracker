package com.californiasense.tracker.service;

import com.californiasense.tracker.model.Community;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CommunityService {
    List<Community> findAll();

    Optional<Community> get(Long id);

    Community create(Community Community);

    void update(Long id, Community Community);

    void delete(Long id);
}
