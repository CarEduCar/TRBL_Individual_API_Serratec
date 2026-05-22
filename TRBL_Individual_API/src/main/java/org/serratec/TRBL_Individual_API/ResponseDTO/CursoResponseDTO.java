package org.serratec.TRBL_Individual_API.ResponseDTO;

import java.time.LocalDate;
import org.serratec.TRBL_Individual_API.Domain.Curso;

public class CursoResponseDTO {
	
	public Integer id;
	public String nome;
	public LocalDate dataInicio;
	public LocalDate dataFim;
	public String descricao;
	
	public CursoResponseDTO() {
		super();
	}
	
	public CursoResponseDTO(Curso curso) {
		
		this.id = curso.getId();
		this.nome = curso.getNomeCurso();
		this.dataInicio = curso.getDataInicio();
		this.dataFim = curso.getDataFim();
		this.descricao = curso.getDescricaoCurso();
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
		return descricao;
	}
	public void setDescricaoCurso(String descricaoCurso) {
		this.descricao = descricaoCurso;
	}
	
	
}
