package com.github.com.pedroofilipe.tests;

import com.github.com.pedroofilipe.controllers.CupomDescontoController;
import com.github.com.pedroofilipe.model.CupomDesconto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

public class CupomDescontoTest {


    CupomDescontoController cupomDescontoController;

    @Autowired
    public void CupomDescontoTest(CupomDescontoController cupomDescontoController){
        this.cupomDescontoController = cupomDescontoController;
    }

    @Test
    public void cadastrarProduto(){
        ResponseEntity<?> retorno = cupomDescontoController.cadastrar(new CupomDesconto(100f, new Date(), "CUPOM10"));
        assertThat(retorno, instanceOf(ResponseEntity.class));

        cupomDescontoController.cadastrar(new CupomDesconto(100f, new Date(), "CUPOM10"));
    }

    @Test
    public void retornar(){
        cupomDescontoController.buscarPorDescricao("Teste");
    }

}
