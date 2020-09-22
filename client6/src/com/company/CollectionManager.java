package com.company;



import Classes.SpaceMarine;
import Exceptions.FileReaderException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/**
 * Здесь указаны команды, которые связаны с коллекцией
 */

public class CollectionManager {
    private static Vector<SpaceMarine> collection;
    private static Date creationDate;
    private static File file;

    public static void initializeVector() {
        if (collection == null) { collection = new Vector<SpaceMarine>(); creationDate = new Date(); }
    }

    /**
    CollectionManager(File file) {
        reader Reader = new reader();
        this.file = file;
        try {
            Vector<SpaceMarine> collection = reader.read(file);
            collection.addAll(collection);

        } catch (FileReaderException e){
            System.out.println(e.getMessage());
        }
    }
     */

    static Vector<SpaceMarine> getCollection() {
        return collection;
    }

    public static void add(SpaceMarine spaceMarine) {
        collection.add(spaceMarine);
    }

    public static void addFromJson(SpaceMarine spaceMarine) {
        spaceMarine.setID(spaceMarine.getId());
        collection.add(spaceMarine);
    }

    public static void getInformation() {
        System.out.println("Тип - "+ collection.getClass());
        System.out.println("Дата - "+ creationDate);
        System.out.println("Количество элементов - "+ collection.size());
    }

    public static void clear() {
        collection.clear();
    }

    public static void show() {
       if (!getCollection().isEmpty()) {
           int i = 1;
           for (SpaceMarine spaceMarine : collection) {
               System.out.println("Элемент под номером " + i);
               System.out.println(spaceMarine.toString());
               i++;
           }
       } else {
           System.out.println("Не густо, а пусто!");
       }
    }


    public static void remove_by_id(Integer marineID) {
        SpaceMarine sp=null;
        for(SpaceMarine s:collection){
            if(s.getId().equals(marineID)){
                sp=s;
                break;
            }
        }
        collection.remove(sp);
    }


    public static void save(){
        try {
            FileWriter writer1 = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(writer1);
            writer.write("[\n");
            int i = 0;
            for(Iterator<SpaceMarine> iterator = collection.iterator(); ((Iterator) iterator).hasNext();){
                i++;
                writer.write(String.valueOf(iterator.next()));
                if(i<collection.size()){
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Не получилось записать файл");
            e.printStackTrace();
        }
    }

    public static void update(SpaceMarine spaceMarine, Integer marineID) {
        for (SpaceMarine SpaceMarineUpdate  : collection) {
            if (spaceMarine.getId().equals(marineID)) {
                spaceMarine.setName(SpaceMarineUpdate.getNames());
                spaceMarine.setID(SpaceMarineUpdate.getId());
                spaceMarine.setChapter(SpaceMarineUpdate.getChapter());
                spaceMarine.setMeleeWeapon(SpaceMarineUpdate.getMeleeWeapon());
                spaceMarine.setHeartCount(SpaceMarineUpdate.getHeartCount());
                spaceMarine.setHealth(SpaceMarineUpdate.getHealth());
                spaceMarine.setCoordinates(SpaceMarineUpdate.getCoordinates());
            }
        };
    }

    public static void AscendingHeight() {
        HeightComparator myHeightComparator = new HeightComparator();
        collection.sort(myHeightComparator);
    }

    public static void DescendingHeight() {
        HeightComparator myHeightComparator = new HeightComparator();
        collection.sort(myHeightComparator);
        System.out.println("Отсортированный вариант:");
        Collections.reverse(collection);
    }

    public static void removeL() {
        if (!getCollection().isEmpty()) {
            collection.remove(collection.lastElement());
            System.out.println("Успешно устранено");
        }
        else {
            System.out.println("Не удалось удалить последний элемент потому что нечего удалять");
        }
    }


}

