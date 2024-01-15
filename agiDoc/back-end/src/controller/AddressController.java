package controller;

import exception.DatabaseException;
import model.address.Address;
import model.Associated;
import service.AddressService;

import java.util.Scanner;

public class AddressController {
    private final AddressService addressService = new AddressService();
    private final Scanner scanner = new Scanner(System.in);

    public Address create(Associated associated, Integer associatedId) {
        try {
            Address address = new Address();

            System.out.print("[Endereço] Logradouro: ");
            address.setStreet(scanner.nextLine().trim());

            System.out.print("[Endereço] Bairro: ");
            address.setDistrict(scanner.nextLine().trim());

            boolean isNotValid = true;
            while (isNotValid) {
                System.out.print("[Endereço] Número: ");
                String inputNumber = scanner.nextLine();

                try {
                    address.setNumber(Integer.parseInt(inputNumber));
                    isNotValid = false;
                } catch (Exception e) {
                    System.out.println("digite um número válido!");
                }
            }


            System.out.print("[Endereço] Complemento: ");
            address.setComplement(scanner.nextLine().trim());

            System.out.print("[Endereço] Cidade: ");
            address.setCity(scanner.nextLine().trim());

            System.out.print("[Endereço] Estado: ");
            address.setState(scanner.nextLine().trim());

            System.out.print("[Endereço] CEP: ");
            address.setZipCode(scanner.nextLine().trim());

            address.setAssociated(associated);
            address.setAssociatedId(associatedId);

            return addressService.create(address);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Integer id) {
        try {
            Address address = new Address();

            System.out.print("[Endereço] Logradouro: ");
            String street = scanner.nextLine().trim();
            if (!street.isEmpty()) address.setStreet(street);

            System.out.print("[Endereço] Bairro: ");
            String district = scanner.nextLine().trim();
            if (!district.isEmpty()) address.setDistrict(district);

            boolean isNotValid = true;
            while (isNotValid) {
                System.out.print("[Endereço] Número: ");
                String inputNumber = scanner.nextLine();

                try {
                    if (!inputNumber.isEmpty()) address.setNumber(Integer.parseInt(inputNumber));
                    isNotValid = false;
                } catch (Exception e) {
                    System.out.println("digite um número válido!");
                }
            }

            System.out.print("[Endereço] Complemento: ");
            String complement = scanner.nextLine().trim();
            if (!complement.isEmpty()) address.setComplement(complement);

            System.out.print("[Endereço] Cidade: ");
            String city = scanner.nextLine().trim();
            if (!city.isEmpty()) address.setCity(city);

            System.out.print("[Endereço] Estado: ");
            String state = scanner.nextLine().trim();
            if (!state.isEmpty()) address.setState(state);

            System.out.print("[Endereço] CEP: ");
            String zipCode = scanner.nextLine().trim();
            if (!zipCode.isEmpty()) address.setZipCode(zipCode);

            return addressService.update(id, address);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Integer id) {
        try {
            return addressService.delete(id);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void list(Associated associated, Integer associatedId) {
        try {
            addressService.list()
                    .stream()
                    .filter(address -> address.getAssociated().equals(associated) && address.getAssociatedId().equals(associatedId))
                    .forEach(System.out::println);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
