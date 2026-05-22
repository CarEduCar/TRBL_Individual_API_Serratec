package org.serratec.TRBL_Individual_API.Service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.TRBL_Individual_API.Domain.Curso;
import org.serratec.TRBL_Individual_API.Exception.ValorNaoEncontradoException;
import org.serratec.TRBL_Individual_API.Repository.CursoRepository;
import org.serratec.TRBL_Individual_API.RequestDTO.CursoRequestDTO;
import org.serratec.TRBL_Individual_API.ResponseDTO.CursoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepo;
	
	public List<CursoResponseDTO> buscarTodosCursos() {
		
		List<Curso> cursos = cursoRepo.findAll();
		List<CursoResponseDTO> cursosDTO = new ArrayList<>();

		if(cursos.isEmpty()) {
			throw new ValorNaoEncontradoException("Nenhum curso cadastrado no sistema!");
		}	
		
		for(Curso curso : cursos) {
			cursosDTO.add(new CursoResponseDTO(curso));
		}
		
		return cursosDTO;
	}
	
	public CursoResponseDTO buscarCursoPorId(Integer id) {
		
		Curso curso = cursoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum curso com o id " + id));
		
		return new CursoResponseDTO(curso);
	}
	
	public CursoResponseDTO buscarCursoPorNome(String nomeCurso) {
		
		Curso curso = cursoRepo.findBynomeCurso(nomeCurso)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum curso com o nome " + nomeCurso));
		
		return new CursoResponseDTO(curso);
	}
	
	
	public CursoResponseDTO atualizarCurso(Integer id, CursoRequestDTO cursoDTO) {
		
		Curso curso = cursoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum Curso com o id " + id));
		
		curso.setNomeCurso(cursoDTO.getNomeCurso());
		curso.setDataInicio(cursoDTO.getDataInicio());
		curso.setDataFim(cursoDTO.getDataFim());
		curso.setDescricaoCurso(cursoDTO.getDescricaoCurso());

		
		Curso cursoAtualizado = cursoRepo.save(curso);
		
		return new CursoResponseDTO(cursoAtualizado);
	}
	
	public void deletarCurso(Integer id) {

		Curso cursoExistente = cursoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não é possível deletar. Curso com id " + id + " não encontrado."));

		cursoRepo.delete(cursoExistente);
	}
}