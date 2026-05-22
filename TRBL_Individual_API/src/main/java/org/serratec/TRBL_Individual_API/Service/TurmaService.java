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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
	
	@Operation(
			summary = "Lista todos as turmas", 
			description = "Lista todos as turmas cadastrados no Banco. Caso não haja nenhuma turma cadastrada, retorna uma mensagem de erro"
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrou pelo menos uma turma e retorna todas as que achou"),
			@ApiResponse(responseCode = "404", description = "Não encontrou nenhuma turma cadastrado")
		})
	
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
	
	@Operation(
			summary = "Busca uma turma por id", 
			description = "Recebe o codigo de uma turma na URL e retorna os dados detalhados. Se o id não existir, a operação é barrada."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "turma encontrada e retornado com sucesso."),
			@ApiResponse(responseCode = "404", description = "turma não encontrada com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL (ex: uma letra no lugar de um número).")
		})
	
	public TurmaResponseDTO buscarTurmaPorId(Integer id) {
		
		Turma turma = turmaRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhuma turma com o id " + id));
		
		return new TurmaResponseDTO(turma);
	}
	
	@Operation(
			summary = "Busca uma turma por codigo", 
			description = "Recebe o codigo de uma turma na URL e retorna os dados detalhados. Se o codigo não existir, a operação é barrada."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "turma encontrada e retornado com sucesso."),
			@ApiResponse(responseCode = "404", description = "turma não encontrada com o codigo informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL (ex: uma letra no lugar de um número).")
		})
	
	public TurmaResponseDTO buscarTurmaPorCodigo(String codigo) {
		
		Turma turma = turmaRepo.findBycodigoTurma(codigo)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhuma turma com o codigo " + codigo));
		
		return new TurmaResponseDTO(turma);
	}
	
	@Operation(
			summary = "Cadastra novas turmas", 
			description = "Recebe uma lista de turmas (JSON) e salva todos de uma vez no banco de dados. Retorna a lista de turmas recém-criadas com seus respectivos IDs."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "turmas cadastradas com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro de validação (ex: nome em branco ou dados mal formatados no JSON).")
		})
	
	public List<TurmaResponseDTO> criarTurmas(List<TurmaRequestDTO> turmasReq) {
		List<Turma> turmaPraSalvar = new ArrayList<>();
		List<TurmaResponseDTO> turmasDTO = new ArrayList<TurmaResponseDTO>();
		
		for(TurmaRequestDTO turmaDTO : turmasReq) {
			turmaPraSalvar.add(new Turma(turmaDTO));
		}
		
		List<Turma> turmasSalvos = turmaRepo.saveAll(turmaPraSalvar);
		
		for(Turma turma : turmasSalvos) {
			turmasDTO.add(new TurmaResponseDTO(turma));
		}
		
		return turmasDTO;	
	}
	
	@Operation(
			summary = "Atualiza os dados de uma turma", 
			description = "Substitui as informações de um aluno existente pelos novos dados fornecidos no corpo da requisição."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "turma atualizada com sucesso."),
			@ApiResponse(responseCode = "404", description = "turma não encontrada com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados ou tipo inválido na URL.")
		})
	
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
	
	@Operation(
			summary = "Remove uma turma do sistema", 
			description = "Deleta permanentemente um aluno do banco de dados utilizando o seu ID. Retorna um status 204 (Sem conteúdo) em caso de sucesso."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "turma deletada com sucesso."),
			@ApiResponse(responseCode = "404", description = "Não é possível deletar, turma não encontrada com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL.")
		})
	
	public void deletarTurma(Integer id) {

		Turma turmaExistente = turmaRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não é possível deletar. Turma com id " + id + " não encontrado."));

		turmaRepo.delete(turmaExistente);
	}
}