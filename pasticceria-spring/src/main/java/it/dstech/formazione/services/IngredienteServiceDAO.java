package it.dstech.formazione.services;

import java.util.List;

import it.dstech.formazione.models.Ingrediente;

public interface IngredienteServiceDAO {

	Ingrediente add(Ingrediente ingrediente);

	List<Ingrediente> findAll();

	void remove(Ingrediente ingrediente);

	Ingrediente edit(Ingrediente ingrediente);

	Ingrediente findById(Long Id);
}
