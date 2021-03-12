package com.californiasense.tracker.service;

import com.californiasense.tracker.model.Community;
import com.californiasense.tracker.repository.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;


    public CommunityServiceImpl(final CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    @Override
    public Optional<Community> get(final Long id) {
        return communityRepository.findById(id);
    }

    @Override
    public Community create(final Community community) {
        return communityRepository.save(community);
    }

    @Override
    public void update(final Long id, final Community community) {
        communityRepository.save(community);
    }

    @Override
    public void delete(final Long id) {
        communityRepository.deleteById(id);
    }


}
