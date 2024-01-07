import employee.Employee;
import juridicalEntity.AbstractJuridicalEntity;

import java.util.ArrayList;

public class Institution extends AbstractJuridicalEntity {
    private ArrayList<Process> processes;
    private ArrayList<Employee> employees;

    @Override
    public String toString() {
        return "Institution {" +
                "\n\tCNPJ: '" + super.getCnpj() + '\'' +
                ",\n\tAddress: '" + super.getAddress() + '\'' +
                ",\n\tContact: '" + super.getContact() + '\'' +
                ",\n\tProcesses: " + processes +
                ",\n\tEmployees: " + employees +
                "\n}";
    }

    public Institution(String cnpj, String address, String contact, ArrayList<Process> processes, ArrayList<Employee> employees) {
        super(cnpj, address, contact);
        this.processes = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public Institution() {
        super();
    }

    public ArrayList<Process> getProcesses() { return this.processes; }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}