package data;

import entities.document.Document;
import entities.employee.Employee;
import entities.competitor.Competitor;
import entities.institution.Institution;
import entities.process.Process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
    public static Institution institution;

    public static void seed() {
//      instâncias de documentos para Processos
        Document documentOneProcessOne = new Document("1", "2024-01-01", "documento: Contrato");
        Document documentTwoProcessOne = new Document("2", "2024-01-02", "documento: Edital");
        Document documentThreeProcessOne = new Document("3", "2024-01-03", "documento: Parecer Jurídico");

        Document documentOneProcessTwo = new Document("4", "2024-01-04", "documento: Contrato");
        Document documentTwoProcessTwo = new Document("5", "2024-01-05", "documento: Delegação de Competência");
        Document documentThreeProcessTwo = new Document("6", "2024-01-06", "documento: Termo de Referência");

        Document documentOneProcessThree = new Document("7", "2024-01-07", "documento: Contrato");
        Document documentTwoProcessThree = new Document("8", "2024-01-08", "documento: Manifestação");
        Document documentThreeProcessThree = new Document("9", "2024-01-09", "documento: Justificativa de Necessidade");

//      instâncias de documentos para Concorrentes
        Document documentOneCompetitorOne = new Document("10", "2024-01-10","documento: Certidão");
        Document documentTwoCompetitorOne = new Document("11", "2024-01-11","documento: Termo de Referência");

        Document documentOneCompetitorTwo = new Document("12", "2024-01-12","documento: Certidão");
        Document documentTwoCompetitorTwo = new Document("13", "2024-01-13","documento: Portaria");

        Document documentOneCompetitorThree = new Document("14", "2024-01-14","documento: Certidão");
        Document documentTwoCompetitorThree = new Document("15", "2024-01-15","documento: Anexos");

        Document documentOneCompetitorFour = new Document("16", "2024-01-16","documento: Certidão");
        Document documentTwoCompetitorFour = new Document("17", "2024-01-17","documento: Justificativa de Necessidade");

        Document documentOneCompetitorFive = new Document("18", "2024-01-18","documento: Certidão");
        Document documentTwoCompetitorFive = new Document("19", "2024-01-19","documento: Delegação de Competência");

        Document documentOneCompetitorSix = new Document("20", "2024-01-20","documento: Certidão");
        Document documentTwoCompetitorSix = new Document("21", "2024-01-21","documento: Portaria");

//      instâncias de Funcionários para Concorrentes
//      TODO: Setar company
        Employee employeeCompetitorOne = new Employee("João", "joao_girassol", "4321", "Rua dos Girassóis", "9 0000-0000");
        Employee employeeCompetitorTwo = new Employee("Maria", "maria_rosa", "1234", "Rua das rosa", "9 0000-0000");
        Employee employeeCompetitorThree = new Employee("Everton", "everton_bromelia", "1234", "Rua das Bromélias", "9 0000-0000");
        Employee employeeCompetitorFour = new Employee("Laura", "laurinha_da_balada", "1234", "Rua das festa", "9 0000-0000");
        Employee employeeCompetitorFive = new Employee("Glória", "gloria_hibisco", "4321", "Rua dos Hibiscos", "9 0000-0000");
        Employee employeeCompetitorSix = new Employee("Robson", "Robson_dos_anjos", "1234", "Rua celeste", "9 0000-0000");

//      instâncias de Funcionários para Instituição
        Employee employeeOneInstitution = new Employee("Aron", "aron_usuario", "aron_senha", "aron_endereco", "9 0000-0000");
        Employee employeeTwoInstitution = new Employee("Gabriel", "gabriel_usuario", "gabriel_senha", "gabriel_endereco", "9 1111-1111");
        Employee employeeThreeInstitution = new Employee("Mari", "mari_usuario", "mari_senha", "mari_endereco", "9 2222-2222");
        Employee employeeFourInstitution = new Employee("Ícaro", "icaro_usuario", "icaro_senha", "icaro_endereco", "9 3333-3333");
        Employee employeeFiveInstitution = new Employee("Rodrigo", "rodrigo_usuario", "rodrigo_senha", "rodrigo_endereco", "9 4444-4444");
        Employee employeeSixInstitution = new Employee("Vinicius", "vinicius_usuario", "vinicius_senha", "vinicius_endereco", "9 5555-5555");
        Employee employeeSevenInstitution = new Employee("Camila", "camila_usuario", "camila_senha", "camila_endereco", "9 6666-6666");
        Employee employeeEightInstitution = new Employee("Clara", "clara_usuario", "clara_senha", "clara_endereco", "9 7777-7777");

//      instâncias de concorrentes para Processos
//      Setar company em Funcionários
        Competitor competitorOneProcessOne = new Competitor(
                "00.0.000/0001-00",
                "Rua dos bobos",
                "9 0000-0000",
                "InnovateTech Solutions",
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
                "Quantum Dynamics Innovations",
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
                "Nexus Innovations Group",
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
                "Stratosphere Solutions",
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
                "Zenith Enterprises",
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
                "Nebula Innovations Inc.",
                new ArrayList<Document>(Arrays.asList(
                        documentOneCompetitorSix,
                        documentTwoCompetitorSix
                )),
                new ArrayList<>(List.of(employeeCompetitorSix))
        );
        employeeCompetitorSix.setCompany(competitorTwoProcessThree);

//      instâncias de Processos
        Process processOne = new Process(
                "111111",
                "Pregão Eletrônico",
                "pregrão eletrônico descrição",
                new ArrayList<Competitor>(Arrays.asList(competitorOneProcessOne, competitorTwoProcessOne)),
                new ArrayList<Document>(Arrays.asList(
                        documentOneProcessOne,
                        documentTwoProcessOne,
                        documentThreeProcessOne
                ))
        );

        Process processTwo = new Process(
                "222222",
                "Tomada de Preços",
                "tomada de preços descrição",
                new ArrayList<Competitor>(Arrays.asList(competitorOneProcessTwo, competitorTwoProcessTwo)),
                new ArrayList<Document>(Arrays.asList(
                        documentOneProcessTwo,
                        documentTwoProcessTwo,
                        documentThreeProcessTwo
                ))
        );

        Process processThree = new Process(
                "33333",
                "Licitação Pública para Contratação de Obra",
                "licitação pública para contratação de obra descrição",
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
