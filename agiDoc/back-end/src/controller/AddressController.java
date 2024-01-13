package controller;

import exception.DatabaseException;
import model.address.Address;
import model.address.AddressAssociated;
import service.AddressService;

import java.util.Scanner;

public class AddressController {
    private final AddressService addressService;
    private final Scanner scanner;

    public AddressController() {
        addressService = new AddressService();
        scanner = new Scanner(System.in);
    }

    public void create(AddressAssociated addressAssociated, Integer associatedId) {
        try {
            Address address = new Address();

            System.out.print("Digite o logradouro: ");
            address.setStreet(scanner.nextLine().trim());

            System.out.print("Digite o bairro: ");
            address.setDistrict(scanner.nextLine().trim());

            System.out.print("Digite o número: ");
            address.setNumber(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Digite o complemento: ");
            address.setComplement(scanner.nextLine().trim());

            System.out.print("Digite a cidade: ");
            address.setCity(scanner.nextLine().trim());

            System.out.print("Digite o estado: ");
            address.setState(scanner.nextLine().trim());

            System.out.print("Digite o CEP: ");
            address.setZipCode(scanner.nextLine().trim());

            address.setAssociated(addressAssociated);
            address.setAssociatedId(associatedId);

            Address createdAddress = addressService.create(address);
            System.out.println("Endereço adicionado com sucesso: " + createdAddress);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public void list(AddressAssociated addressAssociated, Integer associatedId) {
        try {
            addressService.list()
                    .stream()
                    .filter(address -> address.getAssociated().equals(addressAssociated) && address.getAssociatedId().equals(associatedId))
                    .forEach(System.out::println);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
