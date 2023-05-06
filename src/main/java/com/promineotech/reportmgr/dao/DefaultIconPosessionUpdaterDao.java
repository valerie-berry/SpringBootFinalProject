package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.reportmgr.entity.Customer;
import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.IconCustomer;

@Component

public class DefaultIconPosessionUpdaterDao implements IconPosessionUpdaterDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Autowired
  private DefaultIconMgrDao dimd;
  
  @Autowired
  private DefaultCustomerMgrDao dcmd;
  
  private SqlParams generateInsertSql(BigDecimal serialNumber, BigDecimal customerID, String date) {
    // @formatter:off
    String sql = ""
        + "INSERT INTO serial_customer ("
        + "serial_number, customer_id, received_date"
        + ") VALUES ("
        + ":serial_number, :customer_id, :received_date"
        + ")";
    // @formatter:on
    
    SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("serial_number", serialNumber);
    params.source.addValue("customer_id", customerID);
    params.source.addValue("received_date", date);
    return params;
  }
  
  
  public IconCustomer updatePosession(BigDecimal serialNumber, BigDecimal customerID, String date) {
    SqlParams params = generateInsertSql(serialNumber, customerID, date);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    
    //Long orderPK = keyHolder.getKey().longValue();
    Customer c = dcmd.fetchCustomers(customerID).get(0);
    Icon i = dimd.fetchIcons(serialNumber).get(0);
    
    // @formatter:off
    return IconCustomer.builder()
        .customer(c)
        .icon(i)
        .build();
    // @formatter:on
  }
  
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}
