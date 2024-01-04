package document;

public class Document {
    private String protocol;
    private String content;

    public Document() {
    }

    public Document(String protocol, String content) {
        this.protocol = protocol;
        this.content = content;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
