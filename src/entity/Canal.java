package entity;

import Enuns.TipoDeCanal;

public class Canal implements Comparable<Canal> {

	private String nome;
	private TipoDeCanal tipoDoCanal;
	private String linkOuCanal;
	private long id;

	public Canal(String nome, TipoDeCanal tipoDoCanal, String canalOuLink) {
		this.nome = nome;
		this.tipoDoCanal = tipoDoCanal;
		this.linkOuCanal = canalOuLink;
		this.id = System.currentTimeMillis();
	}

	public String toString() {
		return "Canal: " + this.nome + "\n" + "Tipo Do Canal: " + this.tipoDoCanal + "\n"
				+ "Link Ou Numero Do Canal: " + this.linkOuCanal + "\n" + "ID: " + this.id;
	}

	public boolean equals(Canal canal) {
		if (canal.getNome().equals(nome)) {
			return true;
		}
		return false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoDeCanal getTipoDoCanal() {
		return tipoDoCanal;
	}

	public void setTipoDoCanal(TipoDeCanal tipoDoCanal) {
		this.tipoDoCanal = tipoDoCanal;
	}

	public String getLinkOuCanal() {
		return linkOuCanal;
	}

	public void setLinkOuCanal(String linkOuCanal) {
		this.linkOuCanal = linkOuCanal;
	}

	public long getId() {
		return id;
	}

	@Override
	public int compareTo(Canal canal) {
		return nome.compareTo(canal.getNome());
	}
}
