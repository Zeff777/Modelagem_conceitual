package com.danielsilva.modelagem_dados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danielsilva.modelagem_dados.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
