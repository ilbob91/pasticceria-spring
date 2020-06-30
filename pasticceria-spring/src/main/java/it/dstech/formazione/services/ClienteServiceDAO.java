package it.dstech.formazione.services;

import java.util.List;

import it.dstech.formazione.models.Cliente;
import it.dstech.formazione.models.Ordinazione;

public interface ClienteServiceDAO {

	Cliente add(Cliente cliente);

	List<Cliente> findAll();

	void remove(Cliente cliente);

	Cliente edit(Cliente cliente);

	Cliente findById(Long Id);

	Cliente findByCognomeAndNome(String cognome, String nome);

	List<Ordinazione> addOrdine(Ordinazione ordinazione, Cliente cliente);
}
