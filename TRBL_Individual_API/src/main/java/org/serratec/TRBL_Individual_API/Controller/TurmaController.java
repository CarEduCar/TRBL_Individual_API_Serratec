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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/turma")
public class TurmaController {
	@Autowired
	TurmaService service;
	
	@GetMapping()
	public ResponseEntity<List<TurmaResponseDTO>> todoTurmaes(){
		List<TurmaResponseDTO> listaTurmaes = service.buscarTodasTurmas();
		return ResponseEntity.status(HttpStatus.OK).body(listaTurmaes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TurmaResponseDTO> AcharTurmaId(@PathVariable Integer id){
		TurmaResponseDTO Turma = service.buscarTurmaPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(Turma);
	}
	
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<TurmaResponseDTO> AcharTurmaId(@PathVariable String codigo){
		TurmaResponseDTO Turma = service.buscarTurmaPorCodigo(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(Turma);
	}
	
	@PostMapping()
	public ResponseEntity<List<TurmaResponseDTO>> CriarTurma(@RequestBody List<TurmaRequestDTO> turma){
		List<TurmaResponseDTO> turmaesDTO = service.criarTurmas(turma);
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaesDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TurmaResponseDTO> AtualizarTurma(@RequestBody Integer id, TurmaRequestDTO turma){
		TurmaResponseDTO Turma = service.atualizarTurma(id, turma);
		return ResponseEntity.status(HttpStatus.OK).body(Turma);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> ApagarTurmaId(@PathVariable Integer id){
		service.deletarTurma(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
