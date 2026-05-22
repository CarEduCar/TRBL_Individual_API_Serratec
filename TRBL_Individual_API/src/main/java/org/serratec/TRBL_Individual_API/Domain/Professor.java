package org.serratec.TRBL_Individual_API.Domain;

import java.time.LocalDate;
import java.util.List;

import org.serratec.TRBL_Individual_API.RequestDTO.ProfessorRequestDTO;

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
	
	@Column(name = "nome_professor", nullable = false, length = 80)
	private String nome;

	@Column(name = "data_nasc", nullable = false)
	private LocalDate dataNasc;
	
	@JsonIgnore
	@OneToMany(mappedBy = "professor")
	List<Turma> turmas;

	public Professor() {
		super();
	}
	
	public Professor(ProfessorRequestDTO professor) {
		this.nome = professor.getNome();
		this.dataNasc = professor.getDataNasc();
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

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
}
