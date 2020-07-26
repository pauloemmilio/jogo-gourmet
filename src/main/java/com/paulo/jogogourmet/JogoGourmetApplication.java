package com.paulo.jogogourmet;

import java.util.Iterator;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paulo.jogogourmet.entity.Categoria;
import com.paulo.jogogourmet.entity.Prato;
import com.paulo.jogogourmet.service.CategoriaService;
import com.paulo.jogogourmet.service.PratoService;

@SpringBootApplication
public class JogoGourmetApplication implements CommandLineRunner {

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private PratoService pratoService;

	private static final Integer SIM = 0;
	private static final Integer NAO_CONTINUAR = 1;

	public static void main(String[] args) {
		SpringApplication.run(JogoGourmetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.preparar();
		boolean encerrar = false;

		while (!encerrar) {

			JOptionPane.showMessageDialog(null, "Pense em um prato que você gosta");

			Iterator<Categoria> categorias = categoriaService.listar().iterator();
			boolean encontrouCategoria = false;
			while (categorias.hasNext()) {
				Categoria categoria = categorias.next();
				Integer respostaCategoria = JOptionPane.showConfirmDialog(null,
						"O prato que você pensou é: " + categoria.getNome() + "?", "O prato que você pensou",
						JOptionPane.YES_NO_OPTION);

				if (respostaCategoria == SIM) {
					encontrouCategoria = true;
					boolean encontrouPrato = false;
					Iterator<Prato> pratos = pratoService.buscarPorCategoria(categoria).iterator();
					while (pratos.hasNext()) {
						Prato prato = pratos.next();
						Integer respostaPrato = JOptionPane.showConfirmDialog(null,
								"Você pensou em: " + prato.getNome() + "?", "Você pensou em",
								JOptionPane.YES_NO_OPTION);
						if (respostaPrato == SIM) {
							encontrouPrato = true;
							break;
						}
					}
					if (encontrouPrato) {
						JOptionPane.showMessageDialog(null, "Acertei de novo!");
					} else {
						String nomeDoPrato = JOptionPane.showInputDialog("Qual o prato que você pensou?");
						Prato prato = new Prato(nomeDoPrato, categoria);
						pratoService.salvar(prato);
					}
					break;
				}
			}
			if (!encontrouCategoria) {
				String nomeDoPrato = JOptionPane.showInputDialog("Qual o prato que você pensou?");
				String nomeDaCategoria = JOptionPane.showInputDialog(nomeDoPrato + " é um ________?");
				Categoria novaCategoria = new Categoria(nomeDaCategoria);
				categoriaService.salvar(novaCategoria);
				Prato prato = new Prato(nomeDoPrato, novaCategoria);
				pratoService.salvar(prato);
			}
			Object[] options = { "SIM", "NÃO" };
			int respostaEncerrar = JOptionPane.showOptionDialog(null, "Deseja continuar?", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (respostaEncerrar == NAO_CONTINUAR)
				encerrar = true;
		}
	}

	private void preparar() {
		Categoria categoria1 = new Categoria("massa");
		categoriaService.salvar(categoria1);
		Prato prato1 = new Prato("lasanha", categoria1);
		pratoService.salvar(prato1);

		Categoria categoria2 = new Categoria("salgado");
		categoriaService.salvar(categoria2);
		Prato prato2 = new Prato("coxinha", categoria2);
		pratoService.salvar(prato2);

		System.setProperty("java.awt.headless", "false");
	}

}
