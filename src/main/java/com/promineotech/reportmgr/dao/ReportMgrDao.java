package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;
import java.util.List;
import com.promineotech.reportmgr.entity.Report;

public interface ReportMgrDao {
  List<Report> fetchReports(BigDecimal reportID);

}