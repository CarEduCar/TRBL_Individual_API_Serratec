package org.serratec.TRBL_Individual_API.Controller;

import java.util.List;

import org.serratec.TRBL_Individual_API.RequestDTO.TurmaRequestDTO;
import org.serratec.TRBL_Individual_API.ResponseDTO.TurmaResponseDTO;
import org.serratec.TRBL_Individual_API.Service.TurmaService;
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
import jakarta.websocket.server.PathParam;

@RestController
@Validated
@RequestMapping("/turma")
public class TurmaController {
	@Autowired
	TurmaService service;
	
	@Operation(
			summary = "Lista todos as turmas", 
			description = "Lista todos as turmas cadastrados no Banco. Caso não haja nenhuma turma cadastrada, retorna uma mensagem de erro"
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrou pelo menos uma turma e retorna todas as que achou"),
			@ApiResponse(responseCode = "404", description = "Não encontrou nenhuma turma cadastrado")
		})
	
	@GetMapping()
	public ResponseEntity<List<TurmaResponseDTO>> todoTurmas(){
		List<TurmaResponseDTO> listaTurmaes = service.buscarTodasTurmas();
		return ResponseEntity.status(HttpStatus.OK).body(listaTurmaes);
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
	
	@GetMapping("/{id}")
	public ResponseEntity<TurmaResponseDTO> AcharTurmaId(@PathVariable Integer id){
		TurmaResponseDTO Turma = service.buscarTurmaPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(Turma);
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
	
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<TurmaResponseDTO> AcharTurmaId(@PathVariable String codigo){
		TurmaResponseDTO Turma = service.buscarTurmaPorCodigo(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(Turma);
	}
	
	@Operation(
			summary = "Cadastra novas turmas", 
			description = "Recebe uma lista de turmas (JSON) e salva todos de uma vez no banco de dados. Retorna a lista de turmas recém-criadas com seus respectivos IDs."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "turmas cadastradas com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro de validação (ex: nome em branco ou dados mal formatados no JSON).")
		})
	
	@PostMapping()
	public ResponseEntity<List<TurmaResponseDTO>> CriarTurma(@RequestBody List<TurmaRequestDTO> turma){
		List<TurmaResponseDTO> turmaesDTO = service.criarTurmas(turma);
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaesDTO);
	}
	
	@Operation(
			summary = "Atualiza os dados de uma turma", 
			description = "Substitui as informações de uma turma existente pelos novos dados fornecidos no corpo da requisição."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "turma atualizada com sucesso."),
			@ApiResponse(responseCode = "404", description = "turma não encontrada com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados ou tipo inválido na URL.")
		})
	
	@PutMapping("/{id}")
	public ResponseEntity<TurmaResponseDTO> AtualizarTurma(@PathVariable Integer id, @RequestBody TurmaRequestDTO turma){
		TurmaResponseDTO Turma = service.atualizarTurma(id, turma);
		return ResponseEntity.status(HttpStatus.OK).body(Turma);
	}
	
	@Operation(
			summary = "Remove uma turma do sistema", 
			description = "Deleta permanentemente uma turma do banco de dados utilizando o seu ID. Retorna um status 204 (Sem conteúdo) em caso de sucesso."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "turma deletada com sucesso."),
			@ApiResponse(responseCode = "404", description = "Não é possível deletar, turma não encontrada com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL.")
		})
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> ApagarTurmaId(@PathVariable Integer id){
		service.deletarTurma(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
