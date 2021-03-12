package com.californiasense.tracker.repository;

import com.californiasense.tracker.model.Community;
import com.californiasense.tracker.model.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PilotRepository extends JpaRepository<Pilot, Long>, PagingAndSortingRepository<Pilot, Long> {
}
