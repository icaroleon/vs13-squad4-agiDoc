public class Documento {
    private String protocolo;
    private String conteudo;

    public Documento() {
    }

    public Documento(String protocolo, String conteudo) {
        this.protocolo = protocolo;
        this.conteudo = conteudo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
