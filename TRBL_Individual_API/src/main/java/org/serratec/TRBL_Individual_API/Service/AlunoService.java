package org.serratec.TRBL_Individual_API.Service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.TRBL_Individual_API.DTO.AlunoResponseDTO;
import org.serratec.TRBL_Individual_API.Domain.Aluno;
import org.serratec.TRBL_Individual_API.Exception.ValorNaoEncontradoException;
import org.serratec.TRBL_Individual_API.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepo;
	
	public List<AlunoResponseDTO> AlunosFindALL(){
		
		List<AlunoResponseDTO> alunosDTO = new ArrayList<AlunoResponseDTO>();
		List<Aluno> alunos = alunoRepo.findAll();
		
		for(Aluno aluno : alunos) {
			alunosDTO.add(new AlunoResponseDTO(aluno));
		}
		
		return alunosDTO;
	}
	
	public AlunoResponseDTO AlunoFind(Integer id) {
		Aluno aluno = alunoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum aluno com id "+ id));
		
		AlunoResponseDTO alunoDTO = new AlunoResponseDTO(aluno);
		return alunoDTO;
	}
}
