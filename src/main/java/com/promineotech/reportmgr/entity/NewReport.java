package com.promineotech.reportmgr.entity;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data

public class NewReport {

  @NotNull
  private BigDecimal serialNumber;
  
  @NotNull
  private BigDecimal dcValueMin;
  
  @NotNull
  private BigDecimal dcValueMax;
  
  @NotNull
  private boolean battery;
  
  @NotNull
  private boolean code_34;
  
  @NotNull
  private boolean calibrated;
  
  @Length(max = 200)
  @Pattern(regexp = "[\\w\\s]*")
  private String notes;

  
 }
