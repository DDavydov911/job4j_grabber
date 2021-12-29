package ru.job4j.ood.ocp;

public class WrongOCP3 {
    /*
        Метод findFeed имеет разную реализацию, хотя он не абстрактный.
        Это модификация.
     */
    public class Animal {
        private String animalClass;
        private String name;
        private String feed;

        public void findFeed() {
            System.out.println("Find some feed");
        }
    }

    public class Dog extends Animal {
        private String animalClass = "Dogs";
        private String name;
        private String feed;

        public void findFeed() {
            System.out.println("Find meet");
        }
    }

    public class Cat extends Animal {
        private String animalClass = "Cats";
        private String name;
        private String feed = "cats feed";

        public void findFeed() {
            System.out.println("Find feed");
        }
    }

    public class WildCat extends Animal {
        private String animalClass = "Cats";
        private String name;
        private String feed = "Mousses";

        public void findFeed() {
            System.out.println("Find mousses");
        }
    }
}
