package org.serratec.TRBL_Individual_API.Domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Turma")

public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turma")
	private Integer id;
	
	@NotBlank(message= "Código é obrigatorio!") @Size(max = 20, message = "A quantidade de caracteres deve ser no máximo 20")
	@Column(name = "codigo_turma", nullable = false, length = 20)
	private String codigoTurma;
	
	@Column(name = "data_inicio")
	private LocalDate dataInicio;
	
	@Column(name = "data_fim")
	private LocalDate dataFim;
	
	@NotNull(message= "O tamanho da turma é obrigatorio!")
	@Min(value = 1, message = "A turma deve ter no minimo 1 vaga")
	@Column(name = "tamanho_turma", nullable = false)
	private Integer tamanho;
	
	@JsonIgnore
	@ManyToMany @JoinTable(name = "aluno_turma_matriculado",
	joinColumns = @JoinColumn(name = "id_turma"),
	inverseJoinColumns = @JoinColumn(name = "id_aluno"))
	private List<Aluno> alunos;
	
	@ManyToOne
	@JoinColumn(name = "id_professor")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;
}
