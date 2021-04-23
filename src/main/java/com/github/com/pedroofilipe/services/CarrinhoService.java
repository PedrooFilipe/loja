package com.github.com.pedroofilipe.services;

import com.github.com.pedroofilipe.enums.TipoDesconto;
import com.github.com.pedroofilipe.model.Carrinho;
import com.github.com.pedroofilipe.model.ItemCarrinho;
import com.github.com.pedroofilipe.model.Produto;
import com.github.com.pedroofilipe.model.Promocao;
import com.github.com.pedroofilipe.repositories.CarrinhoRepository;
import com.github.com.pedroofilipe.repositories.ItemCarrinhoRepository;
import com.github.com.pedroofilipe.repositories.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ItemCarrinhoRepository produtoRepository;
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

    public Promocao adicionarItem(ItemCarrinho itemCarrinho){
        Produto produto = itemCarrinho.getProduto();

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

        Optional<Carrinho> carrinho = carrinhoRepository.findById(itemCarrinho.getId());
        List<ItemCarrinho> itensCarrinho = carrinho.get().getItemCarrinhos();
        itensCarrinho.add(itemCarrinho);

        carrinho.get().setItemCarrinhos(carrinho.get().getItemCarrinhos());
        carrinho.get();


        return promocao;
    }

    public Promocao removerItem(ItemCarrinho itemCarrinho){

        Promocao promocao = promocaoRepository.findByCategorias_Id(itemCarrinho.getProduto().getCategoria().getId());

        return promocao;
    }


}
