package it.dstech.formazione.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.formazione.models.Cliente;
import it.dstech.formazione.models.Ordinazione;
import it.dstech.formazione.repositories.ClienteRepository;

@Service
public class ClienteServiceDAOImpl implements ClienteServiceDAO {
	@Autowired
	private ClienteRepository repo;

	@Override
	public Cliente add(Cliente cliente) {
		return repo.save(cliente);
	}

	@Override
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	@Override
	public void remove(Cliente cliente) {
		repo.delete(cliente);
	}

	@Override
	public Cliente edit(Cliente cliente) {
		cliente.setListaOrdinazioni(cliente.getListaOrdinazioni());
		return repo.save(cliente);
	}

	@Override
	public Cliente findByCognomeAndNome(String cognome, String nome) {
		return repo.findByCognomeAndNome(cognome, nome);
	}

	@Override
	public List<Ordinazione> addOrdine(Ordinazione ordinazione, Cliente cliente) {

		return null;
	}

	@Override
	public Cliente findById(Long id) {
		return findById(id);
	}

}
