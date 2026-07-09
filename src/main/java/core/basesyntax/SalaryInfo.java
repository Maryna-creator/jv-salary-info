package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate to = LocalDate.parse(dateTo, formatter);
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        int[] salaries = new int[names.length];
        StringBuilder stringBuilder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String record : data) {
            String[] parts = record.split(" ");
            String date = parts[0];
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);
            LocalDate recordDate = LocalDate.parse(date, formatter);
            if (!recordDate.isBefore(from) && !recordDate.isAfter(to)) {
                int index = Arrays.asList(names).indexOf(name);
                salaries[index] += hours * rate;
            }
        }
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i])
                    .append(" - ").append(salaries[i]);
            if (i < names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
