package it.dstech.formazione.services;

import java.util.List;

import it.dstech.formazione.models.Ricetta;

public interface RicettaServiceDAO {

	Ricetta add(Ricetta cliente);

	List<Ricetta> findAll();

	void remove(Ricetta cliente);

	Ricetta edit(Ricetta cliente);

	Ricetta findById(Long Id);
}
