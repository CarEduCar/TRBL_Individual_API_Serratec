package org.serratec.TRBL_Individual_API.ResponseDTO;

import java.math.BigDecimal;

import org.serratec.TRBL_Individual_API.Domain.PerfilSocial;

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
