package com.jmant69.interview.controller;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
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
    
    @Test
    public void testGetAllCouriersDefaultShouldReturn200Ok() throws Exception {
    	List<Courier> courierList = new ArrayList<>();
    	Courier courier1 = Courier.builder()
    			.id(1)
    			.name("Phil Day")
    			.active(false)
    			.build();
    	
    	Courier courier2 = Courier.builder()
    			.id(2)
    			.name("Bill Bloggs")
    			.active(true)
    			.build();
    	
    	Courier courier3 = Courier.builder()
    			.id(3)
    			.name("John Smith")
    			.active(true)
    			.build();
    	
    	courierList.add(courier1);
    	courierList.add(courier2);
    	courierList.add(courier3);

    	String requestUri = END_POINT_PATH + "/couriers";
     
        Mockito.when(courierService.getAllCouriers()).thenReturn(courierList);
     
        mockMvc.perform(MockMvcRequestBuilders.get(requestUri).contentType("application/json"))
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Phil Day"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].active").value("false"))
            .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void testGetAllCouriersShouldReturn200Ok() throws Exception {
    	List<Courier> courierList = new ArrayList<>();
    	Courier courier1 = Courier.builder()
    			.id(1)
    			.name("Phil Day")
    			.active(false)
    			.build();
    	
    	Courier courier2 = Courier.builder()
    			.id(2)
    			.name("Bill Bloggs")
    			.active(true)
    			.build();
    	
    	Courier courier3 = Courier.builder()
    			.id(3)
    			.name("John Smith")
    			.active(true)
    			.build();
    	
    	courierList.add(courier1);
    	courierList.add(courier2);
    	courierList.add(courier3);

    	String requestUri = END_POINT_PATH + "/couriers?isActive=false";
     
        Mockito.when(courierService.getAllCouriers()).thenReturn(courierList);
     
        mockMvc.perform(MockMvcRequestBuilders.get(requestUri).contentType("application/json"))
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Phil Day"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].active").value("false"))
            .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void testGetAllActiveCouriersShouldReturn200Ok() throws Exception {
    	List<Courier> courierList = new ArrayList<>();
    	Courier courier1 = Courier.builder()
    			.id(1)
    			.name("Phil Day")
    			.active(true)
    			.build();
    	
    	Courier courier2 = Courier.builder()
    			.id(2)
    			.name("Bill Bloggs")
    			.active(true)
    			.build();
    	
    	courierList.add(courier1);
    	courierList.add(courier2);
    	
    	boolean isActive=true;

    	String requestUri = END_POINT_PATH + "/couriers?isActive=true";
		    
        Mockito.when(courierService.getAllActiveCouriers(isActive)).thenReturn(courierList);
     
        mockMvc.perform(MockMvcRequestBuilders.get(requestUri).contentType("application/json"))
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Phil Day"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].active").value("true"))
            .andDo(MockMvcResultHandlers.print());
    }
    
}
