package br.com.zupacademy.matheus.casadocodigo.livro;

public class LivroResponse {

    private Long id;
    private String titulo;

    public LivroResponse(Livro livro) {
        id = livro.getId();
        titulo = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
