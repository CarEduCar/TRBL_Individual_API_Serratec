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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository profRepo;
	
	@Operation(
			summary = "Lista todos os professores", 
			description = "Lista todos os professores cadastrados no Banco. Caso não haja nenhum professor cadastrado, retorna uma mensagem de erro"
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrou pelo menos um professor e retorna todos os que achou"),
			@ApiResponse(responseCode = "404", description = "Não encontrou ninguém cadastrado")
		})
	
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
	
	@Operation(
			summary = "Busca um professor por ID", 
			description = "Recebe o ID de um professor na URL e retorna os dados detalhados dele. Se o ID não existir, a operação é barrada."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "professor encontrado e retornado com sucesso."),
			@ApiResponse(responseCode = "404", description = "professor não encontrado com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL (ex: uma letra no lugar de um número).")
		})
	
	public ProfessorResponseDTO buscarProfessorPorId(Integer id) {
		Professor professor = profRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum Professor com id "+ id));
 
		ProfessorResponseDTO professorDTO = new ProfessorResponseDTO(professor);
		return professorDTO;
	}
	
	@Operation(
			summary = "Cadastra novos professores", 
			description = "Recebe uma lista de professores (JSON) e salva todos de uma vez no banco de dados. Retorna a lista de professores recém-criados com seus respectivos IDs."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "professores cadastrados com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro de validação (ex: nome em branco ou dados mal formatados no JSON).")
		})
	
	public List<ProfessorResponseDTO> criarProfessors(List<ProfessorRequestDTO> professoresReq) {
		List<Professor> professoresPraSalvar = new ArrayList<>();
		List<ProfessorResponseDTO> professoresDTO = new ArrayList<ProfessorResponseDTO>();
		
		for(ProfessorRequestDTO professorDTO : professoresReq) {
			professoresPraSalvar.add(new Professor(professorDTO));
		}
		
		List<Professor> professoresSalvos = profRepo.saveAll(professoresPraSalvar);
		
		for(Professor professor : professoresSalvos) {
			professoresDTO.add(new ProfessorResponseDTO(professor));
		}
		
		return professoresDTO;	
	}
	
	@Operation(
			summary = "Atualiza os dados de um professor", 
			description = "Substitui as informações de um aluno existente pelos novos dados fornecidos no corpo da requisição."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "professor atualizado com sucesso."),
			@ApiResponse(responseCode = "404", description = "professor não encontrado com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados ou tipo inválido na URL.")
		})
	
public ProfessorResponseDTO atualizarProfessor(Integer id, ProfessorRequestDTO professorDTO) {
		
		Professor professor = profRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum professor com o id " + id));
		
		professor.setNomeProfessor(professorDTO.getNomeProfessor());
		professor.setDataNasc(professorDTO.getDataNasc());
		
		Professor professorAtualizado = profRepo.save(professor);
		
		return new ProfessorResponseDTO(professorAtualizado);
	}

	@Operation(
		summary = "Remove um professor do sistema", 
		description = "Deleta permanentemente um aluno do banco de dados utilizando o seu ID. Retorna um status 204 (Sem conteúdo) em caso de sucesso."
	)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "professor deletado com sucesso."),
		@ApiResponse(responseCode = "404", description = "Não é possível deletar, professor não encontrado com o ID informado."),
		@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL.")
	})
		
	public void deletarProfessor(Integer id) {

		Professor professor = profRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não é possível deletar. Professor com id " + id + " não encontrado."));

		profRepo.delete(professor);
	}
}
