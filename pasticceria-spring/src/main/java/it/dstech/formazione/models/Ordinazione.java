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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Ordinazione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToMany(mappedBy="listaOrdinazioni")
	//@JoinTable(name = "ordinazione_cliente", joinColumns = @JoinColumn(name = "Ordinazione_id"), inverseJoinColumns = @JoinColumn(name = "Cliente_id"))

	private List<Cliente> cliente;

	@OneToMany
	private List<Dolce> listaDolci;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
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
		spesa = (spesa * sconto) / 100;
		this.costo = spesa;
	}

	public double getSconto() {
		return sconto;
	}

	public void setSconto(double sconto) {
		this.sconto = sconto;
	}

}
