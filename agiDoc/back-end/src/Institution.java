import juridicalEntity.AbstractJuridicalEntity;

import java.util.ArrayList;

public class Institution extends AbstractJuridicalEntity {
    private ArrayList<Process> processes;
    private ArrayList<Employee> employees;

    public Institution() {
        super();
        this.processes = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public Institution(String cpnj, String address, String contact, ArrayList<Process> processes, ArrayList<Employee> employees) {
        super(cpnj, address, contact);
        this.processes = processes;
        this.employees = employees;
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
