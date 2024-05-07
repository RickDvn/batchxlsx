package com.viewnext.batchxlsx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Terminal {
	
	private String id;
	private String name;
	private String description;
	private int code;
}
