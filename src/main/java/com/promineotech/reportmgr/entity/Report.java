package com.promineotech.reportmgr.entity;

import java.math.BigDecimal;
import java.util.Comparator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Report implements Comparable<Report> {
  private BigDecimal reportID;
  private BigDecimal serialNumber;
  private BigDecimal dcValueMin;
  private BigDecimal dcValueMax;
  private boolean battery;
  private boolean code_34;
  private boolean calibrated;
  private String notes;
  
  
  
  @JsonIgnore
  public BigDecimal getReportPK() {
    return reportID;
  }

  @Override
  public int compareTo(Report that) {
  //formatter:off
    return Comparator
        .comparing(Report::getReportID)
        .compare(this, that);
  
  //formatter:off  
  }
}
