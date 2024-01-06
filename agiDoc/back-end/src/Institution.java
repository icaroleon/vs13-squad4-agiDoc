import employee.Employee;

import java.util.ArrayList;

public class Institution {
    private ArrayList<Process> processes;
    private ArrayList<Employee> employees;

    public Institution() {
        this.processes = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public Institution(ArrayList<Process> processes, ArrayList<Employee> employees) {
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
