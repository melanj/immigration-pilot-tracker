package com.californiasense.tracker.service;

import com.californiasense.tracker.model.Province;
import com.californiasense.tracker.repository.ProvinceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceServiceImpl(final ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public List<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Optional<Province> get(final Long id) {
        return provinceRepository.findById(id);
    }

    @Override
    public List<Province> findAllById(List<Long> ids) {
        return provinceRepository.findAllById(ids);
    }

    @Override
    public Province create(final Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public void update(final Long id, final Province province) {
        provinceRepository.save(province);
    }

    @Override
    public void delete(final Long id) {
        provinceRepository.deleteById(id);
    }


}
