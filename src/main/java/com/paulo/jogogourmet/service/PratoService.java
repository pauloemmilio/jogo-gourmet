package com.paulo.jogogourmet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.jogogourmet.entity.Categoria;
import com.paulo.jogogourmet.entity.Prato;
import com.paulo.jogogourmet.repository.PratoRepository;

@Service
public class PratoService {

	@Autowired private PratoRepository pratoRepository;
	
	public Prato salvar(Prato prato) {
		return pratoRepository.save(prato);
	}
	
	public List<Prato> listar(){
		return pratoRepository.findAll();
	}

	public List<Prato> buscarPorCategoria(Categoria categoria) {
		return pratoRepository.findByCategoriaId(categoria.getId());
	}
}
