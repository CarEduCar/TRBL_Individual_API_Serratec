package org.serratec.TRBL_Individual_API.Exception;

public class ValorNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ValorNaoEncontradoException(String message) {
		super(message);
	}
}
