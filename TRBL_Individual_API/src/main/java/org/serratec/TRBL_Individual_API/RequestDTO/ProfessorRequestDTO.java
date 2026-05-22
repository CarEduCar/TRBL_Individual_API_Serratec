package org.serratec.TRBL_Individual_API.RequestDTO;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "classe DTO que é utilizada para criação de novos professores")

public class ProfessorRequestDTO {
	
	@NotBlank(message= "Nome é obrigatorio!") @Size(max = 80, message = "A quantidade de caracteres deve ser no máximo 80")
	private String nome;
	
	@NotNull(message= "Data de Nascimento é obrigatoria!")
	private LocalDate dataNasc;

	public ProfessorRequestDTO() {
		super();
	}

	public String getNomeProfessor() {
		return nome;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nome = nomeProfessor;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}
}
