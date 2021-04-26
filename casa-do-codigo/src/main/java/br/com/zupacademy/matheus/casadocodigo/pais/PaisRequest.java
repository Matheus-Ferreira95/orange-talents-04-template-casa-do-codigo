package br.com.zupacademy.matheus.casadocodigo.pais;

import br.com.zupacademy.matheus.casadocodigo.validation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class PaisRequest {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome", message = "Já existe um país com este nome cadastrado.")
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pais toModel() {
        return new Pais(nome);
    }
}
