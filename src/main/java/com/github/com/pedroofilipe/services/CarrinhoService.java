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
import org.springframework.stereotype.Service;

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

//    public Carrinho cadastrar(Carrinho carrinho){

//        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);

//        if(promocao.getTipoPromocao() == TipoPromocao.PRODUTO){
//            for(Produto item : promocao.getProdutos()){
//                Optional<Produto> produto = produtoRepository.findById(item.getId());
//                produtoRepository.save(new Produto(produto.get().getId(), produto.get().getDescricao(), produto.get().getPrecoVenda(), promocaoSalva));
//            }
//        }

//        return carrinhoSalvo;
//    }
    
    public Page listarTodos(Pageable pageable){
    	Page<CarrinhoDto> carrinhos = carrinhoRepository.findAll(pageable).map(carrinho -> CarrinhoDto.toDto(carrinho));
    	return carrinhos;
    }

    public Carrinho adicionarItem(ItemCarrinho itemCarrinho){
        Produto produto = produtoRepository.findById(itemCarrinho.getProduto().getId()).get();
        Promocao promocao = promocaoRepository.findByCategorias_Id(produto.getCategoria().getId());

        if(promocao != null){
            if(promocao.getTipoDesconto() == TipoDesconto.VALOR){
                itemCarrinho.setValorTotalDesconto(itemCarrinho.getValorTotalDesconto() - promocao.getValorDesconto());
                itemCarrinho.setValorDesconto(promocao.getValorDesconto());
            }else {
                float valorDesconto = (itemCarrinho.getValorTotal() * promocao.getValorDesconto()) / 100;
                itemCarrinho.setValorTotalDesconto(itemCarrinho.getValorTotalDesconto() - valorDesconto);
                itemCarrinho.setValorDesconto(valorDesconto);
            }
        }
        
        itemCarrinhoRepository.save(itemCarrinho);

        Optional<Carrinho> carrinho = carrinhoRepository.findById(itemCarrinho.getCarrinho().getId());
        List<ItemCarrinho> itensCarrinho = carrinho.get().getItemCarrinhos();
        itensCarrinho.add(itemCarrinho);

        carrinho.get().setItemCarrinhos(carrinho.get().getItemCarrinhos());
        carrinho.get().setValorTotal(carrinho.get().getValorTotal() + itemCarrinho.getValorTotalDesconto());

        //verifica se existe alguma promoção cadastrada com o tipo carrinho, e aplicar o desconto caso exista alguma
        promocao = new Promocao();
        promocao = promocaoRepository.findBytipoPromocao(TipoPromocao.CARRINHO);
        
        if(promocao != null) {
        	if(carrinho.get().getValorTotal() >= promocao.getValorMinimo()) {
        		if(promocao.getTipoDesconto() == TipoDesconto.VALOR){
                    carrinho.get().setValorTotal(carrinho.get().getValorTotal() - promocao.getValorDesconto());
                }else {
                    carrinho.get().setValorTotal(carrinho.get().getValorTotal() - (carrinho.get().getValorTotal() * promocao.getValorDesconto()) / 100);
                }
        	}
        	List<Promocao> promocoes = carrinho.get().getPromocoes();
        	promocoes.add(promocao);
        	
        	carrinho.get().setPromocoes(promocoes);
        }
        
        return carrinhoRepository.save(carrinho.get());
    }

    public Promocao removerItem(ItemCarrinho itemCarrinho){

        Promocao promocao = promocaoRepository.findByCategorias_Id(itemCarrinho.getProduto().getCategoria().getId());

        return promocao;
    }


}
