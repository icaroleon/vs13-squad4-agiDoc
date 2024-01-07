package data;

import document.Document;
import Employee;
import Competitor;
import Institution;
import process.Process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
    public static Institution institution;

    public static void seed() {

//      TODO: Refatorar instâncias de documentos para Processos
        Document documentOneProcessOne = new Document("1", "documento: Contrato");
        Document documentTwoProcessOne = new Document("2", "documento: Edital");
        Document documentThreeProcessOne = new Document("3", "documento: Parecer Jurídico");

        Document documentOneProcessTwo = new Document("4", "documento: Contrato");
        Document documentTwoProcessTwo = new Document("6", "documento: Delegação de Competência");
        Document documentThreeProcessTwo = new Document("7", "documento: Termo de Referência");

        Document documentOneProcessThree = new Document("8", "documento: Contrato");
        Document documentTwoProcessThree = new Document("9", "documento: Manifestação");
        Document documentThreeProcessThree = new Document("10", "documento: Justificativa de Necessidade");

//      TODO: Refatorar instâncias de documentos para Concorrentes
        Document documentOneCompetitorOne = new Document("11", "documento: Certidão");
        Document documentTwoCompetitorOne = new Document("12", "documento: Termo de Referência");

        Document documentOneCompetitorTwo = new Document("13", "documento: Certidão");
        Document documentTwoCompetitorTwo = new Document("14", "documento: Portaria");

        Document documentOneCompetitorThree = new Document("15", "documento: Certidão");
        Document documentTwoCompetitorThree = new Document("16", "documento: Anexos");

        Document documentOneCompetitorFour = new Document("17", "documento: Certidão");
        Document documentTwoCompetitorFour = new Document("18", "documento: Justificativa de Necessidade");

        Document documentOneCompetitorFive = new Document("19", "documento: Certidão");
        Document documentTwoCompetitorFive = new Document("20", "documento: Delegação de Competência");

        Document documentOneCompetitorSix = new Document("21", "documento: Certidão");
        Document documentTwoCompetitorSix = new Document("22", "documento: Portaria");

//      TODO: Refatorar instâncias de Funcionários para Concorrentes
        Employee employeeCompetitorOne = new Employee("João", "9 0000-0000");

        Employee employeeCompetitorTwo = new Employee("Maria", "9 0000-0000");

        Employee employeeCompetitorThree = new Employee("Everton", "9 0000-0000");

        Employee employeeCompetitorFour = new Employee("Laura", "9 0000-0000");

        Employee employeeCompetitorFive = new Employee("Glória", "9 0000-0000");

        Employee employeeCompetitorSix = new Employee("Robson", "9 0000-0000");

//      TODO: Refatorar instâncias de Funcionários para Instituição
        Employee employeeOneInstitution = new Employee("Aron", "9 0000-0000");
        Employee employeeTwoInstitution = new Employee("Gabriel", "9 1111-1111");
        Employee employeeThreeInstitution = new Employee("Mari", "9 2222-2222");
        Employee employeeFourInstitution = new Employee("Ícaro", "9 3333-3333");
        Employee employeeFiveInstitution = new Employee("Rodrigo", "9 4444-4444");
        Employee employeeSixInstitution = new Employee("Vinicius", "9 5555-5555");
        Employee employeeSevenInstitution = new Employee("Camila", "9 6666-6666");
        Employee employeeEightInstitution = new Employee("Clara", "9 7777-7777");

//      TODO: Refatorar instâncias de concorrentes para Processos
//      Setar company em Funcionários
        Competitor competitorOneProcessOne = new Competitor(
                "00.0.000/0001-00",
                "Rua dos bobos",
                "9 0000-0000",
                true,
                 new ArrayList<Document>(Arrays.asList(
                         documentOneCompetitorOne,
                         documentTwoCompetitorOne
                 )),
                new ArrayList<>(List.of(employeeCompetitorOne))
        );
        employeeCompetitorOne.setCompany(competitorOneProcessOne);

        Competitor competitorTwoProcessOne = new Competitor(
                "11.1.111/1111-11",
                "Rua dos loucos",
                "9 0000-0000",
                true,
                new ArrayList<Document>(Arrays.asList(
                      documentOneCompetitorTwo,
                      documentTwoCompetitorTwo
                )),
                new ArrayList<>(List.of(employeeCompetitorTwo))
        );
        employeeCompetitorTwo.setCompany(competitorTwoProcessOne);


        Competitor competitorOneProcessTwo = new Competitor(
                "22.2.222/2221-22",
                "Rua dos azedos",
                "9 0000-0000",
                true,
                new ArrayList<Document>(Arrays.asList(
                        documentOneCompetitorThree,
                        documentTwoCompetitorThree
                )),
                new ArrayList<>(List.of(employeeCompetitorThree))
        );
        employeeCompetitorThree.setCompany(competitorOneProcessTwo);

        Competitor competitorTwoProcessTwo = new Competitor(
                "33.3.333/3331-33",
                "Rua dos desavisados",
                "9 0000-0000",
                true,
                new ArrayList<Document>(Arrays.asList(
                        documentOneCompetitorFour,
                        documentTwoCompetitorFour
                        )),
                new ArrayList<>(List.of(employeeCompetitorFour))
        );
        employeeCompetitorFour.setCompany(competitorTwoProcessTwo);


        Competitor competitorOneProcessThree = new Competitor(
                "44.4.444/4441-44",
                "Rua dos bem aventurados",
                "9 0000-0000",
                true,
                new ArrayList<Document>(Arrays.asList(
                        documentOneCompetitorFive,
                        documentTwoCompetitorFive
                )),
                new ArrayList<>(List.of(employeeCompetitorFive))
        );
        employeeCompetitorFive.setCompany(competitorOneProcessThree);

        Competitor competitorTwoProcessThree = new Competitor(
                "55.5.555/5551-55",
                "Rua dos moradores",
                "9 0000-0000",
                true,
                new ArrayList<Document>(Arrays.asList(
                        documentOneCompetitorSix,
                        documentTwoCompetitorSix
                )),
                new ArrayList<>(List.of(employeeCompetitorSix))
        );
        employeeCompetitorSix.setCompany(competitorTwoProcessThree);

//      TODO: Refatorar instâncias de Processos
        Process processOne = new Process(
                "111111",
                new ArrayList<Competitor>(Arrays.asList(competitorOneProcessOne, competitorTwoProcessOne)),
                new ArrayList<Document>(Arrays.asList(
                        documentOneProcessOne,
                        documentTwoProcessOne,
                        documentThreeProcessOne
                ))
        );

        Process processTwo = new Process(
                "222222",
                new ArrayList<Competitor>(Arrays.asList(competitorOneProcessTwo, competitorTwoProcessTwo)),
                new ArrayList<Document>(Arrays.asList(
                        documentOneProcessTwo,
                        documentTwoProcessTwo,
                        documentThreeProcessTwo
                ))
        );

        Process processThree = new Process(
                "33333",
                new ArrayList<Competitor>(Arrays.asList(competitorOneProcessThree, competitorTwoProcessThree)),
                new ArrayList<Document>(Arrays.asList(
                        documentOneProcessThree,
                        documentTwoProcessThree,
                        documentThreeProcessThree
                ))
        );

//      TODO: Setar processo em concorrentes

//      Retorna instância de Institution
        institution = new Institution(
                new ArrayList<Process>(Arrays.asList(processOne, processTwo, processThree)),
                new ArrayList<Employee>(Arrays.asList(
                        employeeOneInstitution,
                        employeeTwoInstitution,
                        employeeThreeInstitution,
                        employeeFourInstitution,
                        employeeFiveInstitution,
                        employeeSixInstitution,
                        employeeSevenInstitution,
                        employeeEightInstitution
                ))
        );
    }
}
