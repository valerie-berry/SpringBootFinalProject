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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class DefaultIconMgrDao implements IconMgrDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Icon> fetchIcons(BigDecimal serialNumber) {
    log.debug("DAO: serialNumber={}", serialNumber);

    //@formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM icon";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();

    if (!Objects.isNull(serialNumber)) {
      sql += " WHERE serial_number = :serial_number";
      params.put("serial_number", serialNumber.toString());
    } else {
      params = null;
    }

    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      @Override
      public Icon mapRow(ResultSet rs, int rowNum) throws SQLException {
        //@formatter:off
            return Icon.builder()
                .serialNumber(rs.getBigDecimal("serial_number"))
                .partNumber(rs.getString("part_number"))
                .status(rs.getString("status"))
                .productionDate(rs.getString("production_date"))
                .notes(rs.getString("notes"))

                .build();
        //@formatter:on
      }
    });
  }
}

