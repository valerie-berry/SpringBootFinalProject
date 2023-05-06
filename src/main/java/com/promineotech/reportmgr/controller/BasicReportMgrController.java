package com.promineotech.reportmgr.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.reportmgr.entity.NewReport;
import com.promineotech.reportmgr.entity.Report;
import com.promineotech.reportmgr.service.ReportDeleterService;
import com.promineotech.reportmgr.service.ReportMakerService;
import com.promineotech.reportmgr.service.ReportMgrService;
import com.promineotech.reportmgr.service.ReportUpdaterService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class BasicReportMgrController implements ReportMgrController {

  @Autowired
  private ReportMgrService reportMgrService;
  
  @Autowired
  ReportMakerService reportMakerService;
  
  @Autowired
  ReportUpdaterService reportUpdaterService;
  
  @Autowired
  ReportDeleterService reportDeleterService;
  
  
  @Override
  public List<Report> fetchReports(BigDecimal reportID) {
    log.debug("Reports to fetch={}");
    return reportMgrService.fetchReports(reportID);
  }

  @Override
  public Report createReport(NewReport newReport) {
    log.debug("Report to create={}", newReport);
    return reportMakerService.createReport(newReport);
  }

  @Override
  public Report updateReport(@Valid Report newReportDetails) {
    log.debug("Report to update={}", newReportDetails);
    return reportUpdaterService.updateReport(newReportDetails);
  }

  @Override
  public void deleteReport(@Valid BigDecimal reportID) {
    log.debug("ReportID to delete={}", reportID);
    reportDeleterService.deleteReport(reportID);
  }

  

}
