package it.dstech.formazione.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ordinazione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Cliente cliente;
	@OneToMany(mappedBy = "dolce")
	private List<Dolce> listaDolci;
	private LocalDateTime consegna;
	private double costo;
	private double sconto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Dolce> getListaDolci() {
		return listaDolci;
	}

	public void setListaDolci(List<Dolce> listaDolci) {
		this.listaDolci = listaDolci;
	}

	public LocalDateTime getConsegna() {
		return consegna;
	}

	public void setConsegna(LocalDateTime consegna) {
		this.consegna = consegna;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto() {
		
		double spesa=0;
		for (Dolce dolce : this.listaDolci) {
			
			spesa=spesa+dolce.getCosto();			
		}		
		spesa=(spesa*sconto)/100;		
		this.costo = spesa;
	}

	public double getSconto() {
		return sconto;
	}

	public void setSconto(double sconto) {
		this.sconto = sconto;
	}

}
