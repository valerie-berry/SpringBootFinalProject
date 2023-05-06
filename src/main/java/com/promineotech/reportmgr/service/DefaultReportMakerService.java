package com.promineotech.reportmgr.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.reportmgr.dao.ReportMakerDao;
import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.NewReport;
import com.promineotech.reportmgr.entity.Report;

@Service
public class DefaultReportMakerService implements ReportMakerService {

  @Autowired
  private ReportMakerDao reportMakerDao;

  @Override
  public Icon getIcon(NewReport newReport) {
    return reportMakerDao.fetchIcon(newReport.getSerialNumber())
        .orElseThrow(() -> new NoSuchElementException(
            "ICON with serialNumber=" + newReport.getSerialNumber() + " was not found"));
  }

  @Override
  public Report createReport(NewReport newReport) {
    Icon icon = getIcon(newReport);
    BigDecimal dcValueMin = newReport.getDcValueMin();
    BigDecimal dcValueMax = newReport.getDcValueMax();
    boolean battery = newReport.isBattery();
    boolean code_34 = newReport.isCode_34();
    boolean calibrated = newReport.isCalibrated();
    String notes = newReport.getNotes();

    return reportMakerDao.saveReport(icon, dcValueMin, dcValueMax, battery, code_34, calibrated, notes);
  }

}
