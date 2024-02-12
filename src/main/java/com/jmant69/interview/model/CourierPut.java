package com.jmant69.interview.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourierPut {
	
	@Valid
	
	@NotNull(message="First name is mandatory.")
	@NotBlank(message="First name is mandatory.")
	@Size(min = 3, max=30, message="First name must be between 3 and 30 characters")
	String firstName;
	
	@NotNull(message="Last name is mandatory.")
	@NotBlank(message="Last name is mandatory.")
	@Size(min = 3, max=30, message="Last name must be between 3 and 30 characters")    
	String lastName;
	
	@NotNull(message="Active is mandatory.")
    Boolean active;
}
