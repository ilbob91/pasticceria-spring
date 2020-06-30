package it.dstech.formazione.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dstech.formazione.models.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

}
