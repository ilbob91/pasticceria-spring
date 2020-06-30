package it.dstech.formazione.services;

import java.util.List;

import it.dstech.formazione.models.Ingrediente;

public interface IngredienteServiceDAO {

	Ingrediente add(Ingrediente cliente);

	List<Ingrediente> findAll();

	void remove(Ingrediente cliente);

	Ingrediente edit(Ingrediente cliente);

	Ingrediente findById(Long Id);
}
