package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component

public class DefaultReportDeleterDao implements ReportDeleterDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public void deleteReport(BigDecimal reportID) {
    String sql = ""
        + "DELETE FROM report "
        + "WHERE report_id = :report_id";
    
    SqlParams params = new SqlParams();
    params.sql = sql;
    params.source.addValue("report_id", reportID);
  
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
  }
  
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }

}
