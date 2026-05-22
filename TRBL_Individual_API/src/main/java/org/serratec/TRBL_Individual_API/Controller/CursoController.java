package org.serratec.TRBL_Individual_API.Controller;

import java.util.List;

import org.serratec.TRBL_Individual_API.RequestDTO.CursoRequestDTO;
import org.serratec.TRBL_Individual_API.ResponseDTO.CursoResponseDTO;
import org.serratec.TRBL_Individual_API.Service.CursoService;
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
@RequestMapping("/curso")
public class CursoController {
	@Autowired
	CursoService service;
	
	@Operation(
			summary = "Lista todos os cursos", 
			description = "Lista todos os cursos cadastrados no Banco. Caso não haja nenhum curso cadastrado, retorna uma mensagem de erro"
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrou pelo menos um curso e retorna todos os que achou"),
			@ApiResponse(responseCode = "404", description = "Não encontrou nenhum curso cadastrado")
		})
	
	
	@GetMapping()
	public ResponseEntity<List<CursoResponseDTO>> todoCursos(){
		List<CursoResponseDTO> listaCursos = service.buscarTodosCursos();
		return ResponseEntity.status(HttpStatus.OK).body(listaCursos);
	}
	
	@Operation(
			summary = "Busca um curso por id", 
			description = "Recebe o id de um curso na URL e retorna os dados detalhados dele. Se o ID não existir, a operação é barrada."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "curso encontrado e retornado com sucesso."),
			@ApiResponse(responseCode = "404", description = "curso não encontrado com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL (ex: uma letra no lugar de um número).")
		})
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CursoResponseDTO> AcharCursoId(@PathVariable Integer id){
		CursoResponseDTO Curso = service.buscarCursoPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(Curso);
	}
	
	@Operation(
			summary = "Busca um curso por nome", 
			description = "Recebe o nome de um curso na URL e retorna os dados detalhados dele. Se o nome não existir, a operação é barrada."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "curso encontrado e retornado com sucesso."),
			@ApiResponse(responseCode = "404", description = "curso não encontrado com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL (ex: uma letra no lugar de um número).")
		})
	
	@GetMapping("/{nome}")
	public ResponseEntity<CursoResponseDTO> AcharCursonome(@PathVariable String nome){
		CursoResponseDTO Curso = service.buscarCursoPorNome(nome);
		return ResponseEntity.status(HttpStatus.OK).body(Curso);
	}
	
	@Operation(
			summary = "Cadastra novos cursos", 
			description = "Recebe uma lista de cursos (JSON) e salva todos de uma vez no banco de dados. Retorna a lista de cursos recém-criados com seus respectivos IDs."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "cursos cadastrados com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro de validação (ex: nome em branco ou dados mal formatados no JSON).")
		})
	
	
	@PostMapping()
	public ResponseEntity<List<CursoResponseDTO>> CriarCurso(@RequestBody List<CursoRequestDTO> curso){
		List<CursoResponseDTO> cursosDTO = service.criarCursos(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursosDTO);
	}
	
	@Operation(
			summary = "Atualiza os dados de um curso", 
			description = "Substitui as informações de um aluno existente pelos novos dados fornecidos no corpo da requisição."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "curso atualizado com sucesso."),
			@ApiResponse(responseCode = "404", description = "curso não encontrado com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados ou tipo inválido na URL.")
		})
	
	@PutMapping("/{id}")
	public ResponseEntity<CursoResponseDTO> AtualizarCurso(@RequestBody Integer id, CursoRequestDTO curso){
		CursoResponseDTO Curso = service.atualizarCurso(id, curso);
		return ResponseEntity.status(HttpStatus.OK).body(Curso);
	}
	
	@Operation(
			summary = "Remove um Curso do sistema", 
			description = "Deleta permanentemente um aluno do banco de dados utilizando o seu ID. Retorna um status 204 (Sem conteúdo) em caso de sucesso."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Curso deletado com sucesso."),
			@ApiResponse(responseCode = "404", description = "Não é possível deletar, curso não encontrado com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL.")
		})
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> ApagarCursoId(@PathVariable Integer id){
		service.deletarCurso(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
