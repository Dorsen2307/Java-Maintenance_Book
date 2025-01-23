package models;

import interfaces.IInputData;

import java.util.Scanner;

public class TypeService implements IInputData<TypeService> {
    private String name;

    public TypeService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public TypeService inputData(Scanner sc) {
        System.out.print("Введите вид обслуживания: ");
        String type = sc.nextLine();

        return new TypeService(type);
    }
}
