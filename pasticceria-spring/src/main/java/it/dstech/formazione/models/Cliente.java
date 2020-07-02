package it.dstech.formazione.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	@ManyToMany
	@JoinTable(name = "cliente_ordinazione", joinColumns = @JoinColumn(name = "Cliente_id"), inverseJoinColumns = @JoinColumn(name = "Ordinazione_id"))
	private List<Ordinazione> listaOrdinazioni;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Ordinazione> getListaOrdinazioni() {
		return listaOrdinazioni;
	}

	public void setListaOrdinazioni(List<Ordinazione> listaOrdinazioni) {
		this.listaOrdinazioni = listaOrdinazioni;
	}

	public double costoTolale() {
		if (listaOrdinazioni == null) {
			return 0;
		} else {
			double costo = 0;
			for (Ordinazione ordinazione : listaOrdinazioni) {

				costo = costo + ordinazione.getCosto();
			}

			return costo;
		}
	}

}
