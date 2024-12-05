package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.service;

import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model.*;
import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // Створення нового звіту
    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    // Отримання звіту за ID
    public Report getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }

    // Отримання всіх звітів
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // Оновлення звіту
    public Report updateReport(Long id, Report newReport) {
        Report report = getReportById(id);
        report.setReportType(newReport.getReportType());
        report.setCreationDate(newReport.getCreationDate());
        report.setText(newReport.getText());
        report.setFileLink(newReport.getFileLink());
        report.setEventLogID(newReport.getEventLogID());
        return reportRepository.save(report);
    }

    // Видалення звіту
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}