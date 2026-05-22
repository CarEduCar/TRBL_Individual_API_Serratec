package org.serratec.TRBL_Individual_API.Exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Handler de erro personalizado, para erros de já existir o mesmo valor no banco de dados")
public class ValorDuplicadoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ValorDuplicadoException(String message) {
		super(message);
	}
}
