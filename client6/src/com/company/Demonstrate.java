package com.company;

import Classes.SpaceMarine;

import java.util.Vector;


public class Demonstrate {

    public static boolean checkExist(int ID) {
        Vector<SpaceMarine> c = CollectionManager.getCollection();
        for (SpaceMarine spaceMarine:CollectionManager.getCollection()) {
            return spaceMarine.getId().equals(ID);
        }
        return false;
    }

    public static void demonstrate(SpaceMarine collection) {

        System.out.println("УНикальный ID: " +collection.getId());
        System.out.println("Имя: " + collection.getNames());
        System.out.println("Координаты: "+collection.getCoordinates());
        System.out.println("Дата создания: " + collection.getCreationDate());
        System.out.println("Здоровье: " + collection.getHealth());
        System.out.println("Непонятное поле: " + collection.getHeartCount());
        System.out.println("Непонятное поле номер 2: " + collection.getMeleeWeapon());
        System.out.println("Непонятное поле номер 3: " + collection.getChapter());

    }
}
