import juridicalEntity.AbstractJuridicalEntity;

public class Employee {

    private String name;
    private String contact;
    private AbstractJuridicalEntity company;

    public Employee(){

    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractJuridicalEntity getCompany() {
        return company;
    }

    public void setCompany(AbstractJuridicalEntity company) {
        this.company = company;
    }
}
