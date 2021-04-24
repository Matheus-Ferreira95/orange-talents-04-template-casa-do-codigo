package br.com.zupacademy.matheus.casadocodigo.autor;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank @Length(max = 400)
    private String descricao;

    private LocalDateTime instanteCadastrado = LocalDateTime.now();

    @Deprecated
    public Autor() {}

    public Autor(@NotBlank String nome, @Email @NotBlank String email, @NotBlank @Length(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteCadastrado() {
        return instanteCadastrado;
    }
}
