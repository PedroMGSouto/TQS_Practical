package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository car_repo;

    @Test
    public void whenfindByExistingCarID_thenReturnCar() {
        Car c1 = new Car("pig", "bacon");
        c1.setCarId(1234L);
        entityManager.persistAndFlush(c1);

        Car found = car_repo.findByCarId(1234L);

        assertThat(found.getModel()).isEqualTo(c1.getModel());
    }

    @Test
    public void whenfindByCarInvalidID_thenReturnNull() {
        Car c9 = car_repo.findByCarId(-123456L);
        assertThat(c9).isNull();
    }

    @Test
    public void givenListOfCars_whenFindAll_thenReturnAllCars() {
        Car c2 = new Car("zebra", "stripes");
        c2.setCarId(4321L);
        Car c3 = new Car("lion", "roar");
        c3.setCarId(1111L);

        entityManager.persist(c2);
        entityManager.persist(c3);
        entityManager.flush();

        List<Car> cars = car_repo.findAll();

        assertThat(cars).hasSize(2).extracting(Car::getMaker).containsOnly("zebra","lion");
    }
}