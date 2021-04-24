package br.com.zupacademy.matheus.casadocodigo.validacao;

public class Error {

    public String campo;
    public String mensagem;

    public Error(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
