package br.com.devinhouse.grupo04.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devinhouse.grupo04.dto.InteressadoDTOInput;
import br.com.devinhouse.grupo04.dto.InteressadoDTOOutput;

import br.com.devinhouse.grupo04.entity.Interessado;
import br.com.devinhouse.grupo04.service.InteressadoService;

@RestController
@RequestMapping(value = "/v1" + "/interessados")
public class InteressadoController {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private InteressadoService service;
	
	
	
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public InteressadoDTOOutput create(@Valid @RequestBody InteressadoDTOInput interessadoDTO) {
		Interessado interessado = service.create(toInteressado(interessadoDTO));
		
		return toDto(interessado);
	}
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public List<InteressadoDTOOutput> findAll(@RequestParam(required = false) String nu_identificacao) {
		List<Interessado> interessados = service.findAll(nu_identificacao);
		
		return toDto(interessados);
	}
	
	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public InteressadoDTOOutput find(@PathVariable long id) {
		Interessado interessado = service.find(id);
		
		return toDto(interessado);
	}
	
	
	@PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@PathVariable Long id, @RequestBody InteressadoDTOInput interessadoDTO) {
		service.update(id, toInteressado(interessadoDTO));
	}
	
	@DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	private Interessado toInteressado(InteressadoDTOInput interessadoDTO) {
		return modelMapper.map(interessadoDTO, Interessado.class);
	}

	private InteressadoDTOOutput toDto(Interessado interessado) {
		return modelMapper.map(interessado, InteressadoDTOOutput.class);
	}

	private List<InteressadoDTOOutput> toDto(List<Interessado> interessados) {
		return interessados.stream().map(interessado -> toDto(interessado)).collect(Collectors.toList());
	}

}