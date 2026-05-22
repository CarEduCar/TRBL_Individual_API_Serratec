package org.serratec.TRBL_Individual_API.RequestDTO;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TurmaRequestDTO {

	@NotBlank(message= "Código é obrigatorio!") @Size(max = 20, message = "A quantidade de caracteres deve ser no máximo 20")
	@UniqueElements
	private String codigoTurma;
	
	@NotNull(message = "A turma deve ter uma data de inicio!")
	private LocalDate dataInicio;
	
	private LocalDate dataFim;
	
	@NotNull(message= "O tamanho da turma é obrigatorio!")
	@Min(value = 1, message = "A turma deve ter no minimo 1 vaga")
	private Integer tamanho;
	
	private List<Integer> idAlunos;
	
	@NotNull(message = "A turma deve ter um professor!")
	private Integer idProfessor;
	
	@NotNull(message = "A turma deve pertencer a um curso!")
	private Integer idCurso;

	public TurmaRequestDTO() {
		super();
	}
	
	public List<Integer> getIdAlunos() {
		return idAlunos;
	}


	public void setIdAlunos(List<Integer> idAlunos) {
		this.idAlunos = idAlunos;
	}



	public Integer getIdProfessor() {
		return idProfessor;
	}



	public void setIdProfessor(Integer idProfessor) {
		this.idProfessor = idProfessor;
	}



	public Integer getIdCurso() {
		return idCurso;
	}



	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}



	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
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

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}
}
