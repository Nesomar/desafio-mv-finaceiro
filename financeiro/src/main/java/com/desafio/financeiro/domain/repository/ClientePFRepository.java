package com.desafio.financeiro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.desafio.financeiro.domain.entity.ClientePF;

@Repository
public interface ClientePFRepository extends JpaRepository<ClientePF, Long>, JpaSpecificationExecutor<ClientePF> {

}
