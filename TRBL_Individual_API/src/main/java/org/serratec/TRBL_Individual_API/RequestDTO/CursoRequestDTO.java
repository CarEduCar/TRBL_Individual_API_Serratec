package org.serratec.TRBL_Individual_API.RequestDTO;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "classe DTO que é utilizada para criação de novos cursos")

public class CursoRequestDTO {
	
	@Schema(description = "nome do Curso", example = "Psicologo de Computadores")
	@NotNull(message= "Nome é obrigatorio!") @Size(max = 80, message = "A quantidade de caracteres deve ser no máximo 80")
	private String nome;
	
	@Schema(description = "Data do inicio do curso", example = "2004-05-20")
	private LocalDate dataInicio;
	
	@Schema(description = "Data do fim do curso", example = "2004-05-20")
	private LocalDate dataFim;
	
	@Schema(description = "Descricao do curso", example = "Curso focado em tecnologia e outras coisas")
	@NotNull(message = "O curso deve ter uma descricao!")
	private String descricaoCurso;

	public CursoRequestDTO() {
		super();
	}

	public String getNomeCurso() {
		return nome;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nome = nomeCurso;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricaoCurso() {
		return descricaoCurso;
	}

	public void setDescricaoCurso(String descricaoCurso) {
		this.descricaoCurso = descricaoCurso;
	}
	
	
}
