package com.californiasense.tracker.service;

import com.californiasense.tracker.model.Province;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProvinceService {
    List<Province> findAll();
    Optional<Province> get(Long id);
    List<Province> findAllById(List<Long> ids);
    Province create(Province province);
    void update(Long id, Province province);
    void delete(Long id);
}
