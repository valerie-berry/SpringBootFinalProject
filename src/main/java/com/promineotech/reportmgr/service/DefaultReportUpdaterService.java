package com.promineotech.reportmgr.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.reportmgr.dao.ReportUpdaterDao;
import com.promineotech.reportmgr.entity.Report;

@Service
public class DefaultReportUpdaterService implements ReportUpdaterService {

  @Autowired
  private ReportUpdaterDao reportUpdaterDao;

  
  @Override
  public Report getReport(Report newReportDetails) {
    return reportUpdaterDao.fetchReport(newReportDetails.getReportID())
        .orElseThrow(() -> new NoSuchElementException(
            "Report with serialNumber=" + newReportDetails.getReportID() + " was not found"));
  }

  @Override
  public Report updateReport(Report newReportDetails) {
    return reportUpdaterDao.updateReport(newReportDetails);
  }

}
