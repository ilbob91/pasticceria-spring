package it.dstech.formazione.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dstech.formazione.models.Ordinazione;

public interface OrdinazioneRepository extends JpaRepository<Ordinazione, Long> {

	List<Ordinazione> findByOrderByConsegnaAsc();
}
