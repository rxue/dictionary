package ruixue.mydictionary.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import ruixue.mydictionary.mapper.EntityToDTO;

@RequestMapping("/rest")
public abstract class CommonController<E,D> {
	protected EntityToDTO<E,D> dtoConverter; 
	protected ResponseEntity<D> getDTO(E entity) {
		return Optional.ofNullable(entity)
			.map(e -> new ResponseEntity<>(dtoConverter.apply(e), HttpStatus.OK))
			.orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	protected ResponseEntity<List<D>> getDTOList(List<E> entities) {
		return Optional.ofNullable(entities.stream()
				.map(e -> dtoConverter.apply(e)).collect(Collectors.toList()))
				.map(d -> new ResponseEntity<>(d, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	protected ResponseEntity<E> getEntity(E entity) {
		return Optional.ofNullable(entity)
			.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
			.orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
}
