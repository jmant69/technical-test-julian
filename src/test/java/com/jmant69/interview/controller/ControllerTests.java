package com.jmant69.interview.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmant69.interview.model.Courier;
import com.jmant69.interview.service.CourierService;

@WebMvcTest(CourierController.class)
public class ControllerTests {
	
	private static final String END_POINT_PATH = "/api";
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private CourierService courierService;
	
    @Test
    public void testUpdateShouldReturn404NotFound() throws Exception {
    	Courier courier = Courier.builder()
    			.id(500)
    			.name("Phil Day")
    			.active(false)
    			.build();

    	Long id = 500L;

    	String requestUri = END_POINT_PATH + "/updatecourier/" + id;
		
		String requestBody = objectMapper.writeValueAsString(courier);
     
        Mockito.when(courierService.update(courier, id)).thenThrow(NotFoundException.class);
     
        mockMvc.perform(MockMvcRequestBuilders.put(requestUri).contentType("application/json").content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void testUpdateShouldReturn200Ok() throws Exception {
    	Courier courier = Courier.builder()
    			.id(1)
    			.name("Phil Day")
    			.active(false)
    			.build();

    	Long id = 1L;

    	String requestUri = END_POINT_PATH + "/updatecourier/" + id;
		
		String requestBody = objectMapper.writeValueAsString(courier);
     
        Mockito.when(courierService.update(courier, id)).thenReturn(courier);
     
        mockMvc.perform(MockMvcRequestBuilders.put(requestUri).contentType("application/json").content(requestBody))
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Phil Day"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.active").value("false"))
            .andDo(MockMvcResultHandlers.print());
    }
    
//	@Test
//	public void testUpdateShouldReturn200Ok() throws Exception {
//		Customer updatedCustomer = new Customer();
//		updatedCustomer.setCustomerRef(1L);
//		String expectedValue = "James";
//		updatedCustomer.setCustomerName(expectedValue);
//
//		String requestBody = objectMapper.writeValueAsString(updatedCustomer);
//
//		Mockito.when(service.update(updatedCustomer)).thenReturn(updatedCustomer);
//
//		Mockito.when(modelMapper.map(updatedCustomer, CustomerDTO.class))
//				.thenReturn(modelMapperMock.map(updatedCustomer, CustomerDTO.class));
//
//		mockMvc.perform(MockMvcRequestBuilders.put(END_POINT_PATH).contentType("application/json").content(requestBody))
//				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value(expectedValue))
//				.andDo(MockMvcResultHandlers.print());
//	}

}
