package com.devsuperior.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.services.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventController {

  @Autowired
  private EventService service;

  // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
  @GetMapping
  public ResponseEntity<List<EventDTO>> findAll() {
    List<EventDTO> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }
}
