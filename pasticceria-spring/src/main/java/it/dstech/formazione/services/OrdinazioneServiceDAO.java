package it.dstech.formazione.services;

import java.util.List;

import it.dstech.formazione.models.Ordinazione;

public interface OrdinazioneServiceDAO {

	Ordinazione add(Ordinazione ordinazione);

	List<Ordinazione> findAll();

	void remove(Ordinazione ordinazione);

	Ordinazione edit(Ordinazione ordinazione);

	Ordinazione findById(Long Id);
}
