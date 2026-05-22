package org.serratec.TRBL_Individual_API.Controller;

import java.util.List;

import org.serratec.TRBL_Individual_API.RequestDTO.ProfessorRequestDTO;
import org.serratec.TRBL_Individual_API.ResponseDTO.ProfessorResponseDTO;
import org.serratec.TRBL_Individual_API.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Validated
@RequestMapping("/professor")
public class ProfessorController {
	@Autowired
	ProfessorService service;
	
	@Operation(
			summary = "Lista todos os professores", 
			description = "Lista todos os professores cadastrados no Banco. Caso não haja nenhum professor cadastrado, retorna uma mensagem de erro"
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrou pelo menos um professor e retorna todos os que achou"),
			@ApiResponse(responseCode = "404", description = "Não encontrou ninguém cadastrado")
		})
	
	@GetMapping()
	public ResponseEntity<List<ProfessorResponseDTO>> todoProfessores(){
		List<ProfessorResponseDTO> listaProfessores = service.buscarTodosProfessores();
		return ResponseEntity.status(HttpStatus.OK).body(listaProfessores);
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
	
	@GetMapping("/{id}")
	public ResponseEntity<ProfessorResponseDTO> AcharProfessorId(@PathVariable Integer id){
		ProfessorResponseDTO Professor = service.buscarProfessorPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(Professor);
	}
	
	@Operation(
			summary = "Cadastra novos professores", 
			description = "Recebe uma lista de professores (JSON) e salva todos de uma vez no banco de dados. Retorna a lista de professores recém-criados com seus respectivos IDs."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "professores cadastrados com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro de validação (ex: nome em branco ou dados mal formatados no JSON).")
		})
	
	@PostMapping()
	public ResponseEntity<List<ProfessorResponseDTO>> CriarProfessor(@RequestBody List<ProfessorRequestDTO> Professor){
		List<ProfessorResponseDTO> professoresDTO = service.criarProfessores(Professor);
		return ResponseEntity.status(HttpStatus.CREATED).body(professoresDTO);
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
	
	@PutMapping("/{id}")
	public ResponseEntity<ProfessorResponseDTO> AtualizarProfessor(@RequestBody Integer id, ProfessorRequestDTO professor){
		ProfessorResponseDTO Professor = service.atualizarProfessor(id, professor);
		return ResponseEntity.status(HttpStatus.OK).body(Professor);
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> ApagarProfessorId(@PathVariable Integer id){
		service.deletarProfessor(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
