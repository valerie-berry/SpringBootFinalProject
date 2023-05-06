package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;

public interface ReportDeleterDao {
  void deleteReport(BigDecimal reportID);
}
