package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;


class MainTest {

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static final List<Animal> ANIMALS = List.of(
            new Animal(1, "Dog", 15, "Japanese", "Male"),
            new Animal(2, "Cat", 25, "Hungarian", "Male"),
            new Animal(3, "Tiger", 12, "Africa", "Male"),
            new Animal(4, "Zebra", 19, "Africa", "Female"),
            new Animal(5, "Bear", 10, "North America", "Male"),
            new Animal(6, "Giraffe", 21, "Africa", "Female"),
            new Animal(7, "Wolf", 16, "Europe", "Male"),
            new Animal(8, "Leopard", 17, "Africa", "Female"),
            new Animal(9, "Kangaroo", 35, "Australian", "Male"),
            new Animal(10, "Penguin", 8, "Antarctica", "Female"),
            new Animal(11, "Monkey", 13, "Asia", "Male"),
            new Animal(12, "Panda", 14, "China", "Female"),
            new Animal(13, "Lion", 35, "Australian", "Female"),
            new Animal(14, "Elephant", 50, "Indonesian", "Male"),
            new Animal(15, "Deer", 12, "Indonesian", "Female")
    );


    @Test
    void task1() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(ANIMALS);
            Main.task1();
            assertTrue(outputStreamCaptor.toString().trim().isEmpty());
        }

    }

    @Test
    void task1EmptyListTest() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(List.of());
            Main.task1();
            assertTrue(outputStreamCaptor.toString().isEmpty());
        }
    }


    @Test
    void task2Test() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(ANIMALS);
            Main.task2();
            assertEquals("DOG", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    void task2EmptyListTest() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(List.of());
            Main.task2();
            assertTrue(outputStreamCaptor.toString().isEmpty());
        }
    }

    @Test
    void task3Test() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(ANIMALS);
            Main.task3();
            assertEquals("Australian", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    void task3EmptyListTest() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(List.of());
            Main.task3();
            assertTrue(outputStreamCaptor.toString().isEmpty());
        }
    }

    @Test
    void task4Test() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(ANIMALS);
            Main.task4();
            assertEquals("7", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    void task4EmptyListTest() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(List.of());
            Main.task4();
            assertEquals("0", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    void task5Test() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(ANIMALS);
            Main.task5();
            assertTrue(Boolean.parseBoolean(outputStreamCaptor.toString().trim()));
        }
    }

    @Test
    void task5EmptyListTest() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(List.of());
            Main.task5();
            assertEquals("false", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    void task6Test() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(ANIMALS);
            Main.task6();
            assertTrue(Boolean.parseBoolean(outputStreamCaptor.toString().trim()));
        }
    }

    @Test
    void task6EmptyListTest() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(List.of());
            Main.task6();
            assertTrue(Boolean.parseBoolean(outputStreamCaptor.toString().trim()));
        }
    }

    @Test
    void task7Test() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(ANIMALS);
            Main.task7();
            assertEquals("false", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    void task7EmptyListTest() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(List.of());
            Main.task7();
            assertTrue(Boolean.parseBoolean(outputStreamCaptor.toString().trim()));
        }
    }

    @Test
    void task8Test() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(ANIMALS);
            Main.task8();
            assertEquals("50", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    void task8EmptyListTest() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(List.of());
            Main.task8();
            assertTrue(outputStreamCaptor.toString().isEmpty());
        }
    }

    @Test
    void task9Test() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(ANIMALS);
            Main.task9();
            assertEquals("3", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    void task9EmptyListTest() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(List.of());
            Main.task9();
            assertTrue(outputStreamCaptor.toString().isEmpty());
        }
    }

    @Test
    void task10Test() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(ANIMALS);
            Main.task10();
            assertEquals("302", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    void task10EmptyListTest() {
        try (MockedStatic<Util> utilities = mockStatic(Util.class)) {
            utilities.when(Util::getAnimals).thenReturn(List.of());
            Main.task10();
            assertEquals("0", outputStreamCaptor.toString().trim());
        }
    }
}

