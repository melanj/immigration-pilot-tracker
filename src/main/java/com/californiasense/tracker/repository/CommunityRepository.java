package com.californiasense.tracker.repository;

import com.californiasense.tracker.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CommunityRepository extends JpaRepository<Community, Long>, PagingAndSortingRepository<Community, Long> {
}
