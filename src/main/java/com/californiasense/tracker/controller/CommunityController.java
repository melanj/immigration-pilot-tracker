package com.californiasense.tracker.controller;

import com.californiasense.tracker.dto.CommunityDTO;
import com.californiasense.tracker.model.Community;
import com.californiasense.tracker.model.Province;
import com.californiasense.tracker.service.CommunityService;
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
@RequestMapping(value = "/api/communities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommunityController {

    private final CommunityService communityService;
    private final ProvinceService provinceService;
    private final ModelMapper modelMapper;

    public CommunityController(final CommunityService CommunityService,
                               final ProvinceService provinceService,
                               final ModelMapper modelMapper) {
        this.communityService = CommunityService;
        this.provinceService = provinceService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<CommunityDTO>> getAllCommunities() {
        return ResponseEntity.ok(communityService.findAll()
                .stream()
                .map(community -> mapToDTO(community, new CommunityDTO()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityDTO> getCommunity(@PathVariable final Long id) {
        return ResponseEntity.ok(communityService.get(id)
                .map(community -> mapToDTO(community, new CommunityDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<CommunityDTO> createCommunity(
            @RequestBody @Valid final CommunityDTO communityDTO) {
        Community community = new Community();
        mapToEntity(communityDTO, community);
        community = communityService.create(community);
        MultiValueMap<String, String> headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(community.getId())
                .toUri();
        headers.add(HttpHeaders.LOCATION, location.toString());
        return new ResponseEntity<>( mapToDTO(community, new CommunityDTO()), headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCommunity(@PathVariable final Long id,
                                                @RequestBody @Valid final CommunityDTO communityDTO) {
        final Community community = communityService.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(communityDTO, community);
        communityService.update(id, community);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable final Long id) {
        communityService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        communityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CommunityDTO mapToDTO(final Community community, final CommunityDTO communityDTO) {
        communityDTO.setId(community.getId());
        communityDTO.setName(community.getName());
        communityDTO.setProvince(community.getProvince() == null ? null : community.getProvince().getId());
        return communityDTO;
    }

    private Community mapToEntity(final CommunityDTO communityDTO, final Community community) {
        community.setName(communityDTO.getName());
        if (communityDTO.getProvince() != null &&
                (community.getProvince() == null || !community.getProvince().getId().equals(communityDTO.getProvince()))) {
            final Province province = provinceService.get(communityDTO.getProvince())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "province not found"));
            community.setProvince(province);
        }
        return community;
    }

}
