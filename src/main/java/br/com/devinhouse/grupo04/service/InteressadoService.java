package br.com.devinhouse.grupo04.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.grupo04.entity.Interessado;
import br.com.devinhouse.grupo04.entity.Processo;
import br.com.devinhouse.grupo04.repository.InteressadoRepository;
import br.com.devinhouse.grupo04.util.AtualizaColunasUtil;

@Service
public class InteressadoService {

	@Autowired
	private InteressadoRepository repository;

	public Interessado create(Interessado interessado) {
		interessado.setNuIdentificacao(interessado.getNuIdentificacao().replaceAll("([^\\d])", ""));
		return repository.save(interessado);
	}

	public List<Interessado> findAll(String nuIdentificacao) {
		if (nuIdentificacao != null) {
		return repository.findByNuIdentificacao(nuIdentificacao);
		}
		return repository.findAll();
	}

	public Interessado find(long id) {
		Optional<Interessado> interessado = repository.findById(id);

		return interessado.orElseThrow();

	}

	public void update(Long id, Interessado interessado) {
		Optional<Interessado> result = repository.findById(id);

		Interessado novoInteressado = result.orElseThrow();

		BeanUtils.copyProperties(interessado, novoInteressado, AtualizaColunasUtil.getNullPropertyNames(interessado));

		repository.save(novoInteressado);

	}

	public void delete(Long id) {
		repository.deleteById(id);

	}
}
