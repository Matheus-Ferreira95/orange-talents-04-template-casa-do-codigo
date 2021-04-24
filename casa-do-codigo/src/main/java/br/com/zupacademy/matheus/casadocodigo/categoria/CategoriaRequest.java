package br.com.zupacademy.matheus.casadocodigo.categoria;

import br.com.zupacademy.matheus.casadocodigo.validation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Categoria com este nome já existente")
    private String nome;

    public CategoriaRequest() {}

    public CategoriaRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
