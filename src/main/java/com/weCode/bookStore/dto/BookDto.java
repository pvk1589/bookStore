package com.weCode.bookStore.dto;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "BookDto", description = "All details about book")
public class BookDto {

	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, value = "UUID", dataType = "UUID", example = "02c27f43-a408-4b42-9102-645d01b92d50", notes = "This is database generated id")
	private UUID id;

	@ApiModelProperty(value = "String", dataType = "String", example = "book titile", notes = "Name of the book", required = true)
	private String title;

	@ApiModelProperty(value = "description", dataType = "String", example = "this is story book", notes = "Descripation of the book", required = true)
	private String description;

	@ApiModelProperty(value = "2022", dataType = "Int", example = "2022", notes = "Published year", required = true)
	private int releaseYear;

	/*
	 * public String getTitle() { return title; }
	 * 
	 * public void setTitle(String title) { this.title = title; }
	 */

}
