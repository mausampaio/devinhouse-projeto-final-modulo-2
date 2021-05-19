package br.com.devinhouse.grupo04.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devinhouse.grupo04.entity.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

	List<Processo> findByChaveProcesso(String chaveProcesso);
	
	
}
