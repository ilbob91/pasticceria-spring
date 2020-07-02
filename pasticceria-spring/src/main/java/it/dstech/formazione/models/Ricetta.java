package it.dstech.formazione.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Ricetta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private String tempo;
	private int difficolta;
	@ManyToMany
	@JoinTable(name = "ricetta_ingrediente", joinColumns = @JoinColumn(name = "ricetta_id"), inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))

	private List<Ingrediente> listaIngredienti;
	private String descrizione;
	private double costo;
	@ManyToMany(mappedBy = "ricetta")
	private List<Dolce> dolce;

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

	public List<Ingrediente> getListaIngredienti() {
		return listaIngredienti;
	}

	public void setListaIngredienti(List<Ingrediente> listaIngredienti) {
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

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void trovaCosto() {
		double somma = 0;
		for (Ingrediente ingredienti : listaIngredienti) {
			somma += ingredienti.getCosto();
		}
		this.costo = somma + somma * 0.1;
	}

	public List<Dolce> getDolce() {
		return dolce;
	}

	public void setDolce(List<Dolce> dolce) {
		this.dolce = dolce;
	}

	@Override
	public String toString() {
		return "Ricetta [id=" + id + ", nome=" + nome + ", tempo=" + tempo + "]";
	}

}
