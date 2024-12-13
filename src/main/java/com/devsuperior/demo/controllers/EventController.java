package com.devsuperior.demo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.services.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/events")
public class EventController {

  @Autowired
  private EventService service;

  @GetMapping
  public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
    Page<EventDTO> list = service.findAll(pageable);
    return ResponseEntity.ok().body(list);
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO dto) {
    CityDTO cityDTO = new CityDTO(dto.getCityId(), null);
    dto = service.insert(dto, cityDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

}
