package com.paulo.jogogourmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulo.jogogourmet.entity.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {

	List<Prato> findByCategoriaId(Long id);

}
