package it.dstech.formazione.services;

import java.util.List;

import it.dstech.formazione.models.Ricetta;

public interface RicettaServiceDAO {

	Ricetta add(Ricetta ricetta);

	List<Ricetta> findAll();

	void remove(Ricetta ricetta);

	Ricetta edit(Ricetta ricetta);

	Ricetta findById(Long id);
	
	List<Ricetta> findPresenti();
}
