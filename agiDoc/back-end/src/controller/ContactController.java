package controller;

import exception.DatabaseException;
import model.Associated;
import model.contact.Contact;
import model.contact.ContactPhoneType;
import service.ContactService;

import java.util.Scanner;

public class ContactController {
    private final ContactService contactService = new ContactService();
    private final Scanner scanner = new Scanner(System.in);

    public Contact create(Associated associated, Integer associatedId) {
        try {
            Contact contact = new Contact();

            System.out.print("[Contato] Nome: ");
            contact.setName(scanner.nextLine().trim());

            System.out.print("[Contato] E-mail (deixar vazio caso não queira cadastrar): ");
            String email = scanner.nextLine().trim();
            if (!email.isEmpty()) contact.setEmail(email);

            System.out.print("[Contato] Número de Telefone: ");
            contact.setPhone(scanner.nextLine().trim());

            boolean isValidOption = false;
            do {
                System.out.println("1 - FIXO");
                System.out.println("2 - MÓVEL");
                System.out.print("[Contato] Tipo do Número de Telefone: ");
                Integer phoneType = scanner.nextInt();
                scanner.nextLine();

                if (phoneType.equals(1) || phoneType.equals(2)) {
                    contact.setPhoneType(ContactPhoneType.ofType(phoneType));
                    isValidOption = true;
                } else
                    System.out.println("Opção inválida!");
            } while (!isValidOption);

            contact.setAssociated(associated);
            contact.setAssociatedId(associatedId);

            return contactService.create(contact);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Integer id) {
        try {
            Contact contact = new Contact();

            System.out.print("[Contato] Nome: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) contact.setName(name);

            System.out.print("[Contato] E-mail: ");
            String email = scanner.nextLine().trim();
            if (!email.isEmpty()) contact.setEmail(email);

            System.out.print("[Contato] Número de Telefone: ");
            String phone = scanner.nextLine().trim();
            if (!phone.isEmpty()) contact.setPhone(phone);

            boolean isValidOption = false;
            do {
                System.out.println("1 - FIXO");
                System.out.println("2 - MÓVEL");
                System.out.print("[Contato] Tipo do Número de Telefone: ");
                Integer phoneType = scanner.nextInt();
                scanner.nextLine();

                if (phoneType.equals(1) || phoneType.equals(2)) {
                    contact.setPhoneType(ContactPhoneType.ofType(phoneType));
                    isValidOption = true;
                } else if (phoneType <= 0)
                    isValidOption = true;
                else
                    System.out.println("Opção inválida!");
            } while (!isValidOption);

            return contactService.update(id, contact);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Integer id) {
        try {
            return contactService.delete(id);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void list(Associated associated, Integer associatedId) {
        try {
            contactService.list()
                    .stream()
                    .filter(address -> address.getAssociated().equals(associated) && address.getAssociatedId().equals(associatedId))
                    .forEach(System.out::println);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
