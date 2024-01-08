package entities.institution;

import entities.employee.Employee;
import entities.process.Process;
import entities.juridical.AbstractJuridical;

import java.util.ArrayList;

public class Institution extends AbstractJuridical {
    private ArrayList<Process> processes;

    @Override
    public String toString() {
        return "entities.institution.Institution {" +
                "\n\tCNPJ: '" + super.getCnpj() + '\'' +
                ",\n\tAddress: '" + super.getAddress() + '\'' +
                ",\n\tContact: '" + super.getContact() + '\'' +
                ",\n\tProcesses: " + processes +
                ",\n\tEmployees: " + employees +
                "\n}";
    }

    public Institution() {
        super();
    }

    public Institution(String cnpj, String address, String contact, ArrayList<Process> processes, ArrayList<Employee> employees) {
        super(cnpj, address, contact, employees);
        this.processes = processes;
    }

    public ArrayList<Process> getProcesses() { return this.processes; }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }
}
