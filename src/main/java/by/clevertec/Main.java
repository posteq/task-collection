package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19("P-1");
        task20();
        task21();
        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(2*7)
                .limit(7)
                .forEach(System.out::println);
    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(s -> s.getOrigin().equals("Japanese"))
                .map(a -> a.getGender().equals("Female") ? a.getBread() : a.getBread().toUpperCase())
                .forEach(System.out::println);
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .distinct()
                .filter(origin -> origin.startsWith("A"))
                .forEach(System.out::println);
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();

        System.out.println(
                animals.stream()
                        .filter(animal -> animal.getGender().equals("Female"))
                        .count());
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        boolean hungarian = animals.stream()
                .filter(animal -> animal.getAge() > 20 && animal.getAge() < 30)
                .anyMatch(animal -> animal.getOrigin().equals("Hungarian"));
        System.out.println(hungarian);
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        Predicate<Animal> genderMaleOrFemale = str -> str.getGender().equals("Male") ||
                str.getGender().equals("Female");
        boolean gender = animals.stream()
                .allMatch(genderMaleOrFemale);
        System.out.println(gender);
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        boolean oceania = animals.stream()
                .allMatch(animal -> animal.getOrigin().equals("Oceania"));
        System.out.println( oceania);
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .mapToInt(Animal::getAge)
                .max()
                .ifPresent(System.out::println);
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(animal -> animal.getBread().toCharArray().length)
                .min(Comparator.comparing(Integer::intValue))
                .ifPresent(breads -> System.out.println( breads));
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        int totalAge = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println( totalAge);
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        OptionalDouble indonesian = animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .average();
        System.out.println("Average age of animals from Indonesia : " + indonesian);
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();

        final int OLD_AGE = 27;
        final int YOUNG_AGE = 18;

        List<Person> people = persons.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(person -> ageNow(person.getDateOfBirth()) >= YOUNG_AGE &&
                        ageNow(person.getDateOfBirth()) <= OLD_AGE)
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .toList();

        System.out.println("Candidates for training at the military academy :"+ people.size() + "\n" + people + "\n");
    }

    private static int ageNow(LocalDate birthdate) {
        Period period = Period.between(birthdate, LocalDate.now());
        return period.getYears();
    }

    public static void task13() {

        List<House> houses = Util.getHouses();

        final int OLD_AGE = 58;
        final int YOUNG_AGE = 18;

        Stream<Person> hospital = houses.stream()
                .filter(house -> house.getBuildingType().equalsIgnoreCase("Hospital"))
                .flatMap(house -> house.getPersonList().stream());

        Stream<Person> childrenAndElder = houses.stream()
                .filter(house -> !house.getBuildingType().equalsIgnoreCase("Hospital"))
                .flatMap(house -> house.getPersonList().stream())
                .filter(person -> ageNow(person.getDateOfBirth()) < YOUNG_AGE ||
                        ageNow(person.getDateOfBirth()) >= OLD_AGE);

        Stream<Person> others = houses.stream()
                .filter(house -> !house.getBuildingType().equalsIgnoreCase("Hospital"))
                .flatMap(house -> house.getPersonList().stream())
                .filter(person -> ageNow(person.getDateOfBirth()) < OLD_AGE &&
                        ageNow(person.getDateOfBirth()) >= YOUNG_AGE);

        Stream.concat(hospital, Stream.concat(
                                childrenAndElder,
                                others
                        )
                )
                .limit(500)
                .forEach(System.out::println);
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
        final double COST_PER_TON = 7.14;

        Map<String, Double> carForCountry = cars.stream()
                .collect(Collectors.groupingBy(car -> {
                            if (car.getCarMake().equals("Jaguar") || car.getColor().equals("White")) {
                                return "Turkmenistan";
                            } else if (car.getMass() < 1500 &&
                                    (
                                            car.getCarMake().equals("BMW") ||
                                                    car.getCarMake().equals("Lexus") ||
                                                    car.getCarMake().equals("Chrysler") ||
                                                    car.getCarMake().equals("Toyota")
                                    )
                            ) {
                                return "Uzbekistan";
                            } else if ((car.getColor().equals("Black") &&
                                    car.getMass() > 4000 ) ||
                                    car.getCarMake().equals("GMC") ||
                                    car.getCarMake().equals("Dodge")
                            ) {
                                return "Kazakhstan";
                            } else if (car.getReleaseYear() < 1982 ||
                                    car.getCarModel().equals("Civic") ||
                                    car.getCarModel().equals("Cherokee")
                            ) {
                                return "Kyrgyzstan";
                            } else if (!(car.getColor().equals("Yellow") ||
                                    car.getColor().equals("Red") ||
                                    car.getColor().equals("Green") ||
                                    car.getColor().equals("Blue")) ||
                                    car.getPrice() > 40000
                            ) {
                                return "Russia";
                            } else if (car.getVin().contains("59")) {
                                return "Mongolia";
                            }
                            return "NONE";
                        },
                        Collectors.summingDouble(car -> car.getMass() / 1000.0 * COST_PER_TON)
                ));
        carForCountry.remove("NONE");

        carForCountry.forEach((country, cost) -> System.out.printf("Transportation costs for %s country : $%.2f%n", country, cost));

        double totalRevenue = carForCountry.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        System.out.printf("Total revenue for the logistics company: $%.2f%n", totalRevenue);

    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();

        final double WATER_COST_PER_CUBIC_METER = 1.39;
        final int TOTAL_DAYS = 1826;
        final int LITERS_PER_CYB = 1000;

        double totalPriceService = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed())
                .sorted(Comparator.comparing(Flower::getPrice))
                .sorted(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed())
                .filter(plant -> plant.getCommonName().toUpperCase().compareTo("S")<= 0 &&
                        plant.getCommonName().toUpperCase().compareTo("C")  >= 0)
                .filter(Flower::isShadePreferred)
                .filter(plant -> plant.getFlowerVaseMaterial().stream()
                        .anyMatch(material -> material.equals("Aluminum") ||
                                material.equals("Steel") ||
                                material.equals("Glass"))
                )
                .mapToDouble(flower -> flower.getPrice() +
                        (TOTAL_DAYS * flower.getWaterConsumptionPerDay()) * WATER_COST_PER_CUBIC_METER / LITERS_PER_CYB)
                .sum();

        System.out.println("Total price of service for government : " + totalPriceService + " $");

    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        List<Student> list = students.stream()
                .filter(ages -> ages.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .toList();
        System.out.println("Student list : " + list);
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .map(Student::getGroup)
                .sorted()
                .distinct()
                .forEach(System.out::println);
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(
                                Student::getFaculty,
                                Collectors.averagingDouble(Student::getAge)
                        )
                )
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }

    public static void task19(String inputGroup) {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        students.stream()
                .filter(student -> student.getGroup().equalsIgnoreCase(inputGroup))
                .filter(student -> examinations.stream()
                        .anyMatch(exam -> exam.getStudentId() == student.getId() &&
                                exam.getExam3() > 4)
                )
                .forEach(System.out::println);
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.averagingDouble(student ->
                                examinations.stream()
                                        .filter(exam -> exam.getStudentId() == student.getId())
                                        .mapToDouble(Examination::getExam1)
                                        .average()
                                        .orElse(0)
                        )
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .ifPresent(System.out::println);
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGroup,
                        Collectors.counting()
                ))
                .entrySet().forEach(System.out::println);
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparingInt(Student::getAge)),
                                student -> student.map(Student::getAge).orElse(0))
                ))
                .entrySet().forEach(System.out::println);
    }
}
