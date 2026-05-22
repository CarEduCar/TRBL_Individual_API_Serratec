package org.serratec.TRBL_Individual_API.Service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.TRBL_Individual_API.DTO.ProfessorResponseDTO;
import org.serratec.TRBL_Individual_API.Domain.Professor;
import org.serratec.TRBL_Individual_API.Exception.ValorNaoEncontradoException;
import org.serratec.TRBL_Individual_API.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository profRepo;
	
	public List<ProfessorResponseDTO> ProfessorFindALL(){
		
		List<ProfessorResponseDTO> professorDTO = new ArrayList<ProfessorResponseDTO>();
		List<Professor> professores = profRepo.findAll();
		
		for(Professor Professor : professores) {
			professorDTO.add(new ProfessorResponseDTO(Professor));
		}
		
		if(professorDTO.isEmpty()) {
			throw new ValorNaoEncontradoException("Nenhum Professor cadastrado no sistema!");
		}	
		return professorDTO;
	}
	
	public ProfessorResponseDTO ProfessorFind(Integer id) {
		Professor professor = profRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum Professor com id "+ id));
		
		ProfessorResponseDTO professorDTO = new ProfessorResponseDTO(professor);
		return professorDTO;
	}
}
