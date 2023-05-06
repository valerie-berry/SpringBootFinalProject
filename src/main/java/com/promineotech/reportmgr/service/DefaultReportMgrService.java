package com.promineotech.reportmgr.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.reportmgr.dao.ReportMgrDao;
import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.Report;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class DefaultReportMgrService implements ReportMgrService {

  @Autowired
  private ReportMgrDao reportMgrDao;

  @Transactional(readOnly = true)
  @Override
  public List<Report> fetchReports(BigDecimal reportID) {
    log.info("The fetchJeeps method was called with model={} and trim = {}");

    List<Report> reports = reportMgrDao.fetchReports(reportID);

    if (reports.isEmpty()) {
      String msg = String.format("No reports found with reportID=%s", reportID.toString());
      throw new NoSuchElementException(msg);
    }

    Collections.sort(reports);
    return reports;

  }
}
