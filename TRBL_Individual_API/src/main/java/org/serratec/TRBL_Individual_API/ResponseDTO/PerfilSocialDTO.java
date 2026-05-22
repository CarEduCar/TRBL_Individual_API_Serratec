package org.serratec.TRBL_Individual_API.ResponseDTO;

import java.math.BigDecimal;

import org.serratec.TRBL_Individual_API.Domain.PerfilSocial;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "classe DTO que é utilizada para retorno de valores com segurança")

public class PerfilSocialDTO {
	
	public String classeSocial;
	public BigDecimal renda;
	
	public PerfilSocialDTO() {
		super();
	}
	
	public PerfilSocialDTO(PerfilSocial perfil) {
		this.classeSocial = perfil.getClasseSocial();
		this.renda = perfil.getRenda();
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
