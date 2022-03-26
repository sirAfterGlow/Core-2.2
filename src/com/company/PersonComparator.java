package com.company;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getFamily().compareTo(p2.getFamily());
    }
}
