package br.com.devinhouse.grupo04.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devinhouse.grupo04.entity.Interessado;

@Repository
public interface InteressadoRepository extends JpaRepository<Interessado, Long> {
	List<Interessado> findByNuIdentificacao(String nuIdentificacao);
	

}
