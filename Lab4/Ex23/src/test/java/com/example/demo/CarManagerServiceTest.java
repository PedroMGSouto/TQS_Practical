package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CarManagerServiceTest {
    @Mock(lenient = true)
    private CarRepository car_repo;

    @InjectMocks
    private CarManagerService service;

    @BeforeEach
    void testUp(){
        Car c1 = new Car("pig", "bacon"); c1.setCarId(1234L);
        Car c2 = new Car("zebra", "stripes"); c2.setCarId(4321L);
        Car c3 = new Car("lion", "roar"); c3.setCarId(1111L);

        List<Car> cars = Arrays.asList(c1, c2, c3);

        Mockito.when(car_repo.findByCarId(c1.getCarId())).thenReturn(c1);
        Mockito.when(car_repo.findByCarId(c2.getCarId())).thenReturn(c2);
        Mockito.when(car_repo.findByCarId(c3.getCarId())).thenReturn(c3);
        Mockito.when(car_repo.findAll()).thenReturn(cars);
        Mockito.when(car_repo.findByCarId(-666L)).thenReturn(null);
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        Optional<Car> c1_BD = service.getCarDetails(1234L);
        assertThat(c1_BD.isPresent()).isTrue();
        Car c1 = c1_BD.get();
        assertThat(c1.getMaker()).isEqualTo("pig");

        Optional<Car> c2_BD = service.getCarDetails(4321L);
        assertThat(c2_BD.isPresent()).isTrue();
        Car c2 = c2_BD.get();
        assertThat(c2.getModel()).isEqualTo("stripes");

        Mockito.verify(car_repo, VerificationModeFactory.times(2)).findByCarId(Mockito.anyLong());
    }

    @Test
    public void whenInValidId_thenEmployeeShouldNotBeFound() {
        Optional<Car> car = service.getCarDetails(-1234L);
        assertThat(car).isEmpty();

        Mockito.verify(car_repo, VerificationModeFactory.times(1)).findByCarId(Mockito.anyLong());
    }

    @Test
    public void given3Cars_whenGetAll_thenReturn3Records() {
        List<Car> allCars = service.getAllCars();
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains("pig", "zebra", "lion");
        Mockito.verify(car_repo, VerificationModeFactory.times(1)).findAll();
    }

}