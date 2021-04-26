package br.com.zupacademy.matheus.casadocodigo.livro;

import br.com.zupacademy.matheus.casadocodigo.autor.Autor;
import br.com.zupacademy.matheus.casadocodigo.categoria.Categoria;
import br.com.zupacademy.matheus.casadocodigo.validation.ExistsId;
import br.com.zupacademy.matheus.casadocodigo.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

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

    @NotBlank
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
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataLancamento;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id", message = "Categoria não existente no banco de dados")
    private Long categoriaId;

    @NotNull
    @ExistsId(domainClass = Autor.class, fieldName = "id", message = "Autor não existente no banco de dados")
    private Long autorId;

    public LivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
                        @NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer paginas,
                        @NotBlank String isbn, @NotNull Long categoriaId,
                        @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.paginas = paginas;
        this.isbn = isbn;
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

    /*
        setter criado pq o jackson não estava sendo capaz de desserializar o json com a data pelo construtor
     */
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Livro toModel(EntityManager manager) {
        Categoria categoria = manager.find(Categoria.class, this.categoriaId);
        Autor autor = manager.find(Autor.class, this.autorId);

        Assert.state(autor != null, "Você esta querendo cadastrar um livro para um autor inexistente " + autorId);
        Assert.state(categoria != null, "Você esta querendo cadastrar um livro para uma categoria inexistente " + categoriaId);

        return new Livro(titulo, resumo, sumario, preco, paginas, isbn, dataLancamento, autor, categoria);
    }
}
