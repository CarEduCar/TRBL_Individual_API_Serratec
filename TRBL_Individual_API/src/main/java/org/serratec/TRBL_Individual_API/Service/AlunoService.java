package org.serratec.TRBL_Individual_API.Service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.TRBL_Individual_API.Domain.Aluno;
import org.serratec.TRBL_Individual_API.Exception.ValorNaoEncontradoException;
import org.serratec.TRBL_Individual_API.Repository.AlunoRepository;
import org.serratec.TRBL_Individual_API.RequestDTO.AlunoRequestDTO;
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
	
	public List<AlunoResponseDTO> criarAlunos(List<AlunoRequestDTO> alunosReq) {
		List<Aluno> alunosPraSalvar = new ArrayList<>();
		List<AlunoResponseDTO> alunosDTO = new ArrayList<AlunoResponseDTO>();
		
		for(AlunoRequestDTO alunoDTO : alunosReq) {
			alunosPraSalvar.add(new Aluno(alunoDTO));
		}
		
		List<Aluno> alunosSalvos = alunoRepo.saveAll(alunosPraSalvar);
		
		for(Aluno aluno : alunosSalvos) {
			alunosDTO.add(new AlunoResponseDTO(aluno));
		}
		
		return alunosDTO;	
	}
	
	public AlunoResponseDTO atualizarAluno(Integer id, AlunoRequestDTO alunoDTO) {
		
		Aluno aluno = alunoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum aluno com o id " + id));
		
		aluno.setNomeAluno(alunoDTO.getNome());
		aluno.setDataNasc(alunoDTO.getDataNasc());
		
		Aluno alunoAtualizado = alunoRepo.save(aluno);
		
		return new AlunoResponseDTO(alunoAtualizado);
	}
	
	public void deletarAluno(Integer id) {

		Aluno alunoExistente = alunoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não é possível deletar. Aluno com id " + id + " não encontrado."));

		alunoRepo.delete(alunoExistente);
	}
}
