package org.serratec.TRBL_Individual_API.Exception;

public class ErroResposta {
	
	private Integer status;
	private String menssagem;
	
	
	public ErroResposta(Integer status, String menssagem) {
		super();
		this.status = status;
		this.menssagem = menssagem;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getMenssagem() {
		return menssagem;
	}


	public void setMenssagem(String menssagem) {
		menssagem = menssagem;
	}
}
