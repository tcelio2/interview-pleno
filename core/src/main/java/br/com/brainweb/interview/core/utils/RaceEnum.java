package br.com.brainweb.interview.core.utils;

public enum RaceEnum {

	HUMAN("HUMAN"),
	ALIEN("ALIEN"),
	DIVINE("DIVINE"),
	CYBORG("CYBORG");
	
	private String tipo;
	
	RaceEnum(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}
