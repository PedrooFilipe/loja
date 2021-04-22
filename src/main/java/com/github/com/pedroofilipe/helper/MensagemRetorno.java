package com.github.com.pedroofilipe.helper;

public class MensagemRetorno {

    private String mensagem;
    private String tipo;

    public MensagemRetorno(String mensagem, String tipo){
        this.mensagem = mensagem;
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
