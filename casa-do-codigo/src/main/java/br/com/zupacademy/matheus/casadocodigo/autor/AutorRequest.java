package br.com.zupacademy.matheus.casadocodigo.autor;

import br.com.zupacademy.matheus.casadocodigo.validation.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@UniqueValue
public class AutorRequest {

    @NotBlank
    private String nome;

    @Email @NotBlank
    private String email;

    @NotBlank @Length(max = 400)
    private String descricao;

    public AutorRequest(@NotBlank String nome, @Email @NotBlank String email, @NotBlank @Length(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public Autor toModel() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
