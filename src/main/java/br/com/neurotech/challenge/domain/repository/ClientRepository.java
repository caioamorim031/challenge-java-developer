package br.com.neurotech.challenge.domain.repository;

import br.com.neurotech.challenge.domain.model.NeurotechClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<NeurotechClient, Long>{
}
