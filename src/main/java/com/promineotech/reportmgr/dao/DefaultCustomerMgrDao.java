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
import com.promineotech.reportmgr.entity.Customer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class DefaultCustomerMgrDao implements CustomerMgrDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Customer> fetchCustomers(BigDecimal customerID) {
    log.debug("DAO: customerID={}", customerID);

    //@formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM customer";
    //@formatter:on

    Map<String, Object> params = new HashMap<>();

    if (!Objects.isNull(customerID)) {
      sql += " WHERE customer_id = :customer_id";
      params.put("customer_id", customerID.toString());
    } else {
      params = null;
    }

    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      @Override
      public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        //@formatter:off
            return Customer.builder()
                .customerID(rs.getBigDecimal("customer_id"))
                .customerName(rs.getString("customer_name"))
                .address(rs.getString("address"))
                .notes(rs.getString("notes"))
                .contactNumber(rs.getBigDecimal("contact_number"))
                .build();
        //@formatter:on
      }
    });
  }
}
