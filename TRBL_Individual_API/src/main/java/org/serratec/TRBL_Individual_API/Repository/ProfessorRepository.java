package org.serratec.TRBL_Individual_API.Repository;

import org.serratec.TRBL_Individual_API.Domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
