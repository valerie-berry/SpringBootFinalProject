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
import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.Report;

@Component

public class DefaultReportMakerDao implements ReportMakerDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  private SqlParams generateInsertSql(Icon icon, BigDecimal dcValueMin, BigDecimal dcValueMax, boolean battery,
      boolean code_34, boolean calibrated, String notes) {
    // @formatter:off
    String sql = ""
        + "INSERT INTO report ("
        + "serial_number, dc_value_min, dc_value_max, code_34, battery, calibrated, notes"
        + ") VALUES ("
        + ":serial_number, :dc_value_min, :dc_value_max, :code_34, :battery, :calibrated, :notes"
        + ")";
    // @formatter:on
    
    SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("serial_number", icon.getSerialNumber());
    params.source.addValue("dc_value_min", dcValueMin);
    params.source.addValue("dc_value_max", dcValueMax);
    params.source.addValue("code_34", code_34);
    params.source.addValue("battery", battery);
    params.source.addValue("calibrated", calibrated);
    params.source.addValue("notes", notes);
    
    return params;
  }
  
  @Override
  public Optional<Icon> fetchIcon(BigDecimal serialNumber) {
    //@formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM icon "
        + "WHERE serial_number = :serial_number";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("serial_number", serialNumber);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new IconResultSetExtractor()));
  }

  @Override
  public Report saveReport(Icon icon, BigDecimal dcValueMin, BigDecimal dcValueMax, boolean battery,
      boolean code_34, boolean calibrated, String notes) {
    SqlParams params = generateInsertSql(icon, dcValueMin, dcValueMax, battery, code_34, calibrated, notes);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    
    Long orderPK = keyHolder.getKey().longValue();
    return Report.builder()
      //@formatter:off
        .serialNumber(icon.getSerialNumber())
        .dcValueMin(dcValueMin)
        .dcValueMax(dcValueMax)
        .battery(battery)
        .code_34(code_34)
        .calibrated(calibrated)
        .notes(notes)
        .build();
      //@formatter:on
   }

  class IconResultSetExtractor implements ResultSetExtractor<Icon> {
    @Override
    public Icon extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Icon.builder()
          .serialNumber(rs.getBigDecimal("serial_number"))
          .partNumber(rs.getString("part_number"))
          .status(rs.getString("status"))
          .productionDate(rs.getString("production_date"))
          .status(rs.getString("notes"))
          .build();
      // @formatter:on
    }

  }
  
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}
