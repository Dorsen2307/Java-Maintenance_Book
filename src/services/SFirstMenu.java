package services;

import db.JobList;
import db.SparePartList;
import db.TypeServiceList;
import interfaces.IBaseService;
import models.Conveyor;
import models.Job;
import models.SparePart;
import models.TypeService;

import java.util.Scanner;

public class SFirstMenu {
    public static void getMenu() {
        boolean exit = true;
        TypeServiceList tsl = new TypeServiceList();
        SparePartList spl = new SparePartList();
        JobList jl = new JobList();

        IBaseService<TypeService> tslService = new STypeService<>();
        IBaseService<SparePart> splService = new SSparePart<>();
        IBaseService<Job> jlService = new SJob<>();

        Conveyor.valueTSL = tsl;
        Conveyor.valueSPL = spl;

        do {
            getFirstMenu();
            exit = inputItemFirstMenu(jlService, tslService, splService, jl, tsl, spl);
        } while (exit);
    }

    public static void getFirstMenu() {
        String textMenu = "Списки:\n1 - Техническое обслуживание\n2 - Вид Обслуживания\n3 - Подходящие запчасти\n0 - Выход";

        System.out.println("-------------------------");
        System.out.println(textMenu);
        System.out.println("-------------------------");
    }

    public static boolean inputItemFirstMenu(
            IBaseService<Job> jlService,
            IBaseService<TypeService> tslService,
            IBaseService<SparePart>splService,
            JobList jl,
            TypeServiceList tsl,
            SparePartList spl
    ) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;

        System.out.print("Введите пункт меню:");

        if (sc.hasNextInt()) {
            switch (sc.nextInt()) {
                case 1:
                    do {
                        exit = jlService.getList(jl);
                    } while (exit);
                    break;
                case 2:
                    do {
                        exit = tslService.getList(tsl);
                    } while (exit);
                    break;
                case 3:
                    do {
                        exit = splService.getList(spl);
                    } while (exit);
                    break;
                case 0:
                    return false;
            }
        }
        return true;
    }
}
