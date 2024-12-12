package com.devsuperior.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	public List<EventDTO> findAll() {
		List<Event> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new EventDTO(x)).toList();
	}
}