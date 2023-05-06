package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;
import java.util.Optional;
import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.Report;

public interface ReportMakerDao {
  Optional<Icon> fetchIcon(BigDecimal serialNumber);
  Report saveReport(Icon icon, BigDecimal dcValueMin,BigDecimal dcValueMax,
      boolean battery, boolean code_34, boolean calibrated,String notes);
}
