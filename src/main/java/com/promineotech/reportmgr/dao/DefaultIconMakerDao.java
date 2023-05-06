package com.promineotech.reportmgr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.NewIcon;

@Component

public class DefaultIconMakerDao implements IconMakerDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  private SqlParams generateInsertSql(NewIcon newIcon) {
    // @formatter:off
    String sql = ""
        + "INSERT INTO icon ("
        + "serial_number, part_number, status, production_date, notes"
        + ") VALUES ("
        + ":serial_number, :part_number, :status, :production_date, :notes"
        + ")";
    // @formatter:on
    
    SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("serial_number", newIcon.getSerialNumber());
    params.source.addValue("part_number", newIcon.getPartNumber());
    params.source.addValue("status", newIcon.getStatus());
    params.source.addValue("production_date", newIcon.getProductionDate());
    params.source.addValue("notes", newIcon.getNotes());
    
    return params;
  }
  
  
  @Override
  public Icon saveIcon(NewIcon newIcon) {
SqlParams params = generateInsertSql(newIcon);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    
    Long orderPK = keyHolder.getKey().longValue();
    return Icon.builder()
      //@formatter:off
        .serialNumber(newIcon.getSerialNumber())
        .partNumber(newIcon.getPartNumber())
        .status(newIcon.getStatus())
        .productionDate(newIcon.getProductionDate())
        .notes(newIcon.getNotes())
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
