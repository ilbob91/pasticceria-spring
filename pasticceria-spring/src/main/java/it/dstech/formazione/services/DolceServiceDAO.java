package it.dstech.formazione.services;

import java.util.List;

import it.dstech.formazione.models.Dolce;

public interface DolceServiceDAO {

	Dolce add(Dolce dolce);

	List<Dolce> findAll();
	
	List<Dolce> findMaggioreZero();

	void remove(Dolce dolce);

	Dolce edit(Dolce dolce);

	Dolce findById(Long Id);
}
