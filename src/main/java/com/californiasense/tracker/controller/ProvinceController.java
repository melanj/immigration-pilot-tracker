package com.californiasense.tracker.controller;

import com.californiasense.tracker.dto.ProvinceDTO;
import com.californiasense.tracker.model.Province;
import com.californiasense.tracker.service.ProvinceService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


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
        province = provinceService.create(province);
        MultiValueMap<String, String> headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(province.getId())
                .toUri();
        headers.add(HttpHeaders.LOCATION, location.toString());
        return new ResponseEntity<>(mapToDTO(province, new ProvinceDTO()), headers, HttpStatus.CREATED);
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
