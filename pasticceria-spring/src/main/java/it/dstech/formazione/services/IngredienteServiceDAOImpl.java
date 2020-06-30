package it.dstech.formazione.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.formazione.models.Ingrediente;
import it.dstech.formazione.repositories.IngredienteRepository;
@Service
public class IngredienteServiceDAOImpl implements IngredienteServiceDAO {

	@Autowired
	private  IngredienteRepository ingreRepo;
	
	@Override
	public Ingrediente add(Ingrediente ingrediente) {
		ingreRepo.save(ingrediente);
		return ingrediente;
	}

	@Override
	public List<Ingrediente> findAll() {
		return ingreRepo.findAll();
	}

	@Override
	public void remove(Ingrediente ingrediente) {

		ingreRepo.delete(ingrediente);
		
	}

	@Override
	public Ingrediente edit(Ingrediente ingrediente) {
		ingreRepo.save(ingrediente);
		return ingrediente;
	}

	@Override
	public Ingrediente findById(Long Id) {
		
		return ingreRepo.findById(Id).get();
		
	}

}
