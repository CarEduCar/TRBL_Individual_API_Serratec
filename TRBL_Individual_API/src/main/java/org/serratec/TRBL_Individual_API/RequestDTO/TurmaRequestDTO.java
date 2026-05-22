package org.serratec.TRBL_Individual_API.RequestDTO;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "classe DTO que é utilizada para criação de novas turmas")

public class TurmaRequestDTO {
	
	@Schema(description = "codigo da turma", example = "MAT2026.1")
	@NotBlank(message= "Código é obrigatorio!") @Size(max = 20, message = "A quantidade de caracteres deve ser no máximo 20")
	@UniqueElements
	private String codigo;
	
	@Schema(description = "Data de inicio das aulas", example = "2004-05-20")
	@NotNull(message = "A turma deve ter uma data de inicio!")
	private LocalDate dataInicio;
	
	@Schema(description = "Data do fim das aulas", example = "2004-05-20")
	private LocalDate dataFim;
	
	@Schema(description = "Quantidade maxima de alunos na turma", example = "30")
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
		return codigo;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigo = codigoTurma;
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
