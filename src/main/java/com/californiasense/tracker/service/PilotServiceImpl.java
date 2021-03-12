package com.californiasense.tracker.service;

import com.californiasense.tracker.model.Pilot;
import com.californiasense.tracker.repository.PilotRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;

    public PilotServiceImpl(final PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    @Override
    public List<Pilot> findAll() {
        return pilotRepository.findAll();
    }

    @Override
    public Optional<Pilot> get(final Long id) {
        return pilotRepository.findById(id);
    }

    @Override
    public Pilot create(final Pilot pilot) {
        return pilotRepository.save(pilot);
    }

    @Override
    public void update(final Long id, final Pilot pilot) {
        pilotRepository.save(pilot);
    }

    @Override
    public void delete(final Long id) {
        pilotRepository.deleteById(id);
    }


}
