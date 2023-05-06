package com.promineotech.reportmgr.service;

import java.math.BigDecimal;
import java.util.List;
import com.promineotech.reportmgr.entity.Report;

public interface ReportMgrService {

  List<Report> fetchReports(BigDecimal reportID);

}
