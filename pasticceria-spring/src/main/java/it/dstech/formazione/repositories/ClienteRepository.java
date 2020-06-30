package it.dstech.formazione.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dstech.formazione.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByCognomeAndNome(String cognome, String nome);
}
