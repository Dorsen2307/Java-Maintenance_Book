package models;

import db.SparePartList;
import db.TypeServiceList;
import interfaces.IInputData;

import java.util.Scanner;

public class Job implements IInputData<Job> {
    private String typeService;
    private String periodicityRegulations;
    private String plannedDate;
    private String lastServiceDate;
    private int quantity;
    private String sparePart;
    private int sparePartQuantity;
    private String note;

    public Job(
            String typeService,
            String periodicityRegulations,
            String plannedDate,
            String lastServiceDate,
            int quantity,
            String sparePart,
            int sparePartQuantity,
            String note
    ) {
        this.typeService = typeService;
        this.periodicityRegulations = periodicityRegulations;
        this.plannedDate = plannedDate;
        this.lastServiceDate = lastServiceDate;
        this.quantity = quantity;
        this.sparePart = sparePart;
        this.sparePartQuantity = sparePartQuantity;
        this.note = note;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public String getPeriodicityRegulations() {
        return periodicityRegulations;
    }

    public void setPeriodicityRegulations(String periodicityRegulations) {
        this.periodicityRegulations = periodicityRegulations;
    }

    public String getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(String plannedDate) {
        this.plannedDate = plannedDate;
    }

    public String getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(String lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSparePart() {
        return sparePart;
    }

    public void setSparePart(String sparePart) {
        this.sparePart = sparePart;
    }

    public int getSparePartQuantity() {
        return sparePartQuantity;
    }

    public void setSparePartQuantity(int sparePartQuantity) {
        this.sparePartQuantity = sparePartQuantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public Job inputData(Scanner sc) {
        int quantity, sparePartQuantity;
        String typeService, sparePart;
        TypeServiceList tsl = Conveyor.valueTSL;
        SparePartList spl = Conveyor.valueSPL;

        System.out.print("Введите вид обслуживания: ");
        tsl.getTypeServiceFromList(sc, tsl);
        if (!Conveyor.valueStringOne.equals("-")) {
            typeService = Conveyor.valueStringOne;
        } else {
            typeService = Conveyor.valueStringOne;
        }

        System.out.print("Введите периодичность по регламенту: ");
        String periodicityRegulations = sc.nextLine();

        System.out.print("Введите планируемую дату обслуживания: ");
        String plannedDate = sc.nextLine();

        System.out.print("Введите последнюю дату обслуживания: ");
        String lastServiceDate = sc.nextLine();

        System.out.print("Введите необходимое количество запчастей: ");
        if (sc.hasNextInt()) {
            quantity = sc.nextInt();
        } else {
            quantity = -1;
        }
        sc.nextLine();

        System.out.print("Введите подходящую запчасть: ");
        spl.getSparePartFromList(sc, spl);
        if (!Conveyor.valueStringTwo.equals("-")) {
            sparePart = Conveyor.valueStringTwo;
        } else {
            sparePart = Conveyor.valueStringTwo;
        }

        System.out.print("Введите кол-во запчастей в наличии: ");
        if (sc.hasNextInt()) {
            sparePartQuantity = sc.nextInt();
        } else {
            sparePartQuantity = -1;
        }
        sc.nextLine();

        System.out.print("Введите примечание: ");
        String notes = sc.nextLine();

        return new Job(
                typeService,
                periodicityRegulations,
                plannedDate,
                lastServiceDate,
                quantity,
                sparePart,
                sparePartQuantity,
                notes
        );
    }
}
