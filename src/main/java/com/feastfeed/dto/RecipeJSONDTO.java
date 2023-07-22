package com.feastfeed.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized @Builder
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeJSONDTO{
	@JsonProperty("recipeId")
	private Integer  id;
	
	@NonNull private String name;
	@NonNull private String url;
	@NonNull private List<String> ingredients;
	@NonNull private List<String> method;
	
	@Builder.Default
	private String description=null;
	
	@Builder.Default
	private String author=null;
}