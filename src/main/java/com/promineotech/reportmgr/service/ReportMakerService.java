package com.promineotech.reportmgr.service;

import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.NewReport;
import com.promineotech.reportmgr.entity.Report;


public interface ReportMakerService {
  Icon getIcon(NewReport newReport);
  Report createReport(NewReport newReport);

}
