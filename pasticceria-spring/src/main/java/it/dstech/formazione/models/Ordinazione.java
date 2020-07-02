package it.dstech.formazione.models;

import java.time.LocalDateTime;
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
public class Ordinazione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToMany(mappedBy = "listaOrdinazioni")
	private List<Cliente> cliente;

	@ManyToMany
	@JoinTable(name = "ordinazione_dolce", joinColumns = @JoinColumn(name = "ordinazione_id"), inverseJoinColumns = @JoinColumn(name = "dolce_id"))
	private List<Dolce> listaDolci;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime consegna;
	private double costo;
	private double sconto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
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

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void trovaCosto() {

		double spesa = 0;
		for (Dolce dolce : this.listaDolci) {

			spesa = spesa + dolce.getCosto();
		}
		spesa = spesa - spesa * sconto / 100;
		this.costo = spesa;
	}

	public double getSconto() {
		return sconto;
	}

	public void setSconto(double sconto) {
		this.sconto = sconto;
	}

}
