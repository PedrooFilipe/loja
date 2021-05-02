package com.github.com.pedroofilipe.services;

import com.github.com.pedroofilipe.dto.CarrinhoDto;
import com.github.com.pedroofilipe.enums.TipoDesconto;
import com.github.com.pedroofilipe.enums.TipoPromocao;
import com.github.com.pedroofilipe.model.Carrinho;
import com.github.com.pedroofilipe.model.ItemCarrinho;
import com.github.com.pedroofilipe.model.Produto;
import com.github.com.pedroofilipe.model.Promocao;
import com.github.com.pedroofilipe.repositories.CarrinhoRepository;
import com.github.com.pedroofilipe.repositories.ItemCarrinhoRepository;
import com.github.com.pedroofilipe.repositories.ProdutoRepository;
import com.github.com.pedroofilipe.repositories.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoService {

   
    private CarrinhoRepository carrinhoRepository;
    private ItemCarrinhoRepository itemCarrinhoRepository;
    private ProdutoRepository produtoRepository;
    private PromocaoRepository promocaoRepository;
    
    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository, ItemCarrinhoRepository itemCarrinhoRepository, ProdutoRepository produtoRepository,PromocaoRepository promocaoRepository){
    	this.carrinhoRepository = carrinhoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.promocaoRepository = promocaoRepository;
    }
    
    public Page<CarrinhoDto> listarTodos(Pageable pageable){
    	return carrinhoRepository.findAll(pageable).map(carrinho -> CarrinhoDto.toDto(carrinho));
    }

    public Carrinho adicionarItem(ItemCarrinho itemCarrinho, int usuarioId){
      Produto produto = produtoRepository.findById(itemCarrinho.getProduto().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
      Carrinho carrinho = carrinhoRepository.findByUsuario_Id(usuarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho não encontrado para o usuário informado!"));
      List<Promocao> promocoes = carrinho.getPromocoes();
        
      itemCarrinho.setCarrinho(carrinho);
        
        //verifica se existe alguma promoção para a categoria do produto e aplica o desconto caso existe alguma.
        Promocao promocao = promocaoRepository.findByCategorias_Id(produto.getCategoria().getId());

        if(promocao != null) {
            if(promocao.getTipoDesconto() == TipoDesconto.VALOR){
                itemCarrinho.setValorTotalDesconto(itemCarrinho.getValorTotalDesconto() - promocao.getValorDesconto());
                itemCarrinho.setValorDesconto(promocao.getValorDesconto());
            }else {
                float valorDesconto = (itemCarrinho.getValorTotal() * promocao.getValorDesconto()) / 100;
                itemCarrinho.setValorTotalDesconto(itemCarrinho.getValorTotalDesconto() - valorDesconto);
                itemCarrinho.setValorDesconto(valorDesconto);
            }
            promocoes.add(promocao);
        }
        
        itemCarrinhoRepository.save(itemCarrinho);
        
        List<ItemCarrinho> itensCarrinho = carrinho.getItemCarrinhos();
        itensCarrinho.add(itemCarrinho);

        carrinho.setItemCarrinhos(itensCarrinho);
        carrinho.setValorTotal(carrinho.getValorTotal() + itemCarrinho.getValorTotalDesconto());

        //verifica se existe alguma promoção cadastrada com o tipo carrinho e aplica o desconto caso exista alguma
        promocao = new Promocao();
        promocao = promocaoRepository.findBytipoPromocao(TipoPromocao.CARRINHO);
        
        //verificação para testar se já existe alguma promoção aplicada do tipo carrinho, esse tipo de promoção é limitada uma por carrinho
        boolean podeAplicarPromocaoCarrinho = true;
        
        if(!promocoes.isEmpty()) {
        	for(Promocao itemPromocao : promocoes) {
        		if(itemPromocao.getTipoPromocao().equals(TipoPromocao.CARRINHO)) {
        			podeAplicarPromocaoCarrinho = false;
        		}
        	}
        }
        
        if(promocao != null && podeAplicarPromocaoCarrinho) {
        	if(carrinho.getValorTotal() >= promocao.getValorMinimo()) {
        		
        		if(promocao.getTipoDesconto() == TipoDesconto.VALOR){
                    carrinho.setValorTotal(carrinho.getValorTotal() - promocao.getValorDesconto());
                    carrinho.setValorAplicadoPromocaoCarrinho(promocao.getValorDesconto());
                }else {
                	float valorDesconto = (carrinho.getValorTotal() * promocao.getValorDesconto()) / 100;
                    carrinho.setValorTotal(carrinho.getValorTotal() - valorDesconto);
                    carrinho.setValorAplicadoPromocaoCarrinho(valorDesconto);
                }
        		promocoes.add(promocao);
        	}
        }
        
        carrinho.setPromocoes(promocoes);
        return carrinhoRepository.save(carrinho);
    } 

    public Carrinho removerItem(ItemCarrinho itemCarrinho){
    	Carrinho carrinho = carrinhoRepository.findByUsuario_Id(itemCarrinho.getCarrinho().getUsuario().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho não encontrado para o usuário informado!"));
    	itemCarrinho = itemCarrinhoRepository.findById(itemCarrinho.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho não encontrado"));
    	List<Promocao> promocoesParaExclusao = new ArrayList<Promocao>();
    	Promocao promocao = new Promocao();
    	float valorTotalCarrinho = 0;
    	
    	//validação para saber se o carrinho tem alguma promoção por categoria do produto
    	if(itemCarrinho.getValorDesconto() > 0) {
    		
    		promocao = promocaoRepository.findByCategorias_Id(itemCarrinho.getProduto().getCategoria().getId());
    		promocoesParaExclusao.add(promocao);
    		
    		valorTotalCarrinho = carrinho.getValorTotal() - itemCarrinho.getValorTotalDesconto();
    		
    	}else {
    		valorTotalCarrinho = carrinho.getValorTotal() - itemCarrinho.getValorTotal();
    	}
    	
    	
    	//validação para saber se o carrinho tem algum outro tipo de promoção
    	List<Promocao> promocoesCarrinho = carrinho.getPromocoes();
    	
    	if(!promocoesCarrinho.isEmpty()) {
    		for(Promocao itemPromocao : promocoesCarrinho) {
        		if(itemPromocao.getTipoPromocao().equals(TipoPromocao.CARRINHO)) {
        			if(carrinho.getValorTotal() - itemCarrinho.getValorTotalDesconto() < itemPromocao.getValorMinimo()) {
        				valorTotalCarrinho = (carrinho.getValorTotal() - itemCarrinho.getValorTotalDesconto()) + carrinho.getValorAplicadoPromocaoCarrinho();
        				carrinho.setValorAplicadoPromocaoCarrinho(0F);
        				
        				promocoesParaExclusao.add(itemPromocao);
        			}
        			
        		}
        	}
    	}
    	

    	if(!promocoesParaExclusao.isEmpty()) {
    		
    		for(Promocao itemPromocao : promocoesParaExclusao) {
    			promocoesCarrinho.remove(itemPromocao);
    			
    		}
    	}
    	
    	carrinho.setValorTotal(valorTotalCarrinho);
    	
    	itemCarrinhoRepository.delete(itemCarrinho);
    	carrinho.getItemCarrinhos().remove(itemCarrinho);
    	carrinho.setPromocoes(promocoesCarrinho);
    	return carrinhoRepository.save(carrinho);
    }
}
