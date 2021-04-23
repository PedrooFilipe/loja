package com.github.com.pedroofilipe.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.com.pedroofilipe.model.Produto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void produtoListAll() throws Exception {
        mockMvc.perform(get("/produtos/buscar")).andExpect(status().isOk());
    }

    @Test
    public void produtoSave() throws Exception{
        Produto produto = new Produto(0, "Novo produto", 100F);
        mockMvc.perform(post("/produtos/cadastrar").contentType("application/json").content(objectMapper.writeValueAsString(produto))).andExpect(status().isOk());
    }

    @Test
    public void produtoUpdate() throws Exception{
        int idProduto = 2;
        Produto produto = new Produto(idProduto, "Nova descricao", 100F);
        mockMvc.perform(put("/produtos/alterar/" +idProduto).contentType("application/json").content(objectMapper.writeValueAsString(produto))).andExpect(status().isOk());
    }
}
