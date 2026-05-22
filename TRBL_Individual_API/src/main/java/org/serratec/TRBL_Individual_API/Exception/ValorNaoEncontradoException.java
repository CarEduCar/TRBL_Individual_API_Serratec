package org.serratec.TRBL_Individual_API.Exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Handler de erro personalizado, para quando não encontrar nenhum da busca no banco de dados")
public class ValorNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ValorNaoEncontradoException(String message) {
		super(message);
	}
}
