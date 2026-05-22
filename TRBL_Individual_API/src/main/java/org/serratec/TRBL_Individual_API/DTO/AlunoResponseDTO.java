package org.serratec.TRBL_Individual_API.DTO;

import java.time.LocalDate;

import org.serratec.TRBL_Individual_API.Domain.Aluno;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AlunoResponseDTO extends Aluno{
	
	Integer id;
	String nome;
	
	public AlunoResponseDTO() {
		super();
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
