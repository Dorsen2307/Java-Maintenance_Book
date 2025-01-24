package db;

import interfaces.IEdition;
import models.Conveyor;
import models.SparePart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SparePartList implements IEdition<SparePart> {
    public static ArrayList<SparePart> sparePartList = new ArrayList<>(List.of(
            new SparePart(
                    "Фильтр масляный",
                    "Masuma",
                    "MFC2016",
                    "",
                    ""
            ),
            new SparePart(
                    "Фильтр масляный",
                    "Mando",
                    "EEOK0003Y",
                    "",
                    ""
            ),
            new SparePart(
                    "Фильтр салонный",
                    "Kortex",
                    "KC0142",
                    "",
                    ""
            )
    ));

    @Override
    public void add(SparePart item) {
        sparePartList.add(new SparePart(
                item.getName(),
                item.getBrand(),
                item.getCode(),
                item.getAddress(),
                item.getNote()
        ));
    }

    @Override
    public void edit(int id, SparePart item) {
        if (!item.getName().equals("-")) {
            sparePartList.get(id - 1).setName(item.getName());
            System.out.println("Изменено имя");
        }
        if (!item.getBrand().equals("-")) {
            sparePartList.get(id - 1).setBrand(item.getBrand());
            System.out.println("Изменена фирма");
        }
        if (!item.getCode().equals("-")) {
            sparePartList.get(id - 1).setCode(item.getCode());
            System.out.println("Изменен код");
        }
        if (!item.getAddress().equals("-")) {
            sparePartList.get(id - 1).setAddress(item.getAddress());
            System.out.println("Изменен адрес");
        }
        if (!item.getNote().equals("-")) {
            sparePartList.get(id - 1).setNote(item.getNote());
            System.out.println("Изменено примечание");
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            sparePartList.remove(id-1);
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
        int brandWidth = 5;
        int codeWidth = 7;
        int addressWidth = 5;
        int noteWidth = 10;

        // Инициализируем максимальные ширины
        for (int i = 0; i < sparePartList.size(); i++) {
            idWidth = Math.max(idWidth, String.valueOf(i + 1).length());
            nameWidth = Math.max(nameWidth, sparePartList.get(i).getName().length());
            brandWidth = Math.max(brandWidth, sparePartList.get(i).getBrand().length());
            codeWidth = Math.max(codeWidth, sparePartList.get(i).getCode().length());
            addressWidth = Math.max(addressWidth, sparePartList.get(i).getAddress().length());
            noteWidth = Math.max(noteWidth, sparePartList.get(i).getNote().length());
        }

        // Генерация границы таблицы
        Integer[] widthList = new Integer[]{
                idWidth,
                nameWidth,
                brandWidth,
                codeWidth,
                addressWidth,
                noteWidth
        };
        StringBuilder border = new StringBuilder("+");
        for (int width : widthList) {
            border.append("-".repeat(width + 2));
            border.append("+");
        }

        String headerFormat = "| %-"
                +idWidth+"s | %-"
                +nameWidth+"s | %-"
                +brandWidth+"s | %-"
                +codeWidth+"s | %-"
                +addressWidth+"s | %-"
                +noteWidth+"s |%n";
        String dataFormat = "| %-"
                +idWidth+"d | %-"
                +nameWidth+"s | %-"
                +brandWidth+"s | %-"
                +codeWidth+"s | %-"
                +addressWidth+"s | %-"
                +noteWidth+"s |%n";

        // Выводим границу таблицы
        System.out.println(border.toString());
        System.out.format(headerFormat, "id", "Название", "Бренд", "Артикул", "адрес", "Примечание");
        System.out.println(border.toString());

        for (int i = 0; i < sparePartList.size(); i++) {
            System.out.format(dataFormat,
                    i + 1,
                    sparePartList.get(i).getName(),
                    sparePartList.get(i).getBrand(),
                    sparePartList.get(i).getCode(),
                    sparePartList.get(i).getAddress(),
                    sparePartList.get(i).getNote()
            );

            System.out.println(border.toString());
        }
    }

    public void getSparePartFromList(Scanner sc, SparePartList spl) {
        spl.view();
        System.out.print("Укажите id нужной строки: ");

        if (sc.hasNextInt()) {
            int id = sc.nextInt();
            sc.nextLine();
            Conveyor.valueStringTwo = SparePartList.sparePartList.get(id-1).getName();
        } else {
            Conveyor.valueStringTwo = "-";
            sc.nextLine();
        }
    }
}
