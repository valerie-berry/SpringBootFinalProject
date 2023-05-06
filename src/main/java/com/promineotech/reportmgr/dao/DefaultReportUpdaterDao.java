package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.reportmgr.entity.Report;

@Component

public class DefaultReportUpdaterDao implements ReportUpdaterDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public Optional<Report> fetchReport(BigDecimal reportID) {
    //@formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM report "
        + "WHERE report_id = :report_id";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("report_id", reportID);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new ReportResultSetExtractor()));
  }
  
  private SqlParams generateUpdateSql(Report newReportDetails) {
    // @formatter:off
    String sql = ""
        + "UPDATE report SET "
        + "serial_number = :serial_number,"
        + "dc_value_min = :dc_value_min,"
        + "dc_value_max = :dc_value_max,"
        + "battery = :battery,"
        + "code_34 = :code_34,"
        + "calibrated = :calibrated,"
        + "notes = ':notes' "
        + "WHERE report_id = :report_id";
        
    // @formatter:on
    
    SqlParams params = new SqlParams();
    params.sql = sql;

    params.source.addValue("serial_number", newReportDetails.getSerialNumber());
    params.source.addValue("dc_value_min", newReportDetails.getDcValueMin());
    params.source.addValue("dc_value_max", newReportDetails.getDcValueMax());
    params.source.addValue("battery", newReportDetails.isBattery());
    params.source.addValue("code_34", newReportDetails.isCode_34());
    params.source.addValue("calibrated", newReportDetails.isCalibrated());
    params.source.addValue("notes", newReportDetails.getNotes());
    params.source.addValue("report_id", newReportDetails.getReportID());
    
    return params;
  }

  @Override
  public Report updateReport(Report newReportDetails) {
    SqlParams params = generateUpdateSql(newReportDetails);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    
    // @formatter:off
    return Report.builder()
        .reportID(newReportDetails.getReportID())
        .serialNumber(newReportDetails.getSerialNumber())
        .dcValueMin(newReportDetails.getDcValueMin())
        .dcValueMax(newReportDetails.getDcValueMax())
        .battery(newReportDetails.isBattery())
        .code_34(newReportDetails.isCode_34())
        .calibrated(newReportDetails.isCalibrated())
        .build();
    // @formatter:on
     }
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  
  class ReportResultSetExtractor implements ResultSetExtractor<Report> {
    @Override
    public Report extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Report.builder()
          .reportID(rs.getBigDecimal("report_id"))
          .serialNumber(rs.getBigDecimal("serial_number"))
          .dcValueMin(rs.getBigDecimal("dc_value_min"))
          .dcValueMax(rs.getBigDecimal("dc_value_max"))
          .battery(rs.getBoolean("battery"))
          .code_34(rs.getBoolean("code_34"))
          .calibrated(rs.getBoolean("calibrated"))
          .build();
      // @formatter:on
    }

  }
}
