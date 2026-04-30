package hw2;

import hw2.dao.CarDAO;
import hw2.dao.DAOFactory;
import hw2.dao.IDAOFactory;
import hw2.entity.Car;

import java.util.List;

public class Main {
    static void main() {
        IDAOFactory factory = DAOFactory.getInstance();
        CarDAO carDAO = factory.getCarDAO();



    }
}
