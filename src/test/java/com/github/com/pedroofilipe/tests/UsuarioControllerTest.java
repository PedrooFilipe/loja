package com.github.com.pedroofilipe.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.com.pedroofilipe.dto.UsuarioDto;
import com.github.com.pedroofilipe.enums.TipoUsuario;
import com.github.com.pedroofilipe.model.Usuario;
import com.github.com.pedroofilipe.services.UsuarioService;
import com.sun.xml.bind.v2.schemagen.xmlschema.Any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

@SpringBootTest
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;
    
//    @Test
//    public void cadastrarUsuarioAdministrador() throws JsonProcessingException, Exception {
//    	
//    	Usuario usuario = new Usuario(0, "UsuarioAdmin", "usuario.admin@gmail.com", "senhaadm", TipoUsuario.ADMINISTRADOR);
//    	
//    	mockMvc.perform(post("/api/usuarios/cadastrar")
//    			.contentType("application/json")
//    			.content(objectMapper.writeValueAsString(usuario)))
//    			.andExpect(status().isCreated()).andReturn();
//    }
//    
//    @Test
//    public void cadastrarUsuarioCliente() throws JsonProcessingException, Exception {
//    Usuario usuario = new Usuario(0, "UsuarioCliente", "usuario.cliente@gmail.com", "senhacliente", TipoUsuario.CLIENTE);
//    	
//    mockMvc.perform(post("/api/usuarios/cadastrar")
//    			.contentType("application/json")
//    			.content(objectMapper.writeValueAsString(usuario)))
//    			.andExpect(status().isCreated());
//    }
    
    @Test
    @WithUserDetails("admin")
    public void listarTodosClientes() throws Exception {
    	mockMvc.perform(get("/api/usuarios/buscar")).andExpect(status().isOk());
    }

}
