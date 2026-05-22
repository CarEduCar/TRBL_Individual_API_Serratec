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

@RestController
@Validated
@RequestMapping("/curso")
public class CursoController {
	@Autowired
	CursoService service;
	
	@GetMapping()
	public ResponseEntity<List<CursoResponseDTO>> todoCursos(){
		List<CursoResponseDTO> listaCursos = service.buscarTodosCursos();
		return ResponseEntity.status(HttpStatus.OK).body(listaCursos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CursoResponseDTO> AcharCursoId(@PathVariable Integer id){
		CursoResponseDTO Curso = service.buscarCursoPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(Curso);
	}
	
	@PostMapping()
	public ResponseEntity<List<CursoResponseDTO>> CriarCurso(@RequestBody List<CursoRequestDTO> curso){
		List<CursoResponseDTO> cursosDTO = service.criarCursos(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursosDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CursoResponseDTO> AtualizarCurso(@RequestBody Integer id, CursoRequestDTO curso){
		CursoResponseDTO Curso = service.atualizarCurso(id, curso);
		return ResponseEntity.status(HttpStatus.OK).body(Curso);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> ApagarCursoId(@PathVariable Integer id){
		service.deletarCurso(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
