package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
class CarControllerRestTest {
    @Autowired
    private MockMvc testServlet;

    @MockBean
    private CarManagerService carService;

    @Test
    void whenPostCar_thenReturnCreatedCar() throws Exception {
        Car c1 = new Car("pig", "bacon");

        when(carService.save(Mockito.any())).thenReturn(c1);

        testServlet.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(c1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("pig")));

        verify(carService, times(1)).save(Mockito.any());
    }

    @Test
    public void whenGetCars_thenReturnList() throws Exception {
        Car c1 = new Car("pig", "bacon"); c1.setCarId(1234L);
        Car c2 = new Car("zebra", "stripes"); c2.setCarId(4321L);

        List<Car> cars = new ArrayList<>();
        cars.add(c1);
        cars.add(c2);

        when(carService.getAllCars()).thenReturn(cars);

        testServlet.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].maker", is("pig")))
                .andExpect(jsonPath("$[0].model", is("bacon")))
                .andExpect(jsonPath("$[1].maker", is("zebra")));

        verify(carService, times(1)).getAllCars();
    }

    @Test
    public void whenValidID_thenReturnCar() throws Exception {
        Car c1 = new Car("pig", "bacon");
        c1.setCarId(1234L);

        when(carService.getCarDetails(1234L)).thenReturn(Optional.of(c1));

        testServlet.perform(get("/api/cars/1234").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.maker", is("pig")))
                .andExpect(jsonPath("$.model", is("bacon")));

        verify(carService, times(1)).getCarDetails(Mockito.anyLong());
    }

}