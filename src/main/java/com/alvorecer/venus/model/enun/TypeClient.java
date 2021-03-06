package com.alvorecer.venus.model.enun;

public enum TypeClient {
	
	FISICA("Física", "CPF", "000.000.000-00"), 
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00");

	private String descricao;
	private String documento;
	private String mascara;

	TypeClient(String descricao, String documento, String mascara) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}
}
