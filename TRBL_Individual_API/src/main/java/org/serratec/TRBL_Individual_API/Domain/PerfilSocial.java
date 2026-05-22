package org.serratec.TRBL_Individual_API.Domain;

import java.math.BigDecimal;

import org.serratec.TRBL_Individual_API.RequestDTO.PerfilSocialRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PerfilSocial")

public class PerfilSocial {
	
	@Id
	private Integer id;

	@MapsId
	@OneToOne
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;
	
	@Column(name = "classe_social", nullable = false, length = 60)
	private String classeSocial;
	
	@Column(name = "renda", nullable = false, precision = 7, scale = 2)
	private BigDecimal renda;

	public PerfilSocial() {
		super();
	}
	
	public PerfilSocial(String classe, BigDecimal renda) {
		this.classeSocial = classe;
		this.renda = renda;
	}
	
	public PerfilSocial(PerfilSocialRequestDTO perfil) {
		this.classeSocial = perfil.getClasseSocial();
		this.renda = perfil.getRenda();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
