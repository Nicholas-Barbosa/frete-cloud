package com.farawaybr.frete.domain;

public enum Modal {
	Rodoviário("01"), Aéreo("02"), Aquaviário("03"), Ferroviário("04"), Dutoviário("05"), Multimodal("06");

	private final String modal;

	private Modal(String modal) {
		this.modal = modal;
	}

	public String getModal() {
		return modal;
	}
}
