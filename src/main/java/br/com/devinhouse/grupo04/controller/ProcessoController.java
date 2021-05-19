package br.com.devinhouse.grupo04.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devinhouse.grupo04.dto.ProcessoDTOInput;
import br.com.devinhouse.grupo04.dto.ProcessoDTOOutput;
import br.com.devinhouse.grupo04.mapper.ProcessoMapper;
import br.com.devinhouse.grupo04.service.ProcessoService;


@RestController
@RequestMapping(value = "/v1" + "/processos")
public class ProcessoController {

	@Autowired
	private ProcessoService service;

	@Autowired
	private ProcessoMapper processoMapper;

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProcessoDTOOutput> findAll(@RequestParam(required = false) String chaveProcesso) {
		return  processoMapper.toDto(service.findAll(chaveProcesso));
	}
	
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public ProcessoDTOOutput create(@RequestBody ProcessoDTOInput processoDTO) {
		return processoMapper.toDto(service.create(processoMapper.toProcesso(processoDTO)));
	}

}