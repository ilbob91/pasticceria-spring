package it.dstech.formazione.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Dolce {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private int quantita;
	private double costo;
	@OneToMany
	private List<Ricetta> listaRicetta;

	public void setCosto(double costo) {
		this.costo = costo;
	}

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

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto() {
		double somma = 0;
		List<Ingrediente> lista = this.listaRicetta.get(0).getListaIngredienti();
		for (Ingrediente ingredienti : lista) {
			somma += ingredienti.getCosto();
		}
		this.costo = somma + somma * 0.2;
	}

	public List<Ricetta> getListaRicetta() {
		return listaRicetta;
	}

	public void setListaRicetta(List<Ricetta> listaRicetta) {
		this.listaRicetta = listaRicetta;
	}

	

}
