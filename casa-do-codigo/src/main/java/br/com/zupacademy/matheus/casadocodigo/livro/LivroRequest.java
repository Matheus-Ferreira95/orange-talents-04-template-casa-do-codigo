package br.com.zupacademy.matheus.casadocodigo.livro;

import br.com.zupacademy.matheus.casadocodigo.autor.Autor;
import br.com.zupacademy.matheus.casadocodigo.categoria.Categoria;
import br.com.zupacademy.matheus.casadocodigo.validation.UniqueValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroRequest {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "Já existe um livro cadastrado com este titulo.")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer paginas;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "Já existe um livro cadastrado com este isbn.")
    private String isbn;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLancamento;

    @NotNull
    private Long categoriaId;

    @NotNull
    private Long autorId;

    public LivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
                        @NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer paginas,
                        @NotBlank String isbn, @Future LocalDate dataLancamento, @NotNull Long categoriaId,
                        @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.paginas = paginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
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

    public Livro toModel(EntityManager manager) {
        Categoria categoria = manager.find(Categoria.class, this.categoriaId);
        Autor autor = manager.find(Autor.class, this.autorId);
        Livro livro = new Livro(this);
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        return livro;
    }
}
