package it.dstech.formazione.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.formazione.models.Dolce;
import it.dstech.formazione.repositories.DolceRepository;

@Service
public class DolceServiceDAOImpl implements DolceServiceDAO {

	@Autowired
	private DolceRepository dolceRepo;

	@Override
	public Dolce add(Dolce dolce) {

		dolceRepo.save(dolce);
		return dolce;
	}

	@Override
	public List<Dolce> findAll() {
		return dolceRepo.findAll();
	}

	@Override
	public void remove(Dolce dolce) {
		dolceRepo.delete(dolce);
	}

	@Override
	public Dolce edit(Dolce dolce) {
		dolceRepo.save(dolce);
		return dolce;
	}

	@Override
	public Dolce findById(Long Id) {

		return dolceRepo.findById(Id).get();
	}

	@Override
	public List<Dolce> findMaggioreZero() {
		
		List<Dolce> listaMaggiore= new ArrayList<>();
		for (Dolce dolce : dolceRepo.findAll()) {
			
			if(dolce.getQuantita()>0) {
				listaMaggiore.add(dolce);
			}
			
		}
		return listaMaggiore;
	}

}
