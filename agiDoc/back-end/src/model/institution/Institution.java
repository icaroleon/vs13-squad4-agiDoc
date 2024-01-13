package model.institution;

import model.employee.Employee;
import model.process.Process;
import model.juridical.AbstractJuridical;


import java.util.ArrayList;

public class Institution extends AbstractJuridical {

    private int id;
    private ArrayList<Process> processes;


    public Institution() {
        super();
    }

    public Institution(String companyName, String cnpj, Address address, Contact contact, ArrayList<Departament> departaments, ArrayList<Process> processes) {
        super(companyName, cnpj, address, contact, departaments);
        this.processes = processes;
    }

    @Override
    public String toString() {
        return """ 
                {
                    CNPJ: %s
                    Company Name: %s
                    Address: %s
                    Contact: %s
                    N° Processes: %d
                    N° Departaments: %d
                }
                """.formatted(
                this.cnpj,
                this.companyName,
                this.address,
                this.contact,
                this.processes.size(),
                this.departaments.size()
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Process> getProcesses() { return this.processes; }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }
}
