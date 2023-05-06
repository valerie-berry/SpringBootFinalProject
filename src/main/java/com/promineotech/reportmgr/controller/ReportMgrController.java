package com.promineotech.reportmgr.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.reportmgr.entity.NewReport;
import com.promineotech.reportmgr.entity.Report;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/reports")
@OpenAPIDefinition(info = @Info(title = "ICON Report Manager Service"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})


public interface ReportMgrController {


  // @formatter:off
  @Operation(
      summary = "Returns a list of reports",
      description = "Returns a list of reports given an optional reportID",
      responses = {
          @ApiResponse(responseCode = "200",
            description = "A list of Reports is returned",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Report.class))
          ),
          @ApiResponse(responseCode = "400",
            description = "The request parameters are invalid",
            content = @Content(mediaType = "application/json")
          ),
          @ApiResponse(responseCode = "404",
            description = "No Reports were found with the input criteria",
            content = @Content(mediaType = "application/json")
          ),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occured",
            content = @Content(mediaType = "application/json")
          )
      },
      parameters = {
          @Parameter(name = "reportID",
            allowEmptyValue = false,
            required = false,
            description = "The ID of the report"
          
        )
      }
      
  )

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Report> fetchReports(
      //@Size(max = Constants.REPORT_ID_MAX_LENGTH)
      //@Pattern(regexp = "[\\w\\s]*")
      @RequestParam(required = false)
        BigDecimal reportID);
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  //@formatter:off
  Report createReport(
      @Valid
      @RequestBody NewReport newReport);
  
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  //@formatter:off
  Report updateReport(
      @Valid
      @RequestBody Report newReportDetails);
  
  @DeleteMapping
  @ResponseStatus(code = HttpStatus.OK)
  //@formatter:off
  void deleteReport(
      @Valid
      @RequestBody BigDecimal reportID);
  
//@formatter:on

}



// @formatter:on
