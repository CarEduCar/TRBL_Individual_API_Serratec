package org.serratec.TRBL_Individual_API.Repository;

import org.serratec.TRBL_Individual_API.Domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
