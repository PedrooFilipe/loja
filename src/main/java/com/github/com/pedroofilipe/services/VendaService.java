package com.github.com.pedroofilipe.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.com.pedroofilipe.dto.VendaDto;
import com.github.com.pedroofilipe.enums.TipoPagamento;
import com.github.com.pedroofilipe.model.Carrinho;
import com.github.com.pedroofilipe.model.ItemCarrinho;
import com.github.com.pedroofilipe.model.ItemVenda;
import com.github.com.pedroofilipe.model.Usuario;
import com.github.com.pedroofilipe.model.Venda;
import com.github.com.pedroofilipe.repositories.CarrinhoRepository;
import com.github.com.pedroofilipe.repositories.ItemVendaRepository;
import com.github.com.pedroofilipe.repositories.UsuarioRepository;
import com.github.com.pedroofilipe.repositories.VendaRepository;

@Service
public class VendaService {
	
	private VendaRepository vendaRepository;
	private ItemVendaRepository itemVendaRepository;
    private CarrinhoRepository carrinhoRepository;	   
    private CarrinhoService carrinhoService;
	private UsuarioRepository usuarioRepository;
    
	@Autowired
	public VendaService(VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository, CarrinhoRepository carrinhoRepository, CarrinhoService carrinhoService, UsuarioRepository usuarioRepository) {
		this.vendaRepository = vendaRepository;
		this.itemVendaRepository = itemVendaRepository;
	    this.carrinhoRepository = carrinhoRepository;	 
	    this.carrinhoService = carrinhoService;
	    this.usuarioRepository = usuarioRepository;
	}
	
	public Page<VendaDto> listarTodos(Pageable pageable){
		return vendaRepository.findAll(pageable).map(venda -> VendaDto.toDto(venda));
	}
	
	public Page<VendaDto> listarPorUsuario(Pageable pageable, int usuarioId){
		return vendaRepository.findByUsuario_Id(pageable, usuarioId).map(venda -> VendaDto.toDto(venda));
	}
	
	public VendaDto retornar(int id) {
    	Venda venda = vendaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Venda não encontrada"));
    	return VendaDto.toDto(venda);
    }

    public VendaDto cadastrar(int usuarioId, Venda venda){
    	List<ItemVenda> itensVenda = new ArrayList<ItemVenda>();
    	Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!"));
    	Carrinho carrinho = carrinhoRepository.findByUsuario_Id(usuarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho não encontrado para o usuário informado!"));
    	
    	if(carrinho.getItemCarrinhos().isEmpty()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário informado não tem itens no carrinho");
    	}
    	
    	for(ItemCarrinho itemCarrinho : carrinho.getItemCarrinhos() ) {
    		itensVenda.add(new ItemVenda(itemCarrinho.getProduto(), itemCarrinho.getQuantidade(), itemCarrinho.getValorTotalDesconto()));
    	}
    	
    	venda.setValorTotal(carrinho.getValorTotal());
    	venda.setValorTotalDesconto(carrinho.getValorTotal() );
    	venda.setValorDesconto(carrinho.getValorAplicadoPromocaoCarrinho());
    	
    	//verifica se o pagamento da venda vai ser a vista, se for, aplica 10% de desconto
    	if(venda.getTipoPagamento().equals(TipoPagamento.A_VISTA)) {
    		float valorDesconto = (venda.getValorTotal() * 10)/100;
    		venda.setValorTotalDesconto(venda.getValorTotal() - valorDesconto);
    		venda.setValorDesconto(venda.getValorDesconto() + valorDesconto);
    	}
    	
    	limparCarrinho(carrinho);
    	venda.setUsuario(usuario);
    	
    	venda = vendaRepository.save(venda);
    	
    	for(ItemVenda item : itensVenda) {
    		item.setVenda(venda);
    	}
    	
    	venda.setItemVendas((List<ItemVenda>)itemVendaRepository.saveAll(itensVenda));
    	
        return VendaDto.toDto(venda);
    }
    
    public void limparCarrinho(Carrinho carrinho) {
    	while(!carrinho.getItemCarrinhos().isEmpty()) {
    		carrinho = carrinhoService.removerItem(carrinho.getItemCarrinhos().get(0));
    	}
    }
}
