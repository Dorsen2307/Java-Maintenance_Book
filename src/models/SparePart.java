package models;

import interfaces.IInputData;

import java.util.ArrayList;
import java.util.Scanner;

public class SparePart implements IInputData<SparePart> {
    private String name;
    private String brand;
    private String code;
    private String address;
    private String note;

    public SparePart(
            String name,
            String firm,
            String code,
            String address,
            String note
    ) {
        this.name = name;
        this.brand = firm;
        this.code = code;
        this.address = address;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public SparePart inputData(Scanner sc) {
        System.out.print("Введите имя: ");
        String name = sc.nextLine();

        System.out.print("Введите фирму: ");
        String brand = sc.nextLine();

        System.out.print("Введите код: ");
        String code = sc.nextLine();

        System.out.print("Введите адрес: ");
        String address = sc.nextLine();

        System.out.print("Введите примечание: ");
        String notes = sc.nextLine();

        return new SparePart(name, brand, code, address, notes);
    }
}