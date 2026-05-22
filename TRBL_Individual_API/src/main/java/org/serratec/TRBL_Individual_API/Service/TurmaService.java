package org.serratec.TRBL_Individual_API.Service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.TRBL_Individual_API.Domain.Aluno;
import org.serratec.TRBL_Individual_API.Domain.Curso;
import org.serratec.TRBL_Individual_API.Domain.Professor;
import org.serratec.TRBL_Individual_API.Domain.Turma;
import org.serratec.TRBL_Individual_API.Exception.ValorNaoEncontradoException;
import org.serratec.TRBL_Individual_API.Repository.AlunoRepository;
import org.serratec.TRBL_Individual_API.Repository.CursoRepository;
import org.serratec.TRBL_Individual_API.Repository.ProfessorRepository;
import org.serratec.TRBL_Individual_API.Repository.TurmaRepository;
import org.serratec.TRBL_Individual_API.RequestDTO.TurmaRequestDTO;
import org.serratec.TRBL_Individual_API.ResponseDTO.TurmaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {
	
	@Autowired
	private TurmaRepository turmaRepo;
	
	@Autowired
	private CursoRepository cursoRepo;
	
	@Autowired
	private ProfessorRepository profRepo;
	
	@Autowired
	private AlunoRepository alunoRepo;
	
	public List<TurmaResponseDTO> buscarTodasTurmas() {
		
		List<Turma> turmas = turmaRepo.findAll();
		List<TurmaResponseDTO> turmasDTO = new ArrayList<>();

		if(turmas.isEmpty()) {
			throw new ValorNaoEncontradoException("Nenhuma turma cadastrada no sistema!");
		}	
		
		for(Turma turma : turmas) {
			turmasDTO.add(new TurmaResponseDTO(turma));
		}
		
		return turmasDTO;
	}
	
	public TurmaResponseDTO buscarTurmaPorId(Integer id) {
		
		Turma turma = turmaRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhuma turma com o id " + id));
		
		return new TurmaResponseDTO(turma);
	}
	
	public TurmaResponseDTO buscarTurmaPorId(String codigo) {
		
		Turma turma = turmaRepo.findBycodigoTurma(codigo)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhuma turma com o codigo " + codigo));
		
		return new TurmaResponseDTO(turma);
	}
	
	public TurmaResponseDTO atualizarTurma(Integer id, TurmaRequestDTO turmaDTO) {
		
		Turma turma = turmaRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum Turma com o id " + id));
		
		Curso curso = cursoRepo.findById(turmaDTO.getIdCurso())
				.orElseThrow(() -> new ValorNaoEncontradoException("Curso não encontrado com o ID " + turmaDTO.getIdCurso()));
				
		Professor professor = profRepo.findById(turmaDTO.getIdProfessor())
				.orElseThrow(() -> new ValorNaoEncontradoException("Professor não encontrado com o ID " + turmaDTO.getIdProfessor()));
				
		List<Aluno> alunos= alunoRepo.findAllById(turmaDTO.getIdAlunos());
		
		turma.setCodigoTurma(turmaDTO.getCodigoTurma());
		turma.setDataInicio(turmaDTO.getDataInicio());
		turma.setDataFim(turmaDTO.getDataFim());
		turma.setTamanho(turmaDTO.getTamanho());
		turma.setAlunos(alunos);
		turma.setProfessor(professor);
		turma.setCurso(curso);
		
		Turma turmaAtualizado = turmaRepo.save(turma);
		
		return new TurmaResponseDTO(turmaAtualizado);
	}
	
	public void deletarTurma(Integer id) {

		Turma turmaExistente = turmaRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não é possível deletar. Turma com id " + id + " não encontrado."));

		turmaRepo.delete(turmaExistente);
	}
}