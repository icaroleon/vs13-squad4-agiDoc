//package database;
//
//import model.document.Document;
//import model.document.DocumentType;
//import model.user.User;
//import model.competitor.Competitor;
//import model.institution.Institution;
//import model.process.Process;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public abstract class Data {
//    public static Institution institution;
//
//    public static void seed() {
////      instâncias de documentos para Processos
//        Document documentOneProcessOne = new Document("1", LocalDate.of(2024, 1, 1), "Instituição", "id:1", DocumentType.ATTACHMENTS, "documento: Contrato");
//        Document documentTwoProcessOne = new Document("2", LocalDate.of(2024, 1, 2), "Instituição", "id:2", DocumentType.ATTACHMENTS, "documento: Edital");
//        Document documentThreeProcessOne = new Document("3", LocalDate.of(2024, 1, 3), "Instituição", "id:3", DocumentType.ATTACHMENTS, "documento: Parecer Jurídico");
//
//        Document documentOneProcessTwo = new Document("4", LocalDate.of(2024, 1, 5), "Instituição", "id:4", DocumentType.ATTACHMENTS, "documento: Contrato");
//        Document documentTwoProcessTwo = new Document("5", LocalDate.of(2024, 1, 6), "Instituição", "id:5", DocumentType.ATTACHMENTS, "documento: Delegação de Competência");
//        Document documentThreeProcessTwo = new Document("6", LocalDate.of(2024, 1, 7), "Instituição", "id:6", DocumentType.ATTACHMENTS, "documento: Termo de Referência");
//
//        Document documentOneProcessThree = new Document("7", LocalDate.of(2024, 1, 8), "Instituição", "id:7", DocumentType.ATTACHMENTS, "documento: Contrato");
//        Document documentTwoProcessThree = new Document("8", LocalDate.of(2024, 1, 9), "Instituição", "id:8", DocumentType.ATTACHMENTS, "documento: Manifestação");
//        Document documentThreeProcessThree = new Document("9", LocalDate.of(2024, 1, 10), "Instituição", "id:9", DocumentType.ATTACHMENTS, "documento: Justificativa de Necessidade");
//
////      instâncias de documentos para Concorrentes
//        Document documentOneCompetitorOne = new Document("10", LocalDate.of(2024, 1, 11), "Concorrente", "id:10", DocumentType.ATTACHMENTS, "documento: Certidão");
//        Document documentTwoCompetitorOne = new Document("11", LocalDate.of(2024, 1, 12), "Concorrente", "id:12", DocumentType.ATTACHMENTS, "documento: Termo de Referência");
//
//        Document documentOneCompetitorTwo = new Document("12", LocalDate.of(2024, 1, 13), "Concorrente", "id:13", DocumentType.ATTACHMENTS, "documento: Certidão");
//        Document documentTwoCompetitorTwo = new Document("13", LocalDate.of(2024, 1, 14), "Concorrente", "id:14", DocumentType.ATTACHMENTS, "documento: Portaria");
//
//        Document documentOneCompetitorThree = new Document("14", LocalDate.of(2024, 1, 15), "Concorrente", "id:15", DocumentType.ATTACHMENTS, "documento: Certidão");
//        Document documentTwoCompetitorThree = new Document("15", LocalDate.of(2024, 1, 16), "Concorrente", "id:16", DocumentType.ATTACHMENTS, "documento: Anexos");
//
//        Document documentOneCompetitorFour = new Document("16", LocalDate.of(2024, 1, 17), "Concorrente", "id:17", DocumentType.ATTACHMENTS, "documento: Certidão");
//        Document documentTwoCompetitorFour = new Document("17", LocalDate.of(2024, 1, 18), "Concorrente", "id:18", DocumentType.ATTACHMENTS, "documento: Justificativa de Necessidade");
//
//        Document documentOneCompetitorFive = new Document("18", LocalDate.of(2024, 1, 19), "Concorrente", "id:19", DocumentType.ATTACHMENTS, "documento: Certidão");
//        Document documentTwoCompetitorFive = new Document("19", LocalDate.of(2024, 1, 20), "Concorrente", "id:10", DocumentType.ATTACHMENTS, "documento: Delegação de Competência");
//
//        Document documentOneCompetitorSix = new Document("20", LocalDate.of(2024, 1, 21), "Concorrente", "id:21", DocumentType.ATTACHMENTS, "documento: Certidão");
//        Document documentTwoCompetitorSix = new Document("21", LocalDate.of(2024, 1, 22), "Concorrente", "id:22", DocumentType.ATTACHMENTS, "documento: Portaria");
//
////      instâncias de Funcionários para Concorrentes
//        User userCompetitorOne = new User("João", "joao_girassol", "4321", "Rua dos Girassóis", "9 0000-0000");
//        User userCompetitorTwo = new User("Maria", "maria_rosa", "1234", "Rua das rosa", "9 0000-0000");
//        User userCompetitorThree = new User("Everton", "everton_bromelia", "1234", "Rua das Bromélias", "9 0000-0000");
//        User userCompetitorFour = new User("Laura", "laurinha_da_balada", "1234", "Rua das festa", "9 0000-0000");
//        User userCompetitorFive = new User("Glória", "gloria_hibisco", "4321", "Rua dos Hibiscos", "9 0000-0000");
//        User userCompetitorSix = new User("Robson", "Robson_dos_anjos", "1234", "Rua celeste", "9 0000-0000");
//
////      instâncias de Funcionários para Instituição
////      TODO: Setar company
//        institution = new Institution("55.5.555/5551-55","Rua do Prato Limpo","9 0000-0000");
//        User userOneInstitution = new User("Aron", "aron_usuario", "aron_senha", "aron_endereco", "9 0000-0000", institution);
//        User userTwoInstitution = new User("Gabriel", "gabriel_usuario", "gabriel_senha", "gabriel_endereco", "9 1111-1111", institution);
//        User userThreeInstitution = new User("Mari", "mari_usuario", "mari_senha", "mari_endereco", "9 2222-2222", institution);
//        User userFourInstitution = new User("Ícaro", "icaro_usuario", "icaro_senha", "icaro_endereco", "9 3333-3333", institution);
//        User userFiveInstitution = new User("Rodrigo", "rodrigo_usuario", "rodrigo_senha", "rodrigo_endereco", "9 4444-4444", institution);
//        User userSixInstitution = new User("Vinicius", "vinicius_usuario", "vinicius_senha", "vinicius_endereco", "9 5555-5555", institution);
//        User userSevenInstitution = new User("Camila", "camila_usuario", "camila_senha", "camila_endereco", "9 6666-6666", institution);
//        User userEightInstitution = new User("Clara", "clara_usuario", "clara_senha", "clara_endereco", "9 7777-7777", institution);
//
////      instâncias de concorrentes para Processos
////      Setar company em Funcionários
//        Competitor competitorOneProcessOne = new Competitor(
//                "00.0.000/0001-00",
//                "Rua dos bobos",
//                "9 0000-0000",
//                "InnovateTech Solutions"
//        );
//        competitorOneProcessOne.setDocuments(new ArrayList<>(Arrays.asList(
//                documentOneCompetitorOne,
//                documentTwoCompetitorOne
//        )));
//        competitorOneProcessOne.setEmployees(new ArrayList<>(List.of(userCompetitorOne)));
//
//        userCompetitorOne.setCompany(competitorOneProcessOne);
//
//        Competitor competitorTwoProcessOne = new Competitor(
//                "11.1.111/1111-11",
//                "Rua dos loucos",
//                "9 0000-0000",
//                "Quantum Dynamics Innovations"
//        );
//        competitorTwoProcessOne.setDocuments(new ArrayList<>(Arrays.asList(
//                documentOneCompetitorTwo,
//                documentTwoCompetitorTwo
//        )));
//        competitorTwoProcessOne.setEmployees(new ArrayList<>(List.of(userCompetitorTwo)));
//
//        userCompetitorTwo.setCompany(competitorTwoProcessOne);
//
//        Competitor competitorOneProcessTwo = new Competitor(
//                "22.2.222/2221-22",
//                "Rua dos azedos",
//                "9 0000-0000",
//                "Nexus Innovations Group"
//        );
//        competitorOneProcessTwo.setDocuments(new ArrayList<>(Arrays.asList(
//                documentOneCompetitorThree,
//                documentTwoCompetitorThree
//        )));
//        competitorOneProcessTwo.setEmployees(new ArrayList<>(List.of(userCompetitorThree)));
//
//        userCompetitorThree.setCompany(competitorOneProcessTwo);
//
//        Competitor competitorTwoProcessTwo = new Competitor(
//                "33.3.333/3331-33",
//                "Rua dos desavisados",
//                "9 0000-0000",
//                "Stratosphere Solutions"
//        );
//        competitorTwoProcessTwo.setDocuments(new ArrayList<>(Arrays.asList(
//                documentOneCompetitorFour,
//                documentTwoCompetitorFour
//        )));
//        competitorTwoProcessTwo.setEmployees(new ArrayList<>(List.of(userCompetitorFour)));
//
//        userCompetitorFour.setCompany(competitorTwoProcessTwo);
//
//        Competitor competitorOneProcessThree = new Competitor(
//                "44.4.444/4441-44",
//                "Rua dos bem aventurados",
//                "9 0000-0000",
//                "Zenith Enterprises"
//        );
//        competitorOneProcessThree.setDocuments(new ArrayList<>(Arrays.asList(
//                documentOneCompetitorFive,
//                documentTwoCompetitorFive
//        )));
//        competitorOneProcessThree.setEmployees(new ArrayList<>(List.of(userCompetitorFive)));
//
//        userCompetitorFive.setCompany(competitorOneProcessThree);
//
//        Competitor competitorTwoProcessThree = new Competitor(
//                "55.5.555/5551-55",
//                "Rua dos moradores",
//                "9 0000-0000",
//                "Nebula Innovations Inc."
//        );
//        competitorTwoProcessThree.setDocuments(new ArrayList<>(Arrays.asList(
//                documentOneCompetitorSix,
//                documentTwoCompetitorSix
//        )));
//        competitorTwoProcessThree.setEmployees(new ArrayList<>(List.of(userCompetitorSix)));
//
//        userCompetitorSix.setCompany(competitorTwoProcessThree);
//
////      instâncias de Processos
//        Process processOne = new Process(
//                "Pregão Eletrônico",
//                "pregrão eletrônico descrição",
//                new ArrayList<>(Arrays.asList(competitorOneProcessOne, competitorTwoProcessOne)),
//                new ArrayList<>(Arrays.asList(
//                        documentOneProcessOne,
//                        documentTwoProcessOne,
//                        documentThreeProcessOne
//                ))
//        );
//
//        Process processTwo = new Process(
//                "Tomada de Preços",
//                "tomada de preços descrição",
//                new ArrayList<>(Arrays.asList(competitorOneProcessTwo, competitorTwoProcessTwo)),
//                new ArrayList<>(Arrays.asList(
//                        documentOneProcessTwo,
//                        documentTwoProcessTwo,
//                        documentThreeProcessTwo
//                ))
//        );
//
//        Process processThree = new Process(
//                "Licitação Pública para Contratação de Obra",
//                "licitação pública para contratação de obra descrição",
//                new ArrayList<>(Arrays.asList(competitorOneProcessThree, competitorTwoProcessThree)),
//                new ArrayList<>(Arrays.asList(
//                        documentOneProcessThree,
//                        documentTwoProcessThree,
//                        documentThreeProcessThree
//                ))
//        );
//
////      TODO: Setar processo em concorrentes
//        competitorOneProcessOne.setProcess(processOne);
//        competitorTwoProcessOne.setProcess(processOne);
//
//        competitorOneProcessTwo.setProcess(processTwo);
//        competitorTwoProcessTwo.setProcess(processTwo);
//
//        competitorOneProcessThree.setProcess(processThree);
//        competitorTwoProcessThree.setProcess(processThree);
//
////      Retorna instância de Institution
//        institution = new Institution(
//                "55.5.555/5551-55",
//                "Rua do Prato Limpo",
//                "9 0000-0000",
//                new ArrayList<>(Arrays.asList(processOne, processTwo, processThree)),
//                new ArrayList<>(Arrays.asList(
//                        userOneInstitution,
//                        userTwoInstitution,
//                        userThreeInstitution,
//                        userFourInstitution,
//                        userFiveInstitution,
//                        userSixInstitution,
//                        userSevenInstitution,
//                        userEightInstitution
//                ))
//        );
//    }
//}
