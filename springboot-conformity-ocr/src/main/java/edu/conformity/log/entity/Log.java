package edu.conformity.log.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String logId;
	
	private String type;
	
	private String title;
	
	private String remoteAddr;
	
	private String requestUri;
	
	private String method;
	
	private String params;
	
	private String exception;
	
	private Long userId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date operateDate;
	
	private String timeout;
	
}
