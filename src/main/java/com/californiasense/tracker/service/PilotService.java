package com.californiasense.tracker.service;

import com.californiasense.tracker.model.Pilot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PilotService {
    List<Pilot> findAll();
    Optional<Pilot> get(Long id);
    Pilot create(Pilot pilot);
    void update(Long id, Pilot pilot);
    void delete(Long id);
}
