package org.serratec.TRBL_Individual_API.Exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<String> erros = new ArrayList<>();
		
		for( FieldError error : ex.getBindingResult().getFieldErrors()) {
			erros.add(error.getField() + ":" + error.getDefaultMessage());
		}
		
		ErroResposta erro = new ErroResposta(status.value(), "Argumentos Inválidos foram adicionados!");
		
		return super.handleExceptionInternal(ex, erro, headers, status, request);
	}
	
	@ExceptionHandler(ValorNaoEncontradoException.class)
	public ResponseEntity<ErroResposta> ValorNaoEncontrado (ValorNaoEncontradoException ex){
		
		List<String> erros = new ArrayList<>();
		
		ErroResposta erro = new ErroResposta(HttpStatus.NOT_FOUND.value(), "Conteudo não encontrado!");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}	
	
	@ExceptionHandler(ValorDuplicadoException.class)
	public ResponseEntity<ErroResposta> ValorDuplicado (ValorDuplicadoException ex){
		
		List<String> erros = new ArrayList<>();
		
		ErroResposta erro = new ErroResposta(HttpStatus.NOT_FOUND.value(), "Já existe um Atributo com essa Entrada!");
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}	
}
