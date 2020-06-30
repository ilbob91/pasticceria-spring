package it.dstech.formazione.services;

import java.util.List;

import it.dstech.formazione.models.Ordinazione;

public interface OrdinazioneServiceDAO {

	Ordinazione add(Ordinazione cliente);

	List<Ordinazione> findAll();

	void remove(Ordinazione cliente);

	Ordinazione edit(Ordinazione cliente);

	Ordinazione findById(Long Id);
}
