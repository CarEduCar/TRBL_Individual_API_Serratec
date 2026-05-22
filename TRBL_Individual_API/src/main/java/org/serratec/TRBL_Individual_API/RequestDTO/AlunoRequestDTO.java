package org.serratec.TRBL_Individual_API.RequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "classe DTO que é utilizada para criação de novos alunos")

public class AlunoRequestDTO {

	@NotNull(message = "O nome é obrigatorio")
	@Size(max = 80, message = "A quantidade de caracteres deve ser no máximo 80")
	private String nome;
	
	@NotNull(message = "A data de nascimento é obrigatória!")
	private LocalDate dataNasc;	
	
	private String classeSocial;
	
	private BigDecimal renda;
	
	
	
	public String getClasseSocial() {
		return classeSocial;
	}

	public void setClasseSocial(String classeSocial) {
		this.classeSocial = classeSocial;
	}

	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	public AlunoRequestDTO() {
		super();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getDataNasc() {
		return dataNasc;
	}
	
	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}
}