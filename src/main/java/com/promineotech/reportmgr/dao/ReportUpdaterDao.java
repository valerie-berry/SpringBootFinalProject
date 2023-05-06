package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;
import java.util.Optional;
import com.promineotech.reportmgr.entity.Report;

public interface ReportUpdaterDao {
  Optional<Report> fetchReport(BigDecimal reportID);
  Report updateReport(Report newReportDetails);
}
