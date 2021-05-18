package br.com.devinhouse.grupo04;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Projeto05Application {

	public static void main(String[] args) {
		SpringApplication.run(Projeto05Application.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.createTypeMap(String.class, LocalDate.class);
		modelMapper.addConverter(toStringDate);
		modelMapper.getTypeMap(String.class, LocalDate.class).setProvider((request) -> LocalDate.now());

		modelMapper.addConverter(new AbstractConverter<>() {
			@Override
			protected String convert(LocalDate source) {
				return source.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			}
		}, LocalDate.class, String.class);
		return modelMapper;
	}

	private static final Converter<String, LocalDate> toStringDate = new AbstractConverter<>() {
		@Override
		protected LocalDate convert(String source) {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return LocalDate.parse(source, format);
		}
	};


}
