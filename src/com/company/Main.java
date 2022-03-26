package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long minorCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовешеннолетних = " + minorCount);

        List<String> recruitFamilies = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("Пятеро первых фамилий списка призывников");
        for (int i = 0; i < 5; i++) {
            System.out.println(recruitFamilies.get(i));
        }


        List<Person> workablePersons = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex() == Sex.MAN && person.getAge() < 65) || (person.getSex() == Sex.WOMAN && person.getAge() < 60))
                .sorted(new PersonComparator())
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("Пятеро первых гражан списка трудоспособных граждан");
        for (int i = 0; i < 5; i++) {
            System.out.println(workablePersons.get(i));
        }
    }
}
