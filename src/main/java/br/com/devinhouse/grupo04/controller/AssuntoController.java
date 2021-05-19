package br.com.devinhouse.grupo04.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devinhouse.grupo04.dto.AssuntoDTOInput;
import br.com.devinhouse.grupo04.dto.AssuntoDTOOutput;
import br.com.devinhouse.grupo04.entity.Assunto;
import br.com.devinhouse.grupo04.service.AssuntoService;

@RestController
@RequestMapping(value = "v1" + "/assuntos")
public class AssuntoController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AssuntoService service;
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public List<AssuntoDTOOutput> findAll() {
		List<Assunto> assuntos = service.findAll();
		
		return toDto(assuntos);
	}
	
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public AssuntoDTOOutput create(@RequestBody AssuntoDTOInput assuntoDTO) {
		Assunto assunto = service.create(toAssunto(assuntoDTO));
		
		return toDto(assunto);
	}
	
	@PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@PathVariable Long id, @RequestBody AssuntoDTOInput assuntoDTO) {
		service.update(id, toAssunto(assuntoDTO));
	}
	
	@DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	private Assunto toAssunto(AssuntoDTOInput assuntoDTO) {
		return modelMapper.map(assuntoDTO, Assunto.class);
	}

	private AssuntoDTOOutput toDto(Assunto assunto) {
		return modelMapper.map(assunto, AssuntoDTOOutput.class);
	}

	private List<AssuntoDTOOutput> toDto(List<Assunto> assuntos) {
		return assuntos.stream().map(assunto -> toDto(assunto)).collect(Collectors.toList());
	}
	
}
 