public class Employee {

    private String name;
    private String contact;
    private LegalPerson company;

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

    public LegalPerson getCompany() {
        return company;
    }

    public void setCompany(LegalPerson company) {
        this.company = company;
    }
}
