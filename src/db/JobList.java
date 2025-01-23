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
        System.out.format("+----+------------------+---------------+-------------+-------------------+------------+-------------------+-----------+-----------------+%n");
        System.out.format("|    |                  | Периодичность | Планируемая | Последняя         |            | Подходящая        | Запчастей |                 |%n");
        System.out.format("| id | Вид обслуживания | по регламенту | дата        | дата обслуживания | Количество | запчасть          | в наличии | Примечание      |%n");
        System.out.format("+----+------------------+---------------+-------------+-------------------+------------+-------------------+-----------+-----------------+%n");
        String leftAlignment = "| %-2d | %-16s | %-13s | %-11s | %-17s | %-10d | %-17s | %-9s | %-15s |%n";
        for (int i = 0; i < jobList.size(); i++) {
            System.out.format(
                    leftAlignment,
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
            System.out.format("+----+------------------+---------------+-------------+-------------------+------------+-------------------+-----------+-----------------+%n");
        }
    }
}
