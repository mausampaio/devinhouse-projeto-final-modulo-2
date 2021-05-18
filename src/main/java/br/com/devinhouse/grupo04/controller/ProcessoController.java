package br.com.devinhouse.grupo04.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devinhouse.grupo04.dto.ProcessoDTO;
import br.com.devinhouse.grupo04.entity.Processo;
import br.com.devinhouse.grupo04.service.ProcessoService;

@RestController
@RequestMapping(value = "/v1" + "/processos")
public class ProcessoController {

	@Autowired
	private ProcessoService service;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProcessoDTO> findAll(@RequestParam(required = false) String chaveProcesso) {

		// List<Processo> processos = service.findAll(chaveProcesso);

		List<ProcessoDTO> processos = toDto(service.findAll(chaveProcesso));

		return processos;
	}

	/*
	 * @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody
	 * 
	 * @ResponseStatus(code = HttpStatus.OK) public ProcessoDTO find(@PathVariable
	 * Long id) { ProcessoDTO processo = service.find(id);
	 * 
	 * return new ProcessoDTO(processo); }
	 * 
	 * @PostMapping(produces = APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody
	 * 
	 * @ResponseStatus(code = HttpStatus.CREATED) public ProcessoDTO
	 * create(@RequestBody ProcessoDTO body) { ProcessoDTO processo =
	 * service.create(body);
	 * 
	 * return new ProcessoDTO(processo); }
	 * 
	 * @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody
	 * 
	 * @ResponseStatus(code = HttpStatus.NO_CONTENT) public void
	 * update(@PathVariable Long id, @RequestBody ProcessoDTO body) {
	 * service.update(id, body); }
	 * 
	 * @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody
	 * 
	 * @ResponseStatus(code = HttpStatus.NO_CONTENT) public void
	 * delete(@PathVariable Long id) { service.delete(id); }
	 */

	private ProcessoDTO toDto(Processo processo) {
		return modelMapper.map(processo, ProcessoDTO.class);
	}

	private List<ProcessoDTO> toDto(List<Processo> processos) {
		return processos.stream().map(this::toDto).collect(Collectors.toList());
	}

}