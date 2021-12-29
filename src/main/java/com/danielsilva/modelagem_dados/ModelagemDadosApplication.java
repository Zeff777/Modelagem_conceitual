package com.danielsilva.modelagem_dados;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.danielsilva.modelagem_dados.domain.Categoria;
import com.danielsilva.modelagem_dados.domain.Cidade;
import com.danielsilva.modelagem_dados.domain.Cliente;
import com.danielsilva.modelagem_dados.domain.Endereco;
import com.danielsilva.modelagem_dados.domain.Estado;
import com.danielsilva.modelagem_dados.domain.ItemPedido;
import com.danielsilva.modelagem_dados.domain.Pagamento;
import com.danielsilva.modelagem_dados.domain.PagamentoComBoleto;
import com.danielsilva.modelagem_dados.domain.PagamentoComCartao;
import com.danielsilva.modelagem_dados.domain.Pedido;
import com.danielsilva.modelagem_dados.domain.Produto;
import com.danielsilva.modelagem_dados.domain.enums.EstadoPagamento;
import com.danielsilva.modelagem_dados.domain.enums.TipoCliente;
import com.danielsilva.modelagem_dados.repositories.CategoriaRepository;
import com.danielsilva.modelagem_dados.repositories.CidadeRepository;
import com.danielsilva.modelagem_dados.repositories.ClienteRepository;
import com.danielsilva.modelagem_dados.repositories.EnderecoRepository;
import com.danielsilva.modelagem_dados.repositories.EstadoRepository;
import com.danielsilva.modelagem_dados.repositories.ItemPedidoRepository;
import com.danielsilva.modelagem_dados.repositories.PagamentoRepository;
import com.danielsilva.modelagem_dados.repositories.PedidoRepository;
import com.danielsilva.modelagem_dados.repositories.ProdutoRepository;

@SpringBootApplication
public class ModelagemDadosApplication implements CommandLineRunner {

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
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    

	public static void main(String[] args) {
		SpringApplication.run(ModelagemDadosApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Mina Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "3637912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("45168495", "12346890"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 307", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 777", "Centro", "38777012", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
				
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2021 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2021 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 7); 
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2021 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		

	}

}
