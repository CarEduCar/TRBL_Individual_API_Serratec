package org.serratec.TRBL_Individual_API.Service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.TRBL_Individual_API.Domain.Aluno;
import org.serratec.TRBL_Individual_API.Exception.ValorNaoEncontradoException;
import org.serratec.TRBL_Individual_API.Repository.AlunoRepository;
import org.serratec.TRBL_Individual_API.Repository.PerfilSocialRepository;
import org.serratec.TRBL_Individual_API.RequestDTO.AlunoRequestDTO;
import org.serratec.TRBL_Individual_API.ResponseDTO.AlunoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepo;
	
	@Autowired
	private PerfilSocialRepository perfilRepo;
	
	
	@Operation(
			summary = "Lista todos os Alunos", 
			description = "Lista todos os Alunos cadastrados no Banco. Caso não haja nenhum aluno cadastrado, retorna uma mensagem de erro"
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrou pelo menos um aluno e retornou todos os que achou"),
			@ApiResponse(responseCode = "404", description = "Não encontrou ninguém cadastrado")
		})
	
	public List<AlunoResponseDTO> buscarTodosAlunos(){
		
		List<AlunoResponseDTO> alunosDTO = new ArrayList<AlunoResponseDTO>();
		List<Aluno> alunos = alunoRepo.findAll();
		
		for(Aluno aluno : alunos) {
			alunosDTO.add(new AlunoResponseDTO(aluno));
		}
		
		if(alunosDTO.isEmpty()) {
			throw new ValorNaoEncontradoException("Nenhum aluno cadastro no sistema!");
		}	
		return alunosDTO;
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
	
	public AlunoResponseDTO buscarAlunoPorId(Integer id) {
		Aluno aluno = alunoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum aluno com id "+ id));
		
		AlunoResponseDTO alunoDTO = new AlunoResponseDTO(aluno);
		return alunoDTO;
	}
	
	@Operation(
			summary = "Cadastra novos Alunos", 
			description = "Recebe uma lista de alunos (JSON) e salva todos de uma vez no banco de dados. Retorna a lista de alunos recém-criados com seus respectivos IDs."
		)
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Alunos cadastrados com sucesso."),
			@ApiResponse(responseCode = "400", description = "Erro de validação (ex: nome em branco ou dados mal formatados no JSON).")
		})
	
	public List<AlunoResponseDTO> criarAlunos(List<AlunoRequestDTO> alunosReq) {
		List<Aluno> alunosPraSalvar = new ArrayList<>();
		List<AlunoResponseDTO> alunosDTO = new ArrayList<AlunoResponseDTO>();
		
		for(AlunoRequestDTO alunoDTO : alunosReq) {
			alunosPraSalvar.add(new Aluno(alunoDTO));
		}
		
		List<Aluno> alunosSalvos = alunoRepo.saveAll(alunosPraSalvar);
		
		for(Aluno aluno : alunosSalvos) {
			alunosDTO.add(new AlunoResponseDTO(aluno));
		}
		
		return alunosDTO;	
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
	
	public AlunoResponseDTO atualizarAluno(Integer id, AlunoRequestDTO alunoDTO) {
		
		Aluno aluno = alunoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não existe nenhum aluno com o id " + id));
		
		aluno.setNomeAluno(alunoDTO.getNome());
		aluno.setDataNasc(alunoDTO.getDataNasc());
		
		Aluno alunoAtualizado = alunoRepo.save(aluno);
		
		return new AlunoResponseDTO(alunoAtualizado);
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
	
	public void deletarAluno(Integer id) {

		Aluno alunoExistente = alunoRepo.findById(id)
				.orElseThrow(() -> new ValorNaoEncontradoException("Não é possível deletar. Aluno com id " + id + " não encontrado."));

		alunoRepo.delete(alunoExistente);
	}
}
