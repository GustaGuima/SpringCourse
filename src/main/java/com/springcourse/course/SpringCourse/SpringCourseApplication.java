package com.springcourse.course.SpringCourse;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springcourse.course.SpringCourse.domain.Categoria;
import com.springcourse.course.SpringCourse.domain.Cidade;
import com.springcourse.course.SpringCourse.domain.Cliente;
import com.springcourse.course.SpringCourse.domain.Endereco;
import com.springcourse.course.SpringCourse.domain.Estado;
import com.springcourse.course.SpringCourse.domain.Produto;
import com.springcourse.course.SpringCourse.domain.enums.TipoCliente;
import com.springcourse.course.SpringCourse.repositories.CategoriaRepository;
import com.springcourse.course.SpringCourse.repositories.CidadeRepository;
import com.springcourse.course.SpringCourse.repositories.ClienteRepository;
import com.springcourse.course.SpringCourse.repositories.EnderecoRepository;
import com.springcourse.course.SpringCourse.repositories.EstadoRepository;
import com.springcourse.course.SpringCourse.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringCourseApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaDAO;
	
	@Autowired
	private ProdutoRepository produtoDAO;
	
	@Autowired
	private EstadoRepository estadoDAO;
	
	@Autowired
	private CidadeRepository cidadeDao;
	
	@Autowired
	private EnderecoRepository enderecoDao;
	
	@Autowired
	private ClienteRepository clienteDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringCourseApplication.class, args);
	}
	 

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "Sao paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estadoDAO.saveAll(Arrays.asList(est1, est2));
		cidadeDao.saveAll(Arrays.asList(cid1, cid2, cid3));
		categoriaDAO.saveAll(Arrays.asList(cat1, cat2));
		produtoDAO.saveAll(Arrays.asList(p1, p2, p3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "33679815490", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("2735679809", "4537568943"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 30", "Jardim", "85879320", cli1, cid1);
		Endereco end2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "85079320", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		clienteDao.saveAll(Arrays.asList(cli1));
		enderecoDao.saveAll(Arrays.asList(end1,end2));
		
		
	}

}
