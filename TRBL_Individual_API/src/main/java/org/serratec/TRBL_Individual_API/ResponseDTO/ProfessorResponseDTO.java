package org.serratec.TRBL_Individual_API.ResponseDTO;

import org.serratec.TRBL_Individual_API.Domain.Professor;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "classe DTO que é utilizada para retorno de valores com segurança")

public class ProfessorResponseDTO {
	public Integer id;
	public String nome;
	
	public ProfessorResponseDTO() {
		super();
	}

	public ProfessorResponseDTO(Professor professor) {

		this.id = professor.getId();	
		this.nome = professor.getNome();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
