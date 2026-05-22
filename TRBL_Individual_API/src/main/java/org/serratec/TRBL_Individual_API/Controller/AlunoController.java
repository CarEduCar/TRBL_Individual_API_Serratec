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

@RestController
@Validated
@RequestMapping("/Aluno")
public class AlunoController {
	
	@Autowired
	AlunoService service;
	
	@GetMapping()
	public ResponseEntity<List<AlunoResponseDTO>> todosAlunos(){
		List<AlunoResponseDTO> listaAlunos = service.buscarTodosAlunos();
		return ResponseEntity.status(HttpStatus.OK).body(listaAlunos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlunoResponseDTO> AcharAlunoId(@PathVariable Integer id){
		AlunoResponseDTO Aluno = service.buscarAlunoPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(Aluno);
	}
	
	@PostMapping()
	public ResponseEntity<List<AlunoResponseDTO>> CriarAluno(@RequestBody List<AlunoRequestDTO> aluno){
		List<AlunoResponseDTO> alunosDTO = service.criarAlunos(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunosDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AlunoResponseDTO> AtualizarAluno(@RequestBody Integer id, AlunoRequestDTO aluno){
		AlunoResponseDTO Aluno = service.atualizarAluno(id, aluno);
		return ResponseEntity.status(HttpStatus.OK).body(Aluno);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> ApagarAlunoId(@PathVariable Integer id){
		service.deletarAluno(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
