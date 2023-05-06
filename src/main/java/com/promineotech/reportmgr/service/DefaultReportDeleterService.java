package com.promineotech.reportmgr.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.reportmgr.dao.ReportDeleterDao;
@Service

public class DefaultReportDeleterService implements ReportDeleterService {

  @Autowired
  private ReportDeleterDao reportDeleterDao;
  
  @Override
  public void deleteReport(BigDecimal reportID) {
    reportDeleterDao.deleteReport(reportID);

  }

}
