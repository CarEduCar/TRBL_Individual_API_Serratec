package org.serratec.TRBL_Individual_API.Domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Professor")

public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_professor")
	private Integer id;
	
	@NotBlank(message= "Nome é obrigatorio!") @Size(max = 80, message = "A quantidade de caracteres deve ser no máximo 80")
	@Column(name = "nome_professor", nullable = false, length = 80)
	private String nomeProfessor;
	
	@NotNull(message= "Data de Nascimento é obrigatoria!")
	@Column(name = "data_nasc", nullable = false)
	private LocalDate dataNasc;
	
	@JsonIgnore
	@OneToMany(mappedBy = "professor")
	List<Turma> turmas;

	protected Professor() {
		super();
	}

	protected Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}

	protected String getNomeProfessor() {
		return nomeProfessor;
	}

	protected void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	protected LocalDate getDataNasc() {
		return dataNasc;
	}

	protected void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	protected List<Turma> getTurmas() {
		return turmas;
	}

	protected void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
}
