package org.serratec.TRBL_Individual_API.RequestDTO;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CursoRequestDTO {

	@NotNull(message= "Nome é obrigatorio!") @Size(max = 80, message = "A quantidade de caracteres deve ser no máximo 80")
	@NotBlank(message = "Nome é obrigatorio!")
	private String nomeCurso;
	
	private LocalDate dataInicio;
	
	@Column(name = "data_fim")
	private LocalDate dataFim;
	
	@NotNull(message = "O curso deve ter uma descricao!")
	private String descricaoCurso;

	public CursoRequestDTO() {
		super();
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
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
