package com.promineotech.reportmgr.entity;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NewIcon {

  @NotNull
  private BigDecimal serialNumber;
  
  @NotNull
  private String partNumber;
  
  @NotNull
  private String status;
  
  @NotNull
  private String productionDate;
  
  @Length(max = 200)
  @Pattern(regexp = "[\\w\\s]*")
  private String notes;
  
 }
