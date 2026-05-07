package hw4.task7;

import java.util.List;

public class Main {
    static void main() {

    AnimalHelper ah = new AnimalHelper();

//    for (int i = 1; i <= 10; i++) {
//        Animal a = new Animal();
//        a.setName("testAnimal" + i);
//        a.setAge(i);
//        a.setTail(true);
//        ah.addAnimal(a);
//    }

        List<Animal> animalList = ah.getAnimalList();
        for (Animal a : animalList) {
            System.out.println(a);
        }

        Animal a = ah.getAnimalById(9); // testAnimal8
        System.out.println(a);

        Animal newAnimal = new Animal();
        newAnimal.setName("newAnimal");
        newAnimal.setAge(999);
        newAnimal.setTail(false);
        ah.updateAnimal(newAnimal, 11);

        ah.deleteAnimal(10);

    }
}
