package com.promineotech.reportmgr.service;

import com.promineotech.reportmgr.entity.NewReport;
import com.promineotech.reportmgr.entity.Report;

public interface ReportUpdaterService {
  Report getReport(Report newReportDetails);
  Report updateReport(Report newReportDetails);
}
