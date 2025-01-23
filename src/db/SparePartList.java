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
        System.out.println("\nid\tИмя\tФирма\tКод\tАдрес\tПримечание");
        for (int i = 0; i < sparePartList.size(); i++) {
            System.out.println(
                    i + 1 + "\t"
                    + sparePartList.get(i).getName() + "\t"
                    + sparePartList.get(i).getBrand() + "\t"
                    + sparePartList.get(i).getCode() + "\t"
                    + sparePartList.get(i).getAddress() + "\t"
                    + sparePartList.get(i).getNote()
            );
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
