 package com.springcourse.course.SpringCourse;

import java.text.SimpleDateFormat;
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
import com.springcourse.course.SpringCourse.domain.ItemPedido;
import com.springcourse.course.SpringCourse.domain.Pagamento;
import com.springcourse.course.SpringCourse.domain.PagamentoComBoleto;
import com.springcourse.course.SpringCourse.domain.PagamentoComCartao;
import com.springcourse.course.SpringCourse.domain.Pedido;
import com.springcourse.course.SpringCourse.domain.Produto;
import com.springcourse.course.SpringCourse.domain.enums.EstadoPagamento;
import com.springcourse.course.SpringCourse.domain.enums.TipoCliente;
import com.springcourse.course.SpringCourse.repositories.CategoriaRepository;
import com.springcourse.course.SpringCourse.repositories.CidadeRepository;
import com.springcourse.course.SpringCourse.repositories.ClienteRepository;
import com.springcourse.course.SpringCourse.repositories.EnderecoRepository;
import com.springcourse.course.SpringCourse.repositories.EstadoRepository;
import com.springcourse.course.SpringCourse.repositories.ItemPedidoRepository;
import com.springcourse.course.SpringCourse.repositories.PagamentoRepository;
import com.springcourse.course.SpringCourse.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoDao;
	
	@Autowired
	private PagamentoRepository pagamentoDao;
	
	@Autowired
	private ItemPedidoRepository itemPedidoDao;
	

	public static void main(String[] args) {
		SpringApplication.run(SpringCourseApplication.class, args);
	}
	 

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Tecnologias");
		Categoria cat4 = new Categoria(null, "Cama mesa e banho");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Construçao");
		Categoria cat7 = new Categoria(null, "Games");
		Categoria cat8 = new Categoria(null, "Consoles");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "God of War", 40.00);
		Produto p5 = new Produto(null, "Mesa de escritorio", 300.00);
		Produto p6 = new Produto(null, "Toalha", 50.00);
		Produto p7 = new Produto(null, "PS4", 270.00);
		Produto p8 = new Produto(null, "Xbox One", 300.00);
		Produto p9 = new Produto(null, "Tijolos", 2.00);
		Produto p10 = new Produto(null, "Lençol", 40.00);
		Produto p11 = new Produto(null, "Teclado", 50.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3,p11));
		cat2.getProdutos().addAll(Arrays.asList(p2,p5));
		cat4.getProdutos().addAll(Arrays.asList(p10, p6));
		cat7.getProdutos().addAll(Arrays.asList(p4));
		cat6.getProdutos().addAll(Arrays.asList(p9));
		cat8.getProdutos().addAll(Arrays.asList(p7,p8));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat7));
		p5.getCategorias().addAll(Arrays.asList(cat2));
		p6.getCategorias().addAll(Arrays.asList(cat4));
		p7.getCategorias().addAll(Arrays.asList(cat8));
		p8.getCategorias().addAll(Arrays.asList(cat8));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat4));
		p11.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "Sao paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estadoDAO.saveAll(Arrays.asList(est1, est2));
		cidadeDao.saveAll(Arrays.asList(cid1, cid2, cid3));
		categoriaDAO.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		produtoDAO.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "33679815490", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("2735679809", "4537568943"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 30", "Jardim", "85879320", cli1, cid1);
		Endereco end2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "85079320", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		clienteDao.saveAll(Arrays.asList(cli1));
		enderecoDao.saveAll(Arrays.asList(end1,end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoDao.saveAll(Arrays.asList(ped1,ped2));
		pagamentoDao.saveAll(Arrays.asList(pag1, pag2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoDao.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
