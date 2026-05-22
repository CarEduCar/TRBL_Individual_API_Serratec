package org.serratec.TRBL_Individual_API.DTO;

import java.math.BigDecimal;

public class PerfilSocialDTO {
	
	public String classeSocial;
	public BigDecimal renda;
	
	
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
