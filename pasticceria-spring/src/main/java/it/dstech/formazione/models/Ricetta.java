package it.dstech.formazione.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ricetta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String tempo;
	private int difficolta;
	@OneToMany(mappedBy = "ingredienti")
	private List<Ingredienti> listaIngredienti;
	private String descrizione;
	private double costo;

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

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public int getDifficolta() {
		return difficolta;
	}

	public void setDifficolta(int difficolta) {
		this.difficolta = difficolta;
	}

	public List<Ingredienti> getListaIngredienti() {
		return listaIngredienti;
	}

	public void setListaIngredienti(List<Ingredienti> listaIngredienti) {
		this.listaIngredienti = listaIngredienti;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto() {
		double somma = 0;
		for (Ingredienti ingredienti : listaIngredienti) {
			somma += ingredienti.getCosto();
		}
		this.costo = somma + (somma * 0.1);
	}
}
