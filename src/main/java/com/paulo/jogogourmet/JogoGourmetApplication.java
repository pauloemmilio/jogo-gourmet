package com.paulo.jogogourmet;

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

	private static final Integer NAO_CONTINUAR = 1;
	private static final String OPCAO_SIM = "SIM";
	private static final String OPCAO_NAO = "NÃO";
	
	public static void main(String[] args) {
		SpringApplication.run(JogoGourmetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.preparar();
		boolean encerrar = false;

		while (!encerrar) {
			JOptionPane.showMessageDialog(null, "Pense em um prato que você gosta");
			
			boolean encontrouCategoria = false;
			encontrouCategoria = categoriaService.encontrarCategoria();
			
			if (!encontrouCategoria)
				categoriaService.cadastrarPratoECategoria();
			
			Object[] opcoes = { OPCAO_SIM, OPCAO_NAO };
			int respostaEncerrar = JOptionPane.showOptionDialog(null, "Deseja continuar?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
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
