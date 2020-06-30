package it.dstech.formazione.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dstech.formazione.models.Ricetta;

public interface RicettaRepository extends JpaRepository<Ricetta, Long> {

}
