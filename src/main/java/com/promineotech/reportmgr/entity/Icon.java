package com.promineotech.reportmgr.entity;

import java.math.BigDecimal;
import java.sql.Date;
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

public class Icon implements Comparable<Icon> {
  private BigDecimal serialNumber;
  private String partNumber;
  private String status;
  private String productionDate;
  private String notes;


  @JsonIgnore
  public BigDecimal getIconPK() {
    return serialNumber;
  }

  @Override
  public int compareTo(Icon that) {
    // formatter:off
    return Comparator
        .comparing(Icon::getSerialNumber)
        .compare(this, that);

    // formatter:off
  }
}
