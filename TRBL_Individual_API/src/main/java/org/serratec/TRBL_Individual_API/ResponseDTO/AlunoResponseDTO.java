package org.serratec.TRBL_Individual_API.ResponseDTO;

import org.serratec.TRBL_Individual_API.Domain.Aluno;

public class AlunoResponseDTO{
	
	Integer id;
	String nome;
	
	public AlunoResponseDTO() {
		super();
	}
	
	public AlunoResponseDTO(Aluno aluno) {
		
		this.id = aluno.getId();
		this.nome = aluno.getNomeAluno();
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
