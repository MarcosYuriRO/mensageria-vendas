package com.marcos.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcos.vendas.domain.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {
	
}
