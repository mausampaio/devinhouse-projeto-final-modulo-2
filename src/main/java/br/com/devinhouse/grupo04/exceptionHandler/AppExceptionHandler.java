package br.com.devinhouse.grupo04.exceptionHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.devinhouse.grupo04.service.exceptions.ProcessoNotFoundException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProcessoNotFoundException.class)
	public ResponseEntity<Object> handleProcessoNotFoundException(ProcessoNotFoundException ex, WebRequest request) {

		Validacao validacao = new Validacao(LocalDate.now(), ex.getMessage(), 404);

		return ResponseEntity.status(404).body(validacao);
	}

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Validacao validacao = new Validacao(LocalDate.now(),
				"Um ou mais campos estão incorretos. Corrija e tente novamente", 400);

		new Locale("pt-BR");

		List<Validacao.Campo> campos = ex.getBindingResult().getAllErrors().stream()
				.map(erro -> new Validacao.Campo(((FieldError) erro).getField(),
						messageSource.getMessage(erro, LocaleContextHolder.getLocale())))
				.collect(Collectors.toList());
		
		validacao.setCampos(campos);
		
		return super.handleExceptionInternal(ex, validacao, headers, status, request);
	}
}
