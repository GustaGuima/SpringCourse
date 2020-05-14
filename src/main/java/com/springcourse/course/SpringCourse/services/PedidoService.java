package com.springcourse.course.SpringCourse.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.course.SpringCourse.domain.ItemPedido;
import com.springcourse.course.SpringCourse.domain.PagamentoComBoleto;
import com.springcourse.course.SpringCourse.domain.Pedido;
import com.springcourse.course.SpringCourse.domain.Produto;
import com.springcourse.course.SpringCourse.domain.enums.EstadoPagamento;
import com.springcourse.course.SpringCourse.repositories.ItemPedidoRepository;
import com.springcourse.course.SpringCourse.repositories.PagamentoRepository;
import com.springcourse.course.SpringCourse.repositories.PedidoRepository;
import com.springcourse.course.SpringCourse.repositories.ProdutoRepository;
import com.springcourse.course.SpringCourse.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoDAO;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoDAO;

	@Autowired
	private ProdutoRepository produtoDAO;

	@Autowired
	private ItemPedidoRepository ipDAO;

	public Pedido search(Integer id) {
		Optional<Pedido> pedido = pedidoDAO.findById(id);

		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! id : " + id + " Pedido : " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = pedidoDAO.save(obj);
		pagamentoDAO.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			// ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			Optional<Produto> prod = produtoDAO.findById(ip.getProduto().getId());
			ip.setPreco(prod.get().getPrice());
			ip.setPedido(obj);
		}
		ipDAO.saveAll(obj.getItens());
		return obj;
	}

}
