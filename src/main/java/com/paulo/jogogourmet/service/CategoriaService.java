package com.paulo.jogogourmet.service;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.jogogourmet.entity.Categoria;
import com.paulo.jogogourmet.entity.Prato;
import com.paulo.jogogourmet.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private static final Integer SIM = 0;
	
	@Autowired private CategoriaRepository categoriaRepository;
	@Autowired private PratoService pratoService;

	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	public boolean encontrarCategoria() {
		boolean encontrouCategoria = false;
		Iterator<Categoria> categorias = this.listar().iterator();
		while (categorias.hasNext()) {
			Categoria categoria = categorias.next();
			Integer respostaCategoria = JOptionPane.showConfirmDialog(null, "O prato que você pensou é um(a): " + categoria.getNome() + "?", "O prato que você pensou", JOptionPane.YES_NO_OPTION);

			if (respostaCategoria == SIM) {
				encontrouCategoria = true;
				boolean encontrouPrato = false;
				
				encontrouPrato = pratoService.encontrarPrato(categoria);
				
				if (encontrouPrato) {
					JOptionPane.showMessageDialog(null, "Acertei de novo!");
				} else {
					pratoService.cadastrarPrato(categoria);
				}
				break;
			
			}
		}
		return encontrouCategoria;
	}
	
	public void cadastrarPratoECategoria() {
		String nomeDoPrato = JOptionPane.showInputDialog("Qual o prato que você pensou?");
		String nomeDaCategoria = JOptionPane.showInputDialog(nomeDoPrato + " é um ________?");
		Categoria novaCategoria = new Categoria(nomeDaCategoria);
		this.salvar(novaCategoria);
		Prato prato = new Prato(nomeDoPrato, novaCategoria);
		pratoService.salvar(prato);
	}
}
