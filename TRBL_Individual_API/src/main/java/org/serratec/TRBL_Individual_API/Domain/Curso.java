package org.serratec.TRBL_Individual_API.Domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Curso")

public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_curso")
	private Integer id;
	
	@NotNull(message= "Nome é obrigatorio!") @Size(max = 80, message = "A quantidade de caracteres deve ser no máximo 80")
	@Column(name = "nome_curso", nullable = false, length = 80)
	private String nomeCurso;
	
	@Column(name = "data_inicio")
	private LocalDate dataInicio;
	
	@Column(name = "data_fim")
	private LocalDate dataFim;
	
	@Column(name = "descricao_curso")
	private String descricaoCurso;
	
	@OneToMany(mappedBy = "curso")
	private List<Turma> turmas;

	protected Curso() {
		super();
	}

	protected Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}

	protected String getNomeCurso() {
		return nomeCurso;
	}

	protected void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	protected LocalDate getDataInicio() {
		return dataInicio;
	}

	protected void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	protected LocalDate getDataFim() {
		return dataFim;
	}

	protected void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	protected String getDescricaoCurso() {
		return descricaoCurso;
	}

	protected void setDescricaoCurso(String descricaoCurso) {
		this.descricaoCurso = descricaoCurso;
	}

	protected List<Turma> getTurmas() {
		return turmas;
	}

	protected void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
}
