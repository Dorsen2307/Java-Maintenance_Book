package db;

import interfaces.IEdition;
import models.Conveyor;
import models.TypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TypeServiceList implements IEdition<TypeService> {
    public static ArrayList<TypeService> typeServiceList = new ArrayList<>(List.of(
            new TypeService("Фильтр масляный"),
            new TypeService("Фильтр салонный"),
            new TypeService("Фильтр воздушный"),
            new TypeService("Фильтр топливный"),
            new TypeService("ГРМ")
    ));

    @Override
    public void add(TypeService item) {
        typeServiceList.add(new TypeService(item.getName()));
    }

    @Override
    public void edit(int id, TypeService item) {
        if (!item.getName().equals("-")) {
            typeServiceList.get(id - 1).setName(item.getName());
            System.out.println("Изменено имя");
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            typeServiceList.remove(id-1);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void view() {
        // Определяем минимальные ширины для каждого столбца
        int idWidth = 2;
        int nameWidth = 8;

        // Инициализируем максимальные ширины
        for (int i = 0; i < typeServiceList.size(); i++) {
            idWidth = Math.max(idWidth, String.valueOf(i + 1).length());
            nameWidth = Math.max(nameWidth, typeServiceList.get(i).getName().length());
        }

        // Генерация границы таблицы
        Integer[] widthList = new Integer[]{
                idWidth,
                nameWidth,
        };
        StringBuilder border = new StringBuilder("+");
        for (int width : widthList) {
            border.append("-".repeat(width + 2));
            border.append("+");
        }

        String headerFormat = "| %-"
                +idWidth+"s | %-"
                +nameWidth+"s |%n";
        String dataFormat = "| %-"
                +idWidth+"d | %-"
                +nameWidth+"s |%n";

        // Выводим границу таблицы
        System.out.println(border.toString());
        System.out.format(headerFormat, "id", "Название");
        System.out.println(border.toString());

        for (int i = 0; i < typeServiceList.size(); i++) {
            System.out.format(dataFormat, i + 1, typeServiceList.get(i).getName());

            System.out.println(border.toString());
        }
    }

    public void getTypeServiceFromList(Scanner sc, TypeServiceList tsl) {

        tsl.view();
        System.out.print("Укажите id нужной строки: ");

        if (sc.hasNextInt()) {
            int id = sc.nextInt();
            sc.nextLine();
            Conveyor.valueStringOne = TypeServiceList.typeServiceList.get(id-1).getName();
        } else {
            Conveyor.valueStringOne = "-";
        }
    }
}
