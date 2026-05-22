package org.serratec.TRBL_Individual_API.Service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.TRBL_Individual_API.Domain.Aluno;
import org.serratec.TRBL_Individual_API.Exception.ValorNaoEncontradoException;
import org.serratec.TRBL_Individual_API.Repository.AlunoRepository;
import org.serratec.TRBL_Individual_API.ResponseDTO.AlunoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepo;
	
	public List<AlunoResponseDTO> buscarTodosAlunos(){
		
		List<AlunoResponseDTO> alunosDTO = new ArrayList<AlunoResponseDTO>();
		List<Aluno> alunos = alunoRepo.findAll();
		
		for(Aluno aluno : alunos) {
			alunosDTO.add(new AlunoResponseDTO(aluno));
		}
		
		if(alunosDTO.isEmpty()) {
			throw new ValorNaoEncontradoException("Nenhum aluno cadastro no sistema!");
		}	
		return alunosDTO;
	}
	
	public AlunoResponseDTO buscarAlunoPorId(Integer id) {
		Aluno aluno = alunoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum aluno com id "+ id));
		
		AlunoResponseDTO alunoDTO = new AlunoResponseDTO(aluno);
		return alunoDTO;
	}
	
	public List<AlunoResponseDTO> criarAlunos(List<AlunoResponseDTO> aluno) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		List<AlunoResponseDTO> alunosDTO = new ArrayList<AlunoResponseDTO>();
		

		
		return aluno;
	}
	
	
}
