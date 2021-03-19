package com.californiasense.tracker.controller;

import com.californiasense.tracker.dto.PilotDTO;
import com.californiasense.tracker.model.Community;
import com.californiasense.tracker.model.Pilot;
import com.californiasense.tracker.model.Province;
import com.californiasense.tracker.service.CommunityService;
import com.californiasense.tracker.service.PilotService;
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
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/pilots", produces = MediaType.APPLICATION_JSON_VALUE)
public class PilotController {

    private final PilotService pilotService;
    private final ProvinceService provinceService;
    private final CommunityService communityService;
    private final ModelMapper modelMapper;

    public PilotController(final PilotService pilotService,
                           final ProvinceService provinceService,
                           final CommunityService communityService,
                           final ModelMapper modelMapper) {
        this.pilotService = pilotService;
        this.provinceService = provinceService;
        this.communityService = communityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PilotDTO>> getAllPilots() {
        return ResponseEntity.ok(pilotService.findAll()
                .stream()
                .map(pilot -> mapToDTO(pilot, new PilotDTO()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PilotDTO> getPilot(@PathVariable final Long id) {
        return ResponseEntity.ok(pilotService.get(id)
                .map(pilot -> mapToDTO(pilot, new PilotDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<PilotDTO> createPilot(@RequestBody @Valid final PilotDTO pilotDTO) {
        Pilot pilot = mapToEntity(pilotDTO, new Pilot());
        //fix me : temporary workaround to fix create failure
        long id = pilotService.findAll().stream().mapToLong(Pilot::getId).max().orElse(1) + 1;
        pilot.setId(id);
        pilot = pilotService.create(pilot);
        MultiValueMap<String, String> headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pilot.getId())
                .toUri();
        headers.add(HttpHeaders.LOCATION, location.toString());
        return new ResponseEntity<>(mapToDTO(pilot, new PilotDTO()), headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePilot(@PathVariable final Long id,
                                            @RequestBody @Valid final PilotDTO pilotDTO) {
        final Pilot pilot = pilotService.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(pilotDTO, pilot);
        pilotService.update(id, pilot);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePilot(@PathVariable final Long id) {
        pilotService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        pilotService.delete(id);
        return ResponseEntity.noContent().build();
    }


    private PilotDTO mapToDTO(final Pilot pilot, final PilotDTO pilotDTO) {
        pilotDTO.setId(pilot.getId());
        pilotDTO.setName(pilot.getName());
        pilotDTO.setCommunities(pilot.getCommunities() == null ? null : pilot.getCommunities().getId());
        pilotDTO.setProvinces(pilot.getProvinces() == null ? null : pilot.getProvinces().stream()
                .map(Province::getId).collect(Collectors.toList()));
        return pilotDTO;
    }

    private Pilot mapToEntity(final PilotDTO pilotDTO, final Pilot pilot) {
        pilot.setName(pilotDTO.getName());
        if (pilotDTO.getCommunities() != null &&
                (pilot.getCommunities() == null || !pilot.getCommunities().getId().equals(pilotDTO.getCommunities()))) {
            final Community communities = communityService.get(pilotDTO.getCommunities())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "communities not found"));
            pilot.setCommunities(communities);
        }
        if (pilotDTO.getProvinces() != null) {
            final List<Province> provinces = provinceService.findAllById(pilotDTO.getProvinces());
            if (provinces.size() != pilotDTO.getProvinces().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of provinces not found");
            }
            pilot.setProvinces(new HashSet<>(provinces));
        }
        return pilot;
    }

}
