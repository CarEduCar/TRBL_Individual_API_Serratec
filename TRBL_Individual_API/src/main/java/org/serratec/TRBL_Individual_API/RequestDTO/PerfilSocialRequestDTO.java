package org.serratec.TRBL_Individual_API.RequestDTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PerfilSocialRequestDTO {
	
	@NotNull(message = "O perfil deve referenciar um aluno")
	private Integer idAluno;
	
	@NotBlank(message= "Classe Social é obrigatoria!") @Size(max = 60, message = "A quantidade de caracteres deve ser no máximo 60")
	private String classeSocial;
	
	@NotNull(message= "Valor da renda é obrigatoria!")
	@DecimalMin(value = "0", message = "O valor não pode ser negativo!")
	private BigDecimal renda;

	public PerfilSocialRequestDTO() {
		super();
	}

	public Integer getIdAluno() {
		return idAluno;
	}


	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}


	public String getClasseSocial() {
		return classeSocial;
	}


	public void setClasseSocial(String classeSocial) {
		this.classeSocial = classeSocial;
	}


	public BigDecimal getRenda() {
		return renda;
	}


	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}
	
	
}
