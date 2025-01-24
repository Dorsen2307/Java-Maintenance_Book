package services;

import db.SparePartList;
import db.TypeServiceList;
import interfaces.IBaseService;
import interfaces.IEdition;
import interfaces.IInputData;
import models.Job;
import models.SparePart;
import models.TypeService;

import java.util.Scanner;

public abstract class BaseService<T> implements IBaseService<T> {
    /**
     * Выводит вторичное меню, которое указывает допустимые действия для редактирования списков
     */
    public void getSecondMenu() {
        String textMenu = "1-Добавить\t2-Удалить\t3-Править\t0-Назад";
        System.out.println("--------------------------------------------");
        System.out.println(textMenu);
        System.out.println("--------------------------------------------");
    }

    public boolean inputItemSecondMenu(IEdition<T> item) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите пункт меню:");
        return selectMenu(sc, item);
    }

    public boolean selectMenu(Scanner sc, IEdition<T> obj) {

        if (obj instanceof SparePartList) {
            @SuppressWarnings("unchecked")
            IInputData<T> inputData = (IInputData<T>) new SparePart("", "", "", "", "");
            return menuHandler(sc, obj, inputData);
        } else if (obj instanceof TypeServiceList) {
            @SuppressWarnings("unchecked")
            IInputData<T> inputData = (IInputData<T>) new TypeService("");
            return menuHandler(sc, obj, inputData);
        } else {
            @SuppressWarnings("unchecked")
            IInputData<T> inputData = (IInputData<T>) new Job("", "", "", "", 0, "", 0, "");
            return menuHandler(sc, obj, inputData);
        }
    }

    public static int inputNumber(Scanner sc, String text) {
        System.out.print(text);
        return sc.nextInt();
    }

    @Override
    public boolean menuHandler(Scanner sc, IEdition<T> obj, IInputData<T> inputData) {
        switch (sc.nextInt()) {
            case 1:
                sc.nextLine();
                T newItem = inputData.inputData(sc);
                obj.add(newItem);
                return true;
            case 2:
                boolean isDel = obj.delete(inputNumber(sc, "Введите id: "));
                if (isDel) {
                    System.out.println("Запись успешно удалена.");
                } else {
                    System.out.println("Возникла ошибка при удалении!");
                }
                return true;
            case 3:
                int id = inputNumber(sc, "Введите id строки для изменения: ");
                sc.nextLine();
                System.out.println("\nВведите '-' чтобы не изменять!");
                T editedItem = inputData.inputData(sc);
                obj.edit(id, editedItem);
               return true;
            case 0:
                return false;
            default:
                System.out.println("Некорректный выбор.");
                return true;
        }
    }
}
