package com.farawaybr.frete.domain;

public class Imposto extends SuperEntity {

	private final Icms00 icms00;
	private final Icms20 icms20;
	private final Icms45 icms45;
	private final Icms60 icms60;
	private final Icms90 icms90;
	private final IcmsOutraUf icmsOutraUf;
	private final IcmsSn icmsSn;
	private final IcmsUfFim icmsUfFim;

	public Imposto() {
		this(null, null, null, null, null, null, null, null, null);
	}

	public Imposto(Long id, Icms00 icms00, Icms20 icms20, Icms45 icms45, Icms60 icms60, Icms90 icms90,
			IcmsOutraUf icmsOutraUf, IcmsSn icmsSn, IcmsUfFim icmsUfFim) {
		super(id);
		this.icms00 = icms00;
		this.icms20 = icms20;
		this.icms45 = icms45;
		this.icms60 = icms60;
		this.icms90 = icms90;
		this.icmsOutraUf = icmsOutraUf;
		this.icmsSn = icmsSn;
		this.icmsUfFim = icmsUfFim;
	}

	public Icms00 getIcms00() {
		return icms00;
	}

	public Icms20 getIcms20() {
		return icms20;
	}

	public Icms45 getIcms45() {
		return icms45;
	}

	public Icms60 getIcms60() {
		return icms60;
	}

	public Icms90 getIcms90() {
		return icms90;
	}

	public IcmsOutraUf getIcmsOutraUf() {
		return icmsOutraUf;
	}

	public IcmsSn getIcmsSn() {
		return icmsSn;
	}

	public IcmsUfFim getIcmsUfFim() {
		return icmsUfFim;
	}

}
