package com.paulo.jogogourmet.service;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.jogogourmet.entity.Categoria;
import com.paulo.jogogourmet.entity.Prato;
import com.paulo.jogogourmet.repository.PratoRepository;

@Service
public class PratoService {

	private static final Integer SIM = 0;
	
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

	public boolean encontrarPrato(Categoria categoria) {
		boolean encontrouPrato = false;
		Iterator<Prato> pratos = this.buscarPorCategoria(categoria).iterator();
		while (pratos.hasNext()) {
			Prato prato = pratos.next();
			Integer respostaPrato = JOptionPane.showConfirmDialog(null, "Você pensou em: " + prato.getNome() + "?", "Você pensou em", JOptionPane.YES_NO_OPTION);
			if (respostaPrato == SIM) {
				encontrouPrato = true;
				break;
			}
		}
		return encontrouPrato;
	}

	public void cadastrarPrato(Categoria categoria) {
		String nomeDoPrato = JOptionPane.showInputDialog("Qual o prato que você pensou?");
		Prato prato = new Prato(nomeDoPrato, categoria);
		this.salvar(prato);
	}
}
