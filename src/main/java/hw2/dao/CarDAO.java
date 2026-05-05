package hw2.dao;
import hw2.entity.Car;

import java.util.List;

public interface CarDAO {
    void add(Car car);
    List<Car> getAll();
    Car getById(int id);
    void deleteById(int id);
    void update(Car car, int id);
}
