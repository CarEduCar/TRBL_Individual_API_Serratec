package org.serratec.TRBL_Individual_API.ResponseDTO;

import java.math.BigDecimal;

import org.serratec.TRBL_Individual_API.Domain.Aluno;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "classe DTO que é utilizada para retorno de valores com segurança")

public class AlunoResponseDTO{
	
	private Integer id;
	private String nome;
	private String classeSocial;
	private BigDecimal renda;
	
	public AlunoResponseDTO() {
		super();
	}
	
	public AlunoResponseDTO(Aluno aluno) {
		
		this.id = aluno.getId();
		this.nome = aluno.getNomeAluno();
		
		if (aluno.getPerfil() != null) {
			this.classeSocial = aluno.getPerfil().getClasseSocial();
			this.renda = aluno.getPerfil().getRenda();
		}
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
}
