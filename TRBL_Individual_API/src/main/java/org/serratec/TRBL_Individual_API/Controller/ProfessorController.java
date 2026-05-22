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

@RestController
@Validated
@RequestMapping("/professor")
public class ProfessorController {
	@Autowired
	ProfessorService service;
	
	@GetMapping()
	public ResponseEntity<List<ProfessorResponseDTO>> todoProfessores(){
		List<ProfessorResponseDTO> listaProfessores = service.buscarTodosProfessores();
		return ResponseEntity.status(HttpStatus.OK).body(listaProfessores);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProfessorResponseDTO> AcharProfessorId(@PathVariable Integer id){
		ProfessorResponseDTO Professor = service.buscarProfessorPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(Professor);
	}
	
	@PostMapping()
	public ResponseEntity<List<ProfessorResponseDTO>> CriarProfessor(@RequestBody List<ProfessorRequestDTO> professor){
		List<ProfessorResponseDTO> professoresDTO = service.criarProfessors(professor);
		return ResponseEntity.status(HttpStatus.CREATED).body(professoresDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProfessorResponseDTO> AtualizarProfessor(@RequestBody Integer id, ProfessorRequestDTO professor){
		ProfessorResponseDTO Professor = service.atualizarProfessor(id, professor);
		return ResponseEntity.status(HttpStatus.OK).body(Professor);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> ApagarProfessorId(@PathVariable Integer id){
		service.deletarProfessor(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
