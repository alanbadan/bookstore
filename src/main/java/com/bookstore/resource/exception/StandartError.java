package com.bookstore.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandartError { // parametros para a classe resourceExcptionHandler

	
	private Long timeStamp;
	private Integer status;
	private String error;

}
