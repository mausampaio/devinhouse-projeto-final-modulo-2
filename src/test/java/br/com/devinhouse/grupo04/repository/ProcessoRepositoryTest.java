package br.com.devinhouse.grupo04.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.devinhouse.grupo04.entity.Assunto;
import br.com.devinhouse.grupo04.entity.Interessado;
import br.com.devinhouse.grupo04.entity.Processo;

@DataJpaTest
class ProcessoRepositoryTest {

	@Autowired
	private ProcessoRepository repositoryTestProcesso;
	@Autowired
	private InteressadoRepository repositoryTestInteressado;
	@Autowired
	private AssuntoRepository repositoryTestAssunto;

	@BeforeEach
	private void createEntities() {
		Interessado interessado = new Interessado("Emanuelle", "18272985020", LocalDate.of(1992, 2, 1));
		Interessado novoIinteressado = repositoryTestInteressado.save(interessado);
		System.out.println(novoIinteressado.getId());
		Assunto assunto = new Assunto("construção civil");
		Assunto novoAssunto = repositoryTestAssunto.save(assunto);
		System.out.println(novoAssunto.getId());

		Processo processo = new Processo("soft", "2021", "implementar", novoAssunto, novoIinteressado);
		repositoryTestProcesso.save(processo);		
	}
	@AfterEach
	private void deleteAll() {
		System.out.println("oiii");
		repositoryTestProcesso.deleteAll();
		repositoryTestInteressado.deleteAll();
		repositoryTestAssunto.deleteAll();
		}
	@Test
	void testFindAllByChaveProcesso() {
		
		// when
		List<Processo> processos = repositoryTestProcesso.findAllByChaveProcesso("soft 1/2021");

		// then
		assertThat(processos).asList().size().isEqualTo(1);
	}

	
	@Test
	void testFindAllByCdInteressadoIdAndCdAssuntoId() {
		
		// when
		List<Processo> processos = repositoryTestProcesso.findAllByCdInteressadoIdAndCdAssuntoId(2L, 2L);

		// then
		assertThat(processos).asList().size().isEqualTo(1);
	}

	@Test
	void testFindAllByCdAssuntoId() {
	// when
	List<Processo> processos = repositoryTestProcesso.findAllByCdAssuntoId(3L);
	// then
	assertThat(processos).asList().size().isEqualTo(1);
	}

	@Test
	void testFindAllByCdInteressadoId() {
		// when
		List<Processo> processos = repositoryTestProcesso.findAllByCdInteressadoId(4L);
		// then
		assertThat(processos).asList().size().isEqualTo(1);
	}

}
