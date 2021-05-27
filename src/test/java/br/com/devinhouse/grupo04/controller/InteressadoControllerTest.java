package br.com.devinhouse.grupo04.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.devinhouse.grupo04.dto.InteressadoDTOInput;
import br.com.devinhouse.grupo04.dto.InteressadoDTOOutput;
import br.com.devinhouse.grupo04.entity.Interessado;
import br.com.devinhouse.grupo04.mapper.InteressadoMapper;
import br.com.devinhouse.grupo04.service.InteressadoService;

@WebMvcTest(value = InteressadoController.class)
class InteressadoControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private InteressadoService interessadoService;

	@MockBean
	private InteressadoMapper interessadoMapper;

	@Mock
	private Interessado interessado;

	@Test
	void deveCriarUmInteressado() throws Exception {
		// given
		InteressadoDTOOutput interessadoDto = new InteressadoDTOOutput(1L, "Testeout", "76423941017", LocalDate.now(), 's');
		InteressadoDTOInput inputDTO = new InteressadoDTOInput("Teste", "76423941017", "05/10/1997");

		given(interessadoMapper.toInteressado(any(InteressadoDTOInput.class))).willReturn(interessado);
		when(interessadoMapper.toDto(any(Interessado.class))).thenReturn(interessadoDto);

		// when
		when(interessadoService.create(any(Interessado.class))).thenReturn(interessado);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/interessados")
				.contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(inputDTO));

		// then
		mvc.perform(request)
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").isNotEmpty())
			.andExpect(jsonPath("$.nmInteressado").value("Testeout"))
			.andExpect(jsonPath("$.dtNascimento").isNotEmpty())
			.andExpect(jsonPath("$.nuIdentificacao").isNotEmpty())
			.andExpect(jsonPath("$.nuIdentificacao").value("76423941017"))
			.andExpect(jsonPath("$.flAtivo").value("s"));
	}

	@Test
	void deveRetornarTodosOsInteressados() throws Exception {
		//given
		InteressadoDTOOutput interessadoDto = new InteressadoDTOOutput(1L, "Testeout", "76423941017", LocalDate.now(), 's');
		InteressadoDTOOutput outroInteressadoDto = new InteressadoDTOOutput(1L, "Testeoutoutro", "56575937071", LocalDate.now(), 's');
		given(interessadoService.findAll(null)).willReturn(List.of(interessado, interessado));
		//when
		when(interessadoMapper.toDto(List.of(interessado, interessado))).thenReturn(List.of(interessadoDto, outroInteressadoDto));
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/interessados")
				.contentType(APPLICATION_JSON);
		

		// then
		mvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0]id").isNotEmpty())
			.andExpect(jsonPath("$.[0]nmInteressado").value("Testeout"))
			.andExpect(jsonPath("$.[0]dtNascimento").isNotEmpty())
			.andExpect(jsonPath("$.[0]nuIdentificacao").isNotEmpty())
			.andExpect(jsonPath("$.[0]nuIdentificacao").value("76423941017"))
			.andExpect(jsonPath("$.[0]flAtivo").value("s"))
			.andExpect(jsonPath("$.[1]id").isNotEmpty())
			.andExpect(jsonPath("$.[1]nmInteressado").value("Testeoutoutro"))
			.andExpect(jsonPath("$.[1]dtNascimento").isNotEmpty())
			.andExpect(jsonPath("$.[1]nuIdentificacao").isNotEmpty())
			.andExpect(jsonPath("$.[1]nuIdentificacao").value("56575937071"))
			.andExpect(jsonPath("$.[1]flAtivo").value("s"));
		
	}
	
	@Test
	void deveRetornarInteressadoPorCpf() throws Exception {
		//given
		InteressadoDTOOutput interessadoDto = new InteressadoDTOOutput(1L, "Testeout", "76423941017", LocalDate.now(), 's');
//		InteressadoDTOOutput outroInteressadoDto = new InteressadoDTOOutput(1L, "Testeoutoutro", "56575937071", LocalDate.now(), 's');
		given(interessadoService.findAll("56575937071")).willReturn(List.of(interessado));
		//when
		when(interessadoMapper.toDto(List.of(interessado))).thenReturn(List.of(interessadoDto));
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/interessados")
				.contentType(APPLICATION_JSON).queryParam("nu_identificacao", "56575937071");
		

		// then
		mvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0]id").isNotEmpty())
			.andExpect(jsonPath("$.[0]nmInteressado").value("Testeout"))
			.andExpect(jsonPath("$.[0]dtNascimento").isNotEmpty())
			.andExpect(jsonPath("$.[0]nuIdentificacao").isNotEmpty())
			.andExpect(jsonPath("$.[0]nuIdentificacao").value("76423941017"))
			.andExpect(jsonPath("$.[0]flAtivo").value("s"));
			
	}
	
//	// when
//	when(assuntoMapper.toDto(List.of(assunto, assunto))).thenReturn(List.of(assuntoDto, outroAssuntoDto));
//
//	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/assuntos")
//			.contentType(MediaType.APPLICATION_JSON);
//
//	// then
//	mvc.perform(request)
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.[0].id").isNotEmpty())
//		.andExpect(jsonPath("$.[0].descricao").value("descricao"))
//		.andExpect(jsonPath("$.[1].id").isNotEmpty())
//		.andExpect(jsonPath("$.[1].descricao").value("outra descricao"))
//	;
//
//	@Test
//	void testFind() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDelete() {
//		fail("Not yet implemented");
//	}

}
