package br.com.devinhouse.grupo04.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.grupo04.entity.Processo;
import br.com.devinhouse.grupo04.repository.ProcessoRepository;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository repository;

	public List<Processo> findAll(String chaveProcesso) {
		List<Processo> processos = new ArrayList<Processo>();
		Iterable<Processo> result = repository.findAll();
		
		result.forEach(processos::add);
		
		if (chaveProcesso != null) {
			List<Processo> processosFiltrados = new ArrayList<Processo>();
			
			for (Processo processo : processos) {
				if (chaveProcesso.equals(processo.getChaveProcesso())) {
					processosFiltrados.add(processo);
					
					return processosFiltrados;
				}
			}			
		}
		
		return processos;
	}

	public Processo find(Long id) {
		Optional<Processo> result = repository.findById(id);
		
		return result.orElseThrow();
	}
	
	public Processo create(Processo processo) {
		return repository.save(processo);
	}
	
	public void update(Long id, Processo body) {
		Optional<Processo> result = repository.findById(id);
		
		Processo processo = result.orElseThrow();
		
		//BeanUtils.copyProperties(body, processo, AtualizaColunasUtil.getNullPropertyNames(body));
		
		repository.save(processo);
	}
	
	public void delete(Long id) {
		Optional<Processo> result = repository.findById(id);
		
		Processo processo = result.orElseThrow();
		
		repository.delete(processo);
	}

}