package com.californiasense.tracker.controller;

import com.californiasense.tracker.dto.PilotDTO;
import com.californiasense.tracker.dto.ProvinceDTO;
import com.californiasense.tracker.model.Pilot;
import com.californiasense.tracker.model.Province;
import com.californiasense.tracker.service.ProvinceService;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(value = "/api/provinces", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProvinceController {

    private final ProvinceService provinceService;
    private final ModelMapper modelMapper;

    public ProvinceController(final ProvinceService provinceService, final ModelMapper modelMapper) {
        this.provinceService = provinceService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProvinceDTO>> getAllProvinces() {
        return ResponseEntity.ok(provinceService.findAll()
                .stream()
                .map(province -> mapToDTO(province, new ProvinceDTO()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceDTO> getProvince(@PathVariable final Long id) {
        return ResponseEntity.ok(provinceService.get(id)
                .map(province -> mapToDTO(province, new ProvinceDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<ProvinceDTO> createProvince(@RequestBody @Valid final ProvinceDTO provinceDTO) {
        Province province = mapToEntity(provinceDTO, new Province());
        return new ResponseEntity<>(mapToDTO(provinceService.create(province), new ProvinceDTO()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProvince(@PathVariable final Long id,
            @RequestBody @Valid final ProvinceDTO provinceDTO) {
        final Province province = provinceService.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(provinceDTO, province);
        provinceService.update(id, province);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvince(@PathVariable final Long id) {
        provinceService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        provinceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ProvinceDTO mapToDTO(final Province province, final ProvinceDTO provinceDTO) {
        //modelMapper.map(province, ProvinceDTO.class);
        provinceDTO.setId(province.getId());
        provinceDTO.setName(province.getName());
        provinceDTO.setPostalAbbr(province.getPostalAbbr());
        provinceDTO.setIsoCode(province.getIsoCode());
        provinceDTO.setCapital(province.getCapital());
        return provinceDTO;
    }

    private Province mapToEntity(final ProvinceDTO provinceDTO, final Province province) {
        //modelMapper.map(provinceDTO, Province.class);
        province.setName(provinceDTO.getName());
        province.setPostalAbbr(provinceDTO.getPostalAbbr());
        province.setIsoCode(provinceDTO.getIsoCode());
        province.setCapital(provinceDTO.getCapital());
        return province;
    }


}
