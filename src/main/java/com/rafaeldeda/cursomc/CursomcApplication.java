package com.rafaeldeda.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rafaeldeda.cursomc.domain.Categoria;
import com.rafaeldeda.cursomc.domain.Cidade;
import com.rafaeldeda.cursomc.domain.Cliente;
import com.rafaeldeda.cursomc.domain.Endereco;
import com.rafaeldeda.cursomc.domain.Estado;
import com.rafaeldeda.cursomc.domain.Produto;
import com.rafaeldeda.cursomc.domain.enums.TipoCliente;
import com.rafaeldeda.cursomc.repositories.CategoriaRepository;
import com.rafaeldeda.cursomc.repositories.CidadeRepository;
import com.rafaeldeda.cursomc.repositories.ClienteRepository;
import com.rafaeldeda.cursomc.repositories.EnderecoRepository;
import com.rafaeldeda.cursomc.repositories.EstadoRepository;
import com.rafaeldeda.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 400.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","009.257.385-14",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("999988889","32312540"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","123","CASA", "Grageru", "49015110", cli1, c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","321","COMERCIO", "Centro", "88777000", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
