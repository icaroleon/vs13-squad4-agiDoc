package model.institution;

import model.user.User;
import model.process.Process;
import model.juridical.AbstractJuridical;

import java.util.ArrayList;

public class Institution extends AbstractJuridical {
    private ArrayList<Process> processes;

    @Override
    public String toString() {
        return """ 
                {
                    CNPJ: %s
                    Address: %s
                    Contact: %s
                    N° Processes: %d
                    N° Employees: %d
                }
                """.formatted(
                this.cnpj,
                this.address,
                this.contact,
                this.processes.size(),
                this.users.size()
        );
    }

    public Institution() {
        super();
    }

    public Institution(String cnpj, String address, String contact, ArrayList<Process> processes, ArrayList<User> users) {
        super(cnpj, address, contact, users);
        this.processes = processes;
    }

    public Institution(String cnpj, String address, String contact) {
        super(cnpj, address, contact);
    }

    public ArrayList<Process> getProcesses() { return this.processes; }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }
}
