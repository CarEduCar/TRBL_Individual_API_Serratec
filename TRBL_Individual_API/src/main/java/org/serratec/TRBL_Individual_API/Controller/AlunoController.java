package org.serratec.TRBL_Individual_API.Controller;

import java.util.List;

import org.serratec.TRBL_Individual_API.RequestDTO.AlunoRequestDTO;
import org.serratec.TRBL_Individual_API.ResponseDTO.AlunoResponseDTO;
import org.serratec.TRBL_Individual_API.Service.AlunoService;
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
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	AlunoService service;
	
	@Operation(
			summary = "Lista todos os Alunos", 
			description = "Lista todos os Alunos cadastrados no Banco. Caso não haja nenhum aluno cadastrado, retorna uma mensagem de erro"
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrou pelo menos um aluno e retornou todos os que achou"),
			@ApiResponse(responseCode = "404", description = "Não encontrou ninguém cadastrado")
		})
	
	@GetMapping()
	public ResponseEntity<List<AlunoResponseDTO>> todosAlunos(){
		List<AlunoResponseDTO> listaAlunos = service.buscarTodosAlunos();
		return ResponseEntity.status(HttpStatus.OK).body(listaAlunos);
	}
			
	@Operation(
			summary = "Busca um Aluno por ID", 
			description = "Recebe o ID de um aluno na URL e retorna os dados detalhados dele. Se o ID não existir, a operação é barrada."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Aluno encontrado e retornado com sucesso."),
			@ApiResponse(responseCode = "404", description = "Aluno não encontrado com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL (ex: uma letra no lugar de um número).")
		})		
	
	@GetMapping("/{id}")
	public ResponseEntity<AlunoResponseDTO> AcharAlunoId(@PathVariable Integer id){
		AlunoResponseDTO Aluno = service.buscarAlunoPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(Aluno);
	}
	
	@Operation(
			summary = "Cadastra novos Alunos", 
			description = "Recebe uma lista de alunos (JSON) e salva todos de uma vez no banco de dados. Retorna a lista de alunos recém-criados com seus respectivos IDs."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Alunos cadastrados com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro de validação (ex: nome em branco ou dados mal formatados no JSON).")
		})
	
	@PostMapping()
	public ResponseEntity<List<AlunoResponseDTO>> CriarAluno(@RequestBody List<AlunoRequestDTO> aluno){
		List<AlunoResponseDTO> alunosDTO = service.criarAlunos(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunosDTO);
	}
	
	@Operation(
			summary = "Atualiza os dados de um Aluno", 
			description = "Substitui as informações de um aluno existente pelos novos dados fornecidos no corpo da requisição."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso."),
			@ApiResponse(responseCode = "404", description = "Aluno não encontrado com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados ou tipo inválido na URL.")
		})
	
	@PutMapping("/{id}")
	public ResponseEntity<AlunoResponseDTO> AtualizarAluno(@PathVariable Integer id, @RequestBody AlunoRequestDTO aluno){
		AlunoResponseDTO Aluno = service.atualizarAluno(id, aluno);
		return ResponseEntity.status(HttpStatus.OK).body(Aluno);
	}
	
	@Operation(
			summary = "Remove um Aluno do sistema", 
			description = "Deleta permanentemente um aluno do banco de dados utilizando o seu ID. Retorna um status 204 (Sem conteúdo) em caso de sucesso."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Aluno deletado com sucesso."),
			@ApiResponse(responseCode = "404", description = "Não é possível deletar, aluno não encontrado com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL.")
		})
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> ApagarAlunoId(@PathVariable Integer id){
		service.deletarAluno(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
