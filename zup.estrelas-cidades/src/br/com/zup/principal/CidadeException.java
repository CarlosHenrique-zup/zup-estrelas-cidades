package br.com.zup.principal;

public class CidadeException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public CidadeException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
