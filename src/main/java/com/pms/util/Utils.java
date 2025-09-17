package com.pms.util;

import com.pms.entity.Project;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {
    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    public static Integer calculateProgress(LocalDate startDate, LocalDate endDate) {
        var today = LocalDate.now();
        var totalDays = ChronoUnit.DAYS.between(startDate, endDate);
        var elapsedDays = ChronoUnit.DAYS.between(startDate, today);

        if (totalDays <= 0) return 100; // startDate = endDate
        return Math.min(100, Math.max(0, ((int) Math.floor((double) elapsedDays / totalDays)) * 100));
    }

    public static Map<String, BigDecimal> generateMonthlySales(Project project) {
        Map<String, BigDecimal> monthlySales = new LinkedHashMap<>();

        int months = (project.getEndDate().getYear() - project.getStartDate().getYear()) * 12 + project.getEndDate().getMonthValue() - project.getStartDate().getMonthValue() + 1;
        BigDecimal monthlyRevenue = BigDecimal.valueOf(project.getRevenue()).divide(BigDecimal.valueOf(months), 2, RoundingMode.HALF_UP);

        for (int i = 0; i < months; i++) {
            LocalDate month = project.getStartDate().plusMonths(i).withDayOfMonth(1);
            monthlySales.put(month.format(MONTH_FORMATTER), monthlyRevenue);
        }

        return monthlySales;
    }

    public static BigDecimal getRevenueForMonth(Project project, int year, int month) {
        Map<String, BigDecimal> monthlySales = generateMonthlySales(project);

        String key = String.format("%04d-%02d", year, month);
        return monthlySales.getOrDefault(key, BigDecimal.ZERO);
    }
}
