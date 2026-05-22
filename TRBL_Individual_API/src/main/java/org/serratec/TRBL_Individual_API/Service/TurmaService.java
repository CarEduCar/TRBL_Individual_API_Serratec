package org.serratec.TRBL_Individual_API.Service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.TRBL_Individual_API.Domain.Turma;
import org.serratec.TRBL_Individual_API.Exception.ValorNaoEncontradoException;
import org.serratec.TRBL_Individual_API.Repository.TurmaRepository;
import org.serratec.TRBL_Individual_API.ResponseDTO.TurmaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {
	
	@Autowired
	private TurmaRepository turmaRepo;
	
	public List<TurmaResponseDTO> buscarTodasTurmas() {
		
		List<Turma> turmas = turmaRepo.findAll();
		List<TurmaResponseDTO> turmasDTO = new ArrayList<>();
		

		if(turmas.isEmpty()) {
			throw new ValorNaoEncontradoException("Nenhuma turma cadastrada no sistema!");
		}	
		
		for(Turma turma : turmas) {
			turmasDTO.add(new TurmaResponseDTO(turma));
		}
		
		return turmasDTO;
	}
	
	public TurmaResponseDTO buscarTurmaPorId(Integer id) {
		
		Turma turma = turmaRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhuma turma com o id " + id));
		
		return new TurmaResponseDTO(turma);
	}
	
public TurmaResponseDTO buscarTurmaPorId(String codigo) {
		
		Turma turma = turmaRepo.findBycodigoTurma(codigo)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhuma turma com o codigo " + codigo));
		
		return new TurmaResponseDTO(turma);
	}
}