package org.serratec.TRBL_Individual_API.Domain;

import java.math.BigDecimal;

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
	
	@NotBlank(message= "Classe Social é obrigatoria!") @Size(max = 60, message = "A quantidade de caracteres deve ser no máximo 60")
	@Column(name = "classe_social", nullable = false, length = 60)
	private String classeSocial;
	
	@NotNull(message= "Valor da renda é obrigatoria!")
	@DecimalMin(value = "0", message = "O valor não pode ser negativo!")
	@Column(name = "renda", nullable = false, precision = 7, scale = 2)
	private BigDecimal renda;
}
