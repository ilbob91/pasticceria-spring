package it.dstech.formazione.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.formazione.models.Ordinazione;
import it.dstech.formazione.repositories.OrdinazioneRepository;

@Service
public class OrdinazioneServiceDAOImpl implements OrdinazioneServiceDAO {

	
	@Autowired
	private OrdinazioneRepository ordiRepo;
	@Override
	public Ordinazione add(Ordinazione ordinazione) {
		ordiRepo.save(ordinazione);
		return ordinazione;
	}

	@Override
	public List<Ordinazione> findAll() {
		return ordiRepo.findAll();
	}

	@Override
	public void remove(Ordinazione ordinazione) {

		ordiRepo.delete(ordinazione);
	}

	@Override
	public Ordinazione edit(Ordinazione ordinazione) {
		ordiRepo.save(ordinazione);
		return ordinazione;
	}

	@Override
	public Ordinazione findById(Long id) {
		return ordiRepo.findById(id).get();
	}

}
