package com.service.demo.dto;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

//	@NotBlank
//	@Min(value = 5)
//	@Max(value = 100)
	private String name;
	
//	@NotBlank
//	@Min(value = 5)
//	@Max(value = 100)
	private String description;
	private Boolean isActive;
	private Integer createdBy;
	private Date createdOn;
	private Integer updatedBy;
	private Date updateOn;

}
