package org.serratec.TRBL_Individual_API.Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.TRBL_Individual_API.RequestDTO.AlunoRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Aluno")

public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aluno")
	private Integer id;
	
	@NotBlank(message= "Nome é obrigatorio!") @Size(max = 80, message = "A quantidade de caracteres deve ser no máximo 80")
	@Column(name = "nome_aluno", nullable = false, length = 80)
	private String nomeAluno;
	
	@NotNull(message= "Data de Nascimento é obrigatoria!")
	@Column(name = "data_nasc", nullable = false)
	private LocalDate dataNasc;
	
	@OneToOne(mappedBy = "aluno")
	private PerfilSocial perfil;
	
	@ManyToMany(mappedBy = "aluno")
    private List<Turma> turmas = new ArrayList<>();

	public List<Turma> getTurmas() {
		return turmas;
	}
	
	public Aluno() {
		super();
	}
	
	public Aluno(AlunoRequestDTO aluno) {
		this.nomeAluno = aluno.getNome();
		this.dataNasc = aluno.getDataNasc();
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public PerfilSocial getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilSocial perfil) {
		this.perfil = perfil;
	}	
}
