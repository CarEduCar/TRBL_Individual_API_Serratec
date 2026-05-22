package org.serratec.TRBL_Individual_API.DTO;

import java.time.LocalDate;

import org.serratec.TRBL_Individual_API.Domain.Turma;

public class TurmaResponseDTO {
	public String codigo;
	public LocalDate dataInicio;
	public LocalDate dataFim;
	public Integer tamanho;
	
		
	public TurmaResponseDTO() {
		super();
	}
	
	public TurmaResponseDTO(Turma turma) {
		
		this.codigo = turma.getCodigoTurma();
		this.dataInicio = turma.getDataInicio();
		this.dataFim = turma.getDataFim();
		this.tamanho= turma.getTamanho();
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public LocalDate getInicio() {
		return dataInicio;
	}
	public void setInicio(LocalDate inicio) {
		this.dataInicio = inicio;
	}
	public LocalDate getFim() {
		return dataFim;
	}
	public void setFim(LocalDate fim) {
		this.dataFim = fim;
	}
	public Integer getTamanho() {
		return tamanho;
	}
	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}
	
	
}
