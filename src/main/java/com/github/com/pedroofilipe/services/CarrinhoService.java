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
import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PromocaoRepository promocaoRepository;
    
    public Page<CarrinhoDto> listarTodos(Pageable pageable){
    	Page<CarrinhoDto> carrinhos = carrinhoRepository.findAll(pageable).map(carrinho -> CarrinhoDto.toDto(carrinho));
    	return carrinhos;
    }

    public CarrinhoDto adicionarItem(ItemCarrinho itemCarrinho, int usuarioId){
      Produto produto = produtoRepository.findById(itemCarrinho.getProduto().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
      Carrinho carrinho = carrinhoRepository.findByUsuario_Id(usuarioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
      List<Promocao> promocoes = carrinho.getPromocoes();
        
        
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
        return CarrinhoDto.toDto(carrinhoRepository.save(carrinho));
    } 

    public void removerItem(ItemCarrinho itemCarrinho){
    	
    	itemCarrinho = itemCarrinhoRepository.findById(itemCarrinho.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    	Carrinho carrinho = carrinhoRepository.findByUsuario_Id(itemCarrinho.getCarrinho().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    	List<Promocao> promocoesParaExclusao = new ArrayList<Promocao>();
    	Promocao promocao = new Promocao();
    	
    	//validação para saber se o carrinho tem alguma promoção por categoria do produto
    	if(itemCarrinho.getValorDesconto() > 0) {
    		
    		promocao = promocaoRepository.findByCategorias_Id(itemCarrinho.getProduto().getCategoria().getId());
    		promocoesParaExclusao.add(promocao);
    		
    		carrinho.setValorTotal(carrinho.getValorTotal() - itemCarrinho.getValorTotalDesconto());
    		
    	}else {
    		carrinho.setValorTotal(carrinho.getValorTotal() - itemCarrinho.getValorTotal());
    	}
    	
    	
    	//validação para saber se o carrinho tem algum outro tipo de promoção
    	List<Promocao> promocoesCarrinho = carrinho.getPromocoes();
    	
    	if(!promocoesCarrinho.isEmpty()) {
    		for(Promocao itemPromocao : promocoesCarrinho) {
        		if(itemPromocao.getTipoPromocao().equals(TipoPromocao.CARRINHO)) {
        			if(carrinho.getValorTotal() - itemCarrinho.getValorTotalDesconto() < itemPromocao.getValorMinimo()) {
        				carrinho.setValorTotal((carrinho.getValorTotal() - itemCarrinho.getValorTotalDesconto()) + carrinho.getValorAplicadoPromocaoCarrinho());
        				
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
    	
    	itemCarrinhoRepository.delete(itemCarrinho);
    	carrinho.getItemCarrinhos().remove(itemCarrinho);
    	carrinho.setPromocoes(promocoesCarrinho);
    	carrinhoRepository.save(carrinho);
    }
}
