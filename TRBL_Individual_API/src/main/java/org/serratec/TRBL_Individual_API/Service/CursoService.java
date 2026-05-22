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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepo;
	
	@Operation(
			summary = "Lista todos os cursos", 
			description = "Lista todos os cursos cadastrados no Banco. Caso não haja nenhum curso cadastrado, retorna uma mensagem de erro"
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrou pelo menos um curso e retorna todos os que achou"),
			@ApiResponse(responseCode = "404", description = "Não encontrou nenhum curso cadastrado")
		})
	
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
	
	@Operation(
			summary = "Busca um curso por id", 
			description = "Recebe o id de um curso na URL e retorna os dados detalhados dele. Se o ID não existir, a operação é barrada."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "curso encontrado e retornado com sucesso."),
			@ApiResponse(responseCode = "404", description = "curso não encontrado com o ID informado."),
			@ApiResponse(responseCode = "400", description = "Tipo de dado inválido fornecido na URL (ex: uma letra no lugar de um número).")
		})
	
	public CursoResponseDTO buscarCursoPorId(Integer id) {
		
		Curso curso = cursoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum curso com o id " + id));
		
		return new CursoResponseDTO(curso);
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
	
	public CursoResponseDTO buscarCursoPorNome(String nomeCurso) {
		
		Curso curso = cursoRepo.findBynomeCurso(nomeCurso)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum curso com o nome " + nomeCurso));
		
		return new CursoResponseDTO(curso);
	}
	
	@Operation(
			summary = "Cadastra novos cursos", 
			description = "Recebe uma lista de cursos (JSON) e salva todos de uma vez no banco de dados. Retorna a lista de cursos recém-criados com seus respectivos IDs."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "cursos cadastrados com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro de validação (ex: nome em branco ou dados mal formatados no JSON).")
		})
	
	public List<CursoResponseDTO> criarCursos(List<CursoRequestDTO> CursosReq) {
		List<Curso> cursoPraSalvar = new ArrayList<>();
		List<CursoResponseDTO> CursosDTO = new ArrayList<CursoResponseDTO>();
		
		for(CursoRequestDTO cursoDTO : CursosReq) {
			cursoPraSalvar.add(new Curso(cursoDTO));
		}
		
		List<Curso> CursosSalvos = cursoRepo.saveAll(cursoPraSalvar);
		
		for(Curso curso : CursosSalvos) {
			CursosDTO.add(new CursoResponseDTO(curso));
		}
		
		return CursosDTO;	
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
	
	
	public CursoResponseDTO atualizarCurso(Integer id, CursoRequestDTO cursoDTO) {
		
		Curso curso = cursoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum Curso com o id " + id));
		
		curso.setNomeCurso(cursoDTO.getNome());
		curso.setDataInicio(cursoDTO.getDataInicio());
		curso.setDataFim(cursoDTO.getDataFim());
		curso.setDescricaoCurso(cursoDTO.getDescricao());

		
		Curso cursoAtualizado = cursoRepo.save(curso);
		
		return new CursoResponseDTO(cursoAtualizado);
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
	
	public void deletarCurso(Integer id) {

		Curso cursoExistente = cursoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não é possível deletar. Curso com id " + id + " não encontrado."));

		cursoRepo.delete(cursoExistente);
	}
}