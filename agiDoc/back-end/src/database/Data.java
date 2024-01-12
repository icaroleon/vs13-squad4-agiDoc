package database;

import model.document.Document;
import model.document.DocumentType;
import model.employee.Employee;
import model.competitor.Competitor;
import model.institution.Institution;
import model.process.Process;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Data {
    public static Institution institution;

    public static void seed() {
//      instâncias de documentos para Processos
        Document documentOneProcessOne = new Document("1", LocalDate.of(2024, 1, 1), "Instituição", "id:1", DocumentType.ATTACHMENTS, "documento: Contrato");
        Document documentTwoProcessOne = new Document("2", LocalDate.of(2024, 1, 2), "Instituição", "id:2", DocumentType.ATTACHMENTS, "documento: Edital");
        Document documentThreeProcessOne = new Document("3", LocalDate.of(2024, 1, 3), "Instituição", "id:3", DocumentType.ATTACHMENTS, "documento: Parecer Jurídico");

        Document documentOneProcessTwo = new Document("4", LocalDate.of(2024, 1, 5), "Instituição", "id:4", DocumentType.ATTACHMENTS, "documento: Contrato");
        Document documentTwoProcessTwo = new Document("5", LocalDate.of(2024, 1, 6), "Instituição", "id:5", DocumentType.ATTACHMENTS, "documento: Delegação de Competência");
        Document documentThreeProcessTwo = new Document("6", LocalDate.of(2024, 1, 7), "Instituição", "id:6", DocumentType.ATTACHMENTS, "documento: Termo de Referência");

        Document documentOneProcessThree = new Document("7", LocalDate.of(2024, 1, 8), "Instituição", "id:7", DocumentType.ATTACHMENTS, "documento: Contrato");
        Document documentTwoProcessThree = new Document("8", LocalDate.of(2024, 1, 9), "Instituição", "id:8", DocumentType.ATTACHMENTS, "documento: Manifestação");
        Document documentThreeProcessThree = new Document("9", LocalDate.of(2024, 1, 10), "Instituição", "id:9", DocumentType.ATTACHMENTS, "documento: Justificativa de Necessidade");

//      instâncias de documentos para Concorrentes
        Document documentOneCompetitorOne = new Document("10", LocalDate.of(2024, 1, 11), "Concorrente", "id:10", DocumentType.ATTACHMENTS, "documento: Certidão");
        Document documentTwoCompetitorOne = new Document("11", LocalDate.of(2024, 1, 12), "Concorrente", "id:12", DocumentType.ATTACHMENTS, "documento: Termo de Referência");

        Document documentOneCompetitorTwo = new Document("12", LocalDate.of(2024, 1, 13), "Concorrente", "id:13", DocumentType.ATTACHMENTS, "documento: Certidão");
        Document documentTwoCompetitorTwo = new Document("13", LocalDate.of(2024, 1, 14), "Concorrente", "id:14", DocumentType.ATTACHMENTS, "documento: Portaria");

        Document documentOneCompetitorThree = new Document("14", LocalDate.of(2024, 1, 15), "Concorrente", "id:15", DocumentType.ATTACHMENTS, "documento: Certidão");
        Document documentTwoCompetitorThree = new Document("15", LocalDate.of(2024, 1, 16), "Concorrente", "id:16", DocumentType.ATTACHMENTS, "documento: Anexos");

        Document documentOneCompetitorFour = new Document("16", LocalDate.of(2024, 1, 17), "Concorrente", "id:17", DocumentType.ATTACHMENTS, "documento: Certidão");
        Document documentTwoCompetitorFour = new Document("17", LocalDate.of(2024, 1, 18), "Concorrente", "id:18", DocumentType.ATTACHMENTS, "documento: Justificativa de Necessidade");

        Document documentOneCompetitorFive = new Document("18", LocalDate.of(2024, 1, 19), "Concorrente", "id:19", DocumentType.ATTACHMENTS, "documento: Certidão");
        Document documentTwoCompetitorFive = new Document("19", LocalDate.of(2024, 1, 20), "Concorrente", "id:10", DocumentType.ATTACHMENTS, "documento: Delegação de Competência");

        Document documentOneCompetitorSix = new Document("20", LocalDate.of(2024, 1, 21), "Concorrente", "id:21", DocumentType.ATTACHMENTS, "documento: Certidão");
        Document documentTwoCompetitorSix = new Document("21", LocalDate.of(2024, 1, 22), "Concorrente", "id:22", DocumentType.ATTACHMENTS, "documento: Portaria");

//      instâncias de Funcionários para Concorrentes
        Employee employeeCompetitorOne = new Employee("João", "joao_girassol", "4321", "Rua dos Girassóis", "9 0000-0000");
        Employee employeeCompetitorTwo = new Employee("Maria", "maria_rosa", "1234", "Rua das rosa", "9 0000-0000");
        Employee employeeCompetitorThree = new Employee("Everton", "everton_bromelia", "1234", "Rua das Bromélias", "9 0000-0000");
        Employee employeeCompetitorFour = new Employee("Laura", "laurinha_da_balada", "1234", "Rua das festa", "9 0000-0000");
        Employee employeeCompetitorFive = new Employee("Glória", "gloria_hibisco", "4321", "Rua dos Hibiscos", "9 0000-0000");
        Employee employeeCompetitorSix = new Employee("Robson", "Robson_dos_anjos", "1234", "Rua celeste", "9 0000-0000");

//      instâncias de Funcionários para Instituição
//      TODO: Setar company
        institution = new Institution("55.5.555/5551-55","Rua do Prato Limpo","9 0000-0000");
        Employee employeeOneInstitution = new Employee("Aron", "aron_usuario", "aron_senha", "aron_endereco", "9 0000-0000", institution);
        Employee employeeTwoInstitution = new Employee("Gabriel", "gabriel_usuario", "gabriel_senha", "gabriel_endereco", "9 1111-1111", institution);
        Employee employeeThreeInstitution = new Employee("Mari", "mari_usuario", "mari_senha", "mari_endereco", "9 2222-2222", institution);
        Employee employeeFourInstitution = new Employee("Ícaro", "icaro_usuario", "icaro_senha", "icaro_endereco", "9 3333-3333", institution);
        Employee employeeFiveInstitution = new Employee("Rodrigo", "rodrigo_usuario", "rodrigo_senha", "rodrigo_endereco", "9 4444-4444", institution);
        Employee employeeSixInstitution = new Employee("Vinicius", "vinicius_usuario", "vinicius_senha", "vinicius_endereco", "9 5555-5555", institution);
        Employee employeeSevenInstitution = new Employee("Camila", "camila_usuario", "camila_senha", "camila_endereco", "9 6666-6666", institution);
        Employee employeeEightInstitution = new Employee("Clara", "clara_usuario", "clara_senha", "clara_endereco", "9 7777-7777", institution);

//      instâncias de concorrentes para Processos
//      Setar company em Funcionários
        Competitor competitorOneProcessOne = new Competitor(
                "00.0.000/0001-00",
                "Rua dos bobos",
                "9 0000-0000",
                "InnovateTech Solutions"
        );
        competitorOneProcessOne.setDocuments(new ArrayList<>(Arrays.asList(
                documentOneCompetitorOne,
                documentTwoCompetitorOne
        )));
        competitorOneProcessOne.setEmployees(new ArrayList<>(List.of(employeeCompetitorOne)));

        employeeCompetitorOne.setCompany(competitorOneProcessOne);

        Competitor competitorTwoProcessOne = new Competitor(
                "11.1.111/1111-11",
                "Rua dos loucos",
                "9 0000-0000",
                "Quantum Dynamics Innovations"
        );
        competitorTwoProcessOne.setDocuments(new ArrayList<>(Arrays.asList(
                documentOneCompetitorTwo,
                documentTwoCompetitorTwo
        )));
        competitorTwoProcessOne.setEmployees(new ArrayList<>(List.of(employeeCompetitorTwo)));

        employeeCompetitorTwo.setCompany(competitorTwoProcessOne);

        Competitor competitorOneProcessTwo = new Competitor(
                "22.2.222/2221-22",
                "Rua dos azedos",
                "9 0000-0000",
                "Nexus Innovations Group"
        );
        competitorOneProcessTwo.setDocuments(new ArrayList<>(Arrays.asList(
                documentOneCompetitorThree,
                documentTwoCompetitorThree
        )));
        competitorOneProcessTwo.setEmployees(new ArrayList<>(List.of(employeeCompetitorThree)));

        employeeCompetitorThree.setCompany(competitorOneProcessTwo);

        Competitor competitorTwoProcessTwo = new Competitor(
                "33.3.333/3331-33",
                "Rua dos desavisados",
                "9 0000-0000",
                "Stratosphere Solutions"
        );
        competitorTwoProcessTwo.setDocuments(new ArrayList<>(Arrays.asList(
                documentOneCompetitorFour,
                documentTwoCompetitorFour
        )));
        competitorTwoProcessTwo.setEmployees(new ArrayList<>(List.of(employeeCompetitorFour)));

        employeeCompetitorFour.setCompany(competitorTwoProcessTwo);

        Competitor competitorOneProcessThree = new Competitor(
                "44.4.444/4441-44",
                "Rua dos bem aventurados",
                "9 0000-0000",
                "Zenith Enterprises"
        );
        competitorOneProcessThree.setDocuments(new ArrayList<>(Arrays.asList(
                documentOneCompetitorFive,
                documentTwoCompetitorFive
        )));
        competitorOneProcessThree.setEmployees(new ArrayList<>(List.of(employeeCompetitorFive)));

        employeeCompetitorFive.setCompany(competitorOneProcessThree);

        Competitor competitorTwoProcessThree = new Competitor(
                "55.5.555/5551-55",
                "Rua dos moradores",
                "9 0000-0000",
                "Nebula Innovations Inc."
        );
        competitorTwoProcessThree.setDocuments(new ArrayList<>(Arrays.asList(
                documentOneCompetitorSix,
                documentTwoCompetitorSix
        )));
        competitorTwoProcessThree.setEmployees(new ArrayList<>(List.of(employeeCompetitorSix)));

        employeeCompetitorSix.setCompany(competitorTwoProcessThree);

//      instâncias de Processos
        Process processOne = new Process(
                "Pregão Eletrônico",
                "pregrão eletrônico descrição",
                new ArrayList<>(Arrays.asList(competitorOneProcessOne, competitorTwoProcessOne)),
                new ArrayList<>(Arrays.asList(
                        documentOneProcessOne,
                        documentTwoProcessOne,
                        documentThreeProcessOne
                ))
        );

        Process processTwo = new Process(
                "Tomada de Preços",
                "tomada de preços descrição",
                new ArrayList<>(Arrays.asList(competitorOneProcessTwo, competitorTwoProcessTwo)),
                new ArrayList<>(Arrays.asList(
                        documentOneProcessTwo,
                        documentTwoProcessTwo,
                        documentThreeProcessTwo
                ))
        );

        Process processThree = new Process(
                "Licitação Pública para Contratação de Obra",
                "licitação pública para contratação de obra descrição",
                new ArrayList<>(Arrays.asList(competitorOneProcessThree, competitorTwoProcessThree)),
                new ArrayList<>(Arrays.asList(
                        documentOneProcessThree,
                        documentTwoProcessThree,
                        documentThreeProcessThree
                ))
        );

//      TODO: Setar processo em concorrentes
        competitorOneProcessOne.setProcess(processOne);
        competitorTwoProcessOne.setProcess(processOne);

        competitorOneProcessTwo.setProcess(processTwo);
        competitorTwoProcessTwo.setProcess(processTwo);

        competitorOneProcessThree.setProcess(processThree);
        competitorTwoProcessThree.setProcess(processThree);

//      Retorna instância de Institution
        institution = new Institution(
                "55.5.555/5551-55",
                "Rua do Prato Limpo",
                "9 0000-0000",
                new ArrayList<>(Arrays.asList(processOne, processTwo, processThree)),
                new ArrayList<>(Arrays.asList(
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
