public class Employee {

    private String name;
    private String contact;
    private LegalPerson company;

    public String getContato() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    public LegalPerson getEmpresa() {
        return company;
    }

    public void setEmpresa(LegalPerson company) {
        this.company = company;
    }
}
