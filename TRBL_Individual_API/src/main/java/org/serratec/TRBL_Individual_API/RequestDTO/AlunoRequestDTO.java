package org.serratec.TRBL_Individual_API.RequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "classe DTO que é utilizada para criação de novos alunos")

public class AlunoRequestDTO {
	
	@Schema(description = "nome do Aluno", example = "Joao Pedro")
	@NotNull(message = "O nome é obrigatorio")
	@Size(max = 80, message = "A quantidade de caracteres deve ser no máximo 80")
	private String nome;
	
	@Schema(description = "Data de nascimento", example = "2004-05-20")
	@NotNull(message = "A data de nascimento é obrigatória!")
	private LocalDate dataNasc;	
	
	@Schema(description = "ClasseSocial do Aluno", example = "Classe Baixa")
	private String classeSocial;
	
	@Schema(description = "renda do aluno", example = "900.00")
	@Size(max = 50, message = "A quantidade de caracteres deve ser no máximo 50")
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