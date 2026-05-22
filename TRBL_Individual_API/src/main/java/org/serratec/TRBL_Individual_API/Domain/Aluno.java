package org.serratec.TRBL_Individual_API.Domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@NotBlank(message= "Endereço é obrigatorio!") @Size(max = 100, message = "A quantidade de caracteres deve ser no máximo 100")
	@Column(name = "endereco", nullable = false, length = 100)
	private String endereco;

	protected Aluno() {
		super();
	}

	protected Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}

	protected String getNomeAluno() {
		return nomeAluno;
	}

	protected void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	protected LocalDate getDataNasc() {
		return dataNasc;
	}

	protected void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	protected PerfilSocial getPerfil() {
		return perfil;
	}

	protected void setPerfil(PerfilSocial perfil) {
		this.perfil = perfil;
	}	
}
