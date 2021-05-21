package br.com.devinhouse.grupo04.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.grupo04.entity.Processo;
import br.com.devinhouse.grupo04.repository.ProcessoRepository;
import br.com.devinhouse.grupo04.service.exceptions.ProcessoNotFoundException;
import br.com.devinhouse.grupo04.util.AtualizaColunasUtil;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository repository;

	public List<Processo> findAll(String chaveProcesso) {

		if (chaveProcesso != null) {
			return repository.findByChaveProcesso(chaveProcesso);
		}

		return repository.findAll();

	}

	public Processo find(Long id) {
		Optional<Processo> result = repository.findById(id);

		return result.orElseThrow(() -> new ProcessoNotFoundException());
	}

	public Processo create(Processo processo) {
		Processo novoProcesso = repository.save(processo);
		
		novoProcesso.setNuProcesso(novoProcesso.getId());
		
		novoProcesso.setChaveProcesso(novoProcesso.getSgOrgaoSetor() + " " + novoProcesso.getNuProcesso() + "/" + novoProcesso.getNuAno());
		
		return repository.save(novoProcesso);
	}

	public void update(Long id, Processo processo) {
		Optional<Processo> result = repository.findById(id);

		Processo novoProcesso = result.orElseThrow(() -> new ProcessoNotFoundException());

		BeanUtils.copyProperties(processo, novoProcesso, AtualizaColunasUtil.getNullPropertyNames(processo));

		repository.save(novoProcesso);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}