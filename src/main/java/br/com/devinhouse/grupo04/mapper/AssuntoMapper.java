package br.com.devinhouse.grupo04.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.devinhouse.grupo04.dto.AssuntoDTOInput;
import br.com.devinhouse.grupo04.dto.AssuntoDTOOutput;
import br.com.devinhouse.grupo04.entity.Assunto;

@Mapper(componentModel = "spring")
public interface AssuntoMapper {
	@Mapping(source = "dtCadastro", target = "dtCadastro", dateFormat = "dd/MM/yyyy")
	Assunto toAssunto(AssuntoDTOInput assuntoDTO);
	AssuntoDTOOutput toDto(Assunto assunto);
	List<AssuntoDTOOutput> toDto(List<Assunto> assuntos);
}
