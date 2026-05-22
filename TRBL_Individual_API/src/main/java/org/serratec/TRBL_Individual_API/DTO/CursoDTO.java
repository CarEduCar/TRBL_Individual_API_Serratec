package org.serratec.TRBL_Individual_API.DTO;

import java.time.LocalDate;

public class CursoDTO {
	
	public String id;
	public String nomeCurso;
	public LocalDate dataInicio;
	public LocalDate dataFim;
	public String descricaoCurso;
	
	
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
