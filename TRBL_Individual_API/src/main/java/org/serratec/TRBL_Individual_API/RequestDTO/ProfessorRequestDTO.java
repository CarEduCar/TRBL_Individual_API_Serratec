package org.serratec.TRBL_Individual_API.RequestDTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProfessorRequestDTO {
	
	@NotBlank(message= "Nome é obrigatorio!") @Size(max = 80, message = "A quantidade de caracteres deve ser no máximo 80")
	private String nomeProfessor;
	
	@NotNull(message= "Data de Nascimento é obrigatoria!")
	private LocalDate dataNasc;

	public ProfessorRequestDTO() {
		super();
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}
}
