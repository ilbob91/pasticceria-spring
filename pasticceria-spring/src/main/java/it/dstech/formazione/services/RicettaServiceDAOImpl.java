package it.dstech.formazione.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.dstech.formazione.models.Ricetta;
import it.dstech.formazione.repositories.RicettaRepository;

@Service
public class RicettaServiceDAOImpl implements RicettaServiceDAO {

	@Autowired
	private RicettaRepository ricettaRepo;

	@Override
	public Ricetta add(Ricetta ricetta) {
		ricettaRepo.save(ricetta);
		return ricetta;
	}

	@Override
	public List<Ricetta> findAll() {
		return ricettaRepo.findAll();
	}

	@Override
	public void remove(Ricetta ricetta) {

		ricettaRepo.delete(ricetta);
	}

	@Override
	public Ricetta edit(Ricetta ricetta) {
		ricettaRepo.save(ricetta);
		return ricetta;
	}

	@Override
	public Ricetta findById(Long id) {
		return ricettaRepo.findById(id).get();
	}
	

}
