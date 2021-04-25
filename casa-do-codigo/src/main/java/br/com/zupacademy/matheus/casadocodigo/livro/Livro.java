package br.com.zupacademy.matheus.casadocodigo.livro;

import br.com.zupacademy.matheus.casadocodigo.autor.Autor;
import br.com.zupacademy.matheus.casadocodigo.categoria.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String resumo;

    private String sumario;

    private BigDecimal preco;

    private Integer paginas;

    @Column(unique = true)
    private String isbn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Autor autor;

    @Deprecated
    public Livro() {}

    public Livro(LivroRequest livroRequest) {
        this.titulo = livroRequest.getTitulo();
        this.resumo = livroRequest.getResumo();
        this.sumario = livroRequest.getSumario();
        this.preco = livroRequest.getPreco();
        this.paginas = livroRequest.getPaginas();
        this.isbn = livroRequest.getIsbn();
        this.dataLancamento = livroRequest.getDataLancamento();
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
