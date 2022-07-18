package com.weCode.bookStore.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator =  "UUID")
	private UUID id;
	
	@Column
	@NonNull
	private String title;
	
	@Column
	@NonNull
	private String description;
	
	@Column
	@NonNull
	private int releaseYear;

}
