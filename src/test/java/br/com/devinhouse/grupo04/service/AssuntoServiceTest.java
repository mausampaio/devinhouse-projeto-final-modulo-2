package br.com.devinhouse.grupo04.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.devinhouse.grupo04.entity.Assunto;
import br.com.devinhouse.grupo04.repository.AssuntoRepository;
import br.com.devinhouse.grupo04.service.exceptions.AssuntoFlAtivoInvalidException;
import br.com.devinhouse.grupo04.service.exceptions.AssuntoNotFoundException;

@ExtendWith(MockitoExtension.class)
class AssuntoServiceTest {

	@Mock
	private AssuntoRepository assuntoRepository;

	@InjectMocks
	private AssuntoService assuntoService;

	@Test
	void DeveRetornarTodosOsAssuntos() {
		// when
		assuntoService.findAll();
		// then
		verify(assuntoRepository).findAll();
	}

	@Test
	void DeveRetornarAssuntoBaseadoNoId() {
		// given
		Assunto assunto = new Assunto("descrição");
		assunto.setId(1L);

		// when
		when(assuntoRepository.findById(1L)).thenReturn(Optional.of(assunto));
		Assunto serviceAssunto = assuntoService.find(1L);

		// then
		assertThat(serviceAssunto.getId()).isEqualTo(assunto.getId());
	}

	@Test()
	void DeveRetornarExceptionCasoNaoEncontreOId() {

		assertThatThrownBy(() -> assuntoService.find(1L)).isInstanceOf(AssuntoNotFoundException.class);
	}

	@Test()
	void DeveRetornarNullCasoNaoSejaPassadoOId() {
		
		// when
		Assunto serviceAssunto = assuntoService.find(null);

		// then
		assertThat(serviceAssunto).isEqualTo(null);
	}

	@Test
	void DeveCriarUmAssunto() {
		// given
		Assunto assunto = new Assunto("descrição");
		assunto.setId(1L);

		// when
		assuntoService.create(assunto);
		ArgumentCaptor<Assunto> assuntoArgumentoCapturado = ArgumentCaptor.forClass(Assunto.class);

		// then
		verify(assuntoRepository).save(assuntoArgumentoCapturado.capture());

		Assunto assuntoCapturado = assuntoArgumentoCapturado.getValue();

		assertThat(assuntoCapturado).isEqualTo(assunto);

	}

	@Test
	void DeveAtualizarUmAssunto() {
		// given
		Assunto assunto = new Assunto("descrição");
		assunto.setId(1L);
		when(assuntoRepository.findById(1L)).thenReturn(Optional.of(assunto));

		// when
		Assunto novoAssunto = new Assunto("outra descrição");
		assuntoService.update(1L, novoAssunto);

		ArgumentCaptor<Assunto> assuntoArgumentoCapturado = ArgumentCaptor.forClass(Assunto.class);
		// then

		verify(assuntoRepository).save(assuntoArgumentoCapturado.capture());

		Assunto assuntoCapturado = assuntoArgumentoCapturado.getValue();

		assertThat(assuntoCapturado.getDescricao()).isEqualTo(novoAssunto.getDescricao());

	}

	@Test()
	void DeveRetornarExceptionCasoFlAtivoSejaDiferenteDeSOuN() {
		Assunto assunto = new Assunto("cool descrption");
		assunto.setId(1L);
		assunto.setFlAtivo('V');
			
		assertThatThrownBy(() -> assuntoService.update(1L, assunto)).isInstanceOf(AssuntoFlAtivoInvalidException.class);
	}

	@Test
	void DeveExcluirUmAssuntoPorId() {
		// when
		assuntoService.delete(1L);
		// then
		verify(assuntoRepository).deleteById(1L);
	}

}
