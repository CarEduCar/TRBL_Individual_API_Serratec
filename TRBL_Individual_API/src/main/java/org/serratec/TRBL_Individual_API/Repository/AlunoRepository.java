package org.serratec.TRBL_Individual_API.Repository;

import org.serratec.TRBL_Individual_API.Domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Necessário para o Manuseio do Banco de Dados Aluno")
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
