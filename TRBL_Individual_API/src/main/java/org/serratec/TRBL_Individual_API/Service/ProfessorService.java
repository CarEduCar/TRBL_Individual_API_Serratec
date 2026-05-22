package org.serratec.TRBL_Individual_API.Service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.TRBL_Individual_API.Domain.Professor;
import org.serratec.TRBL_Individual_API.Exception.ValorNaoEncontradoException;
import org.serratec.TRBL_Individual_API.Repository.ProfessorRepository;
import org.serratec.TRBL_Individual_API.RequestDTO.ProfessorRequestDTO;
import org.serratec.TRBL_Individual_API.ResponseDTO.ProfessorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository profRepo;
	
	public List<ProfessorResponseDTO> buscarTodosProfessores(){
		
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
	
	public ProfessorResponseDTO buscarProfessorPorId(Integer id) {
		Professor professor = profRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum Professor com id "+ id));
	
		ProfessorResponseDTO professorDTO = new ProfessorResponseDTO(professor);
		return professorDTO;
	}
	
public ProfessorResponseDTO atualizarProfessor(Integer id, ProfessorRequestDTO professorDTO) {
		
		Professor professor = profRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum professor com o id " + id));
		
		professor.setNomeProfessor(professorDTO.getNomeProfessor());
		professor.setDataNasc(professorDTO.getDataNasc());
		
		Professor professorAtualizado = profRepo.save(professor);
		
		return new ProfessorResponseDTO(professorAtualizado);
	}
	
	public void deletarProfessor(Integer id) {

		Professor professor = profRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não é possível deletar. Professor com id " + id + " não encontrado."));

		profRepo.delete(professor);
	}
}
