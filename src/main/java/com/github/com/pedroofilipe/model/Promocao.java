package com.github.com.pedroofilipe.model;

import com.github.com.pedroofilipe.enums.TipoDesconto;
import com.github.com.pedroofilipe.enums.TipoPromocao;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Promocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "valordesconto")
    private float valorDesconto;
    @Column(name = "valorminimo")
    private float valorMinimo;
    private String descricao;
    @Column(name = "tipopromocao")
    private TipoPromocao tipoPromocao;
    @Column(name = "tipodesconto")
    private TipoDesconto tipoDesconto;
    @OneToMany(mappedBy = "promocao")
    private List<Categoria> categorias;

    public Promocao(){

    }

    public Promocao(float valorDesconto, Date dataValidade, String descricao){
        this.valorDesconto = valorDesconto;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoPromocao getTipoPromocao() {
        return tipoPromocao;
    }

    public void setTipoPromocao(TipoPromocao tipoPromocao) {
        this.tipoPromocao = tipoPromocao;
    }

    public TipoDesconto getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDesconto tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public float getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
