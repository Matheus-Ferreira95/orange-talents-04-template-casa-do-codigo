package br.com.zupacademy.matheus.casadocodigo.livro;

import br.com.zupacademy.matheus.casadocodigo.autor.AutorResponse;
import br.com.zupacademy.matheus.casadocodigo.categoria.CategoriaResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroResponsePorId {

    private String titulo;

    private String resumo;

    private String sumario;

    private BigDecimal preco;

    private Integer paginas;

    private String isbn;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataLancamento;

    private CategoriaResponse categoriaResponse;

    private AutorResponse autorResponse;

    public LivroResponsePorId(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.paginas = livro.getPaginas();
        this.isbn = livro.getIsbn();
        this.dataLancamento = livro.getDataLancamento();
        this.categoriaResponse = new CategoriaResponse(livro.getCategoria());
        this.autorResponse = new AutorResponse(livro.getAutor());
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public CategoriaResponse getCategoriaResponse() {
        return categoriaResponse;
    }

    public AutorResponse getAutorResponse() {
        return autorResponse;
    }
}
