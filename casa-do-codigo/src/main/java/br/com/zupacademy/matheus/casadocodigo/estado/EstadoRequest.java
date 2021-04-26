package br.com.zupacademy.matheus.casadocodigo.estado;

import br.com.zupacademy.matheus.casadocodigo.pais.Pais;
import br.com.zupacademy.matheus.casadocodigo.validation.ExistsId;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @ExistsId(domainClass = Pais.class,  fieldName = "id", message = "Não foi encontrado um país com este id")
    private Long idPais;

    public EstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Estado toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, idPais);
        return new Estado(nome, pais);
    }
}
