package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.Report;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultReportMgrDao implements ReportMgrDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Report> fetchReports(BigDecimal reportID) {
    log.debug("DAO: reportID={}", reportID);
       
    //formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM report ";
  //formatter:on
    
    Map<String, Object> params = new HashMap<>();
    
    if (!Objects.isNull(reportID)) {
      sql += "WHERE report_id = :report_id ";
      params.put("report_id",  reportID.toString());
    } else {
      params = null;
    }
    
    return jdbcTemplate.query(sql, params,
        new RowMapper<>() {

          @Override
          public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
            //formatter:off
            return Report.builder()
                .reportID(new BigDecimal(rs.getString("report_id")))
                .serialNumber(new BigDecimal(rs.getString("serial_number")))
                .dcValueMin(new BigDecimal(rs.getString("dc_value_min")))
                .dcValueMax(new BigDecimal(rs.getString("dc_value_max")))
                .battery(rs.getBoolean("battery"))
                .code_34(rs.getBoolean("code_34"))
                .calibrated(rs.getBoolean("calibrated"))
                .notes(rs.getString("notes"))
                .build();
           //formatter:on
          }});
  }
}
