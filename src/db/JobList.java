package db;

import interfaces.IEdition;
import models.Job;

import java.util.ArrayList;
import java.util.List;

public class JobList implements IEdition<Job> {
    public static ArrayList<Job> jobList = new ArrayList<>(List.of(
            new Job(
                    "Фильтр масляный",
                    "7500 км",
                    "17.03.2025",
                    "10.10.2024",
                    1,
                    "Masuma (MFC2016)",
                    1,
                    ""
                    ),
            new Job(
                    "Фильтр воздушный",
                    "15000 км",
                    "17.03.2025",
                    "10.10.2024",
                    1,
                    "Hyundai (WFC2010)",
                    1,
                    ""
                    ),
            new Job(
                    "Колодки задние",
                    "по износу",
                    "",
                    "10.10.2024",
                    2,
                    "Abas (ab145010)",
                    1,
                    ""
                    )
    ));

    @Override
    public void add(Job item) {
        jobList.add(new Job(
                item.getTypeService(),
                item.getPeriodicityRegulations(),
                item.getPlannedDate(),
                item.getLastServiceDate(),
                item.getQuantity(),
                item.getSparePart(),
                item.getSparePartQuantity(),
                item.getNote()
        ));
    }

    @Override
    public void edit(int id, Job item) {
        if (!item.getTypeService().equals("-")) {
            jobList.get(id - 1).setTypeService(item.getTypeService());
            System.out.println("Изменен вид обслуживания");
        }
        if (!item.getPeriodicityRegulations().equals("-")) {
            jobList.get(id - 1).setPeriodicityRegulations(item.getPeriodicityRegulations());
            System.out.println("Изменена периодичность по регламенту");
        }
        if (!item.getPlannedDate().equals("-")) {
            jobList.get(id - 1).setPlannedDate(item.getPlannedDate());
            System.out.println("Изменена планируемая дата");
        }
        if (!item.getLastServiceDate().equals("-")) {
            jobList.get(id - 1).setLastServiceDate(item.getLastServiceDate());
            System.out.println("Изменена последняя дата обслуживания");
        }
        if (item.getQuantity() != (-1)) {
            jobList.get(id - 1).setQuantity(item.getQuantity());
            System.out.println("Изменено необходимое количество запчастей");
        }
        if (!item.getSparePart().equals("-")) {
            jobList.get(id - 1).setSparePart(item.getSparePart());
            System.out.println("Изменена подходящая запчасть");
        }
        if (item.getSparePartQuantity() != (-1)) {
            jobList.get(id - 1).setSparePartQuantity(item.getSparePartQuantity());
            System.out.println("Изменено количество запчастей в наличии");
        }
        if (!item.getNote().equals("-")) {
            jobList.get(id - 1).setNote(item.getNote());
            System.out.println("Изменено примечание");
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            jobList.remove(id-1);
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
        int typeServiceWidth = 16;
        int periodicityWidth = 13;
        int plannedDateWidth = 11;
        int lastServiceDateWidth = 17;
        int quantityWidth = 10;
        int sparePartWidth = 10;
        int sparePartQuantityWidth = 9;
        int noteWidth = 10;

        // Инициализируем максимальные ширины
        for (int i = 0; i < jobList.size(); i++) {
            idWidth = Math.max(idWidth, String.valueOf(i + 1).length());
            typeServiceWidth = Math.max(typeServiceWidth, jobList.get(i).getTypeService().length());
            periodicityWidth = Math.max(periodicityWidth, jobList.get(i).getPeriodicityRegulations().length());
            plannedDateWidth = Math.max(plannedDateWidth, jobList.get(i).getPlannedDate().length());
            lastServiceDateWidth = Math.max(lastServiceDateWidth, jobList.get(i).getLastServiceDate().length());
            quantityWidth = Math.max(quantityWidth, String.valueOf(jobList.get(i).getQuantity()).length());
            sparePartWidth = Math.max(sparePartWidth, jobList.get(i).getSparePart().length());
            sparePartQuantityWidth = Math.max(sparePartQuantityWidth, String.valueOf(jobList.get(i).getSparePartQuantity()).length());
            noteWidth = Math.max(noteWidth, jobList.get(i).getNote().length());
        }

        // Генерация границы таблицы
        Integer[] widthList = new Integer[]{
                idWidth,
                typeServiceWidth,
                periodicityWidth,
                plannedDateWidth,
                lastServiceDateWidth,
                quantityWidth,
                sparePartWidth,
                sparePartQuantityWidth,
                noteWidth
        };
        StringBuilder border = new StringBuilder("+");
        for (int width : widthList) {
            border.append("-".repeat(width + 2));
            border.append("+");
        }

        String headerFormat = "| %-"
                +idWidth+"s | %-"
                +typeServiceWidth+"s | %-"
                +periodicityWidth+"s | %-"
                +plannedDateWidth+"s | %-"
                +lastServiceDateWidth+"s | %-"
                +quantityWidth+"s | %-"
                +sparePartWidth+"s | %-"
                +sparePartQuantityWidth+"s | %-"
                +noteWidth+"s |%n";
        String dataFormat = "| %-"
                +idWidth+"d | %-"
                +typeServiceWidth+"s | %-"
                +periodicityWidth+"s | %-"
                +plannedDateWidth+"s | %-"
                +lastServiceDateWidth+"s | %-"
                +quantityWidth+"d | %-"
                +sparePartWidth+"s | %-"
                +sparePartQuantityWidth+"d | %-"
                +noteWidth+"s |%n";

        // Выводим границу таблицы
        System.out.println(border);
        System.out.format(headerFormat, "  ", "                ", "Периодичность", "Планируемая", "Последняя        ", "          ", "Подходящая", "Запчастей", "          ");
        System.out.format(headerFormat, "id", "Вид обслуживания", "по регламенту", "дата       ", "дата обслуживания", "Количество", "запчасть  ", "в наличии", "Примечание");
        System.out.println(border);

        for (int i = 0; i < jobList.size(); i++) {
            System.out.format(
                    dataFormat,
                    i + 1,
                    jobList.get(i).getTypeService(),
                    jobList.get(i).getPeriodicityRegulations(),
                    jobList.get(i).getPlannedDate(),
                    jobList.get(i).getLastServiceDate(),
                    jobList.get(i).getQuantity(),
                    jobList.get(i).getSparePart(),
                    jobList.get(i).getSparePartQuantity(),
                    jobList.get(i).getNote()
            );

            System.out.println(border);
        }
    }
}
