package com.springcourse.course.SpringCourse.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.springcourse.course.SpringCourse.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamento, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setDataVencimento(cal.getTime());
	}
	
}
