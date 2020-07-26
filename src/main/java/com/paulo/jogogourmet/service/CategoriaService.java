package com.paulo.jogogourmet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.jogogourmet.entity.Categoria;
import com.paulo.jogogourmet.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired private CategoriaRepository categoriaRepository;

	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
}
