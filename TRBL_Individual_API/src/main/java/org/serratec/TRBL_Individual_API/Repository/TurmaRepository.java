package org.serratec.TRBL_Individual_API.Repository;

import java.util.Optional;

import org.serratec.TRBL_Individual_API.Domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Necessário para o Manuseio do Banco de Dados Turma")
@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
	
	Optional<Turma> findBycodigoTurma(String codigoTurma);
}
