package it.dstech.formazione.services;

import java.util.List;

import it.dstech.formazione.models.Dolce;

public interface DolceServiceDAO {

	Dolce add(Dolce cliente);

	List<Dolce> findAll();

	void remove(Dolce cliente);

	Dolce edit(Dolce cliente);

	Dolce findById(Long Id);
}
