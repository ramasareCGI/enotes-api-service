package com.service.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotesDto {

	private Integer id;
	private String title;
	private String description;

	private CategoryDto categorydto;

	private Integer createdBy;
	private Date createdOn;
	private Integer updatedBy;
	private Date updateOn;

	private FilesDto fileDetails;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CatetoryDto {
		private Integer id;
		private String name;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FilesDto {
		private Integer id;
		private String originalFileName;
		private String displayFilename;

	}

}
