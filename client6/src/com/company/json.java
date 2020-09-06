package com.company;

/**
* Сохранение коллекции в заданном формате (json — поэтому и имя класса такое)
 */

import Classes.SpaceMarine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.ZonedDateTime;
import java.util.Vector;

public class json {


    private static String filePath = System.getenv("WORK_FILE_PATH");
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder
            .registerTypeAdapter(ZonedDateTime.class, new TypeAdapter<ZonedDateTime>() {
                @Override
                public void write(JsonWriter out, ZonedDateTime value) throws IOException {
                    out.value(value.toString());
                }

                @Override
                public ZonedDateTime read(JsonReader in) throws IOException {
                    return ZonedDateTime.parse(in.nextString());
                }
            })
            .serializeNulls()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();

    public static void SaveCollectionToJson() {
        Gson gson = new Gson();
        //"C:\\Users\\user\\Desktop\\data.txt"
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(CollectionManager.getCollection(), writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void JsonToCollection() {
        //"C:\\Users\\user\\Desktop\\field.json"
        if (filePath!=null) {
            try (Reader reader = new FileReader(filePath)) {
                CollectionManager.initializeVector();
                Vector<SpaceMarine> spaceMarines = gson.fromJson(reader,new TypeToken<Vector<SpaceMarine>>(){}.getType());
                if (spaceMarines.size()!=0) {
                    for (SpaceMarine spaceMarine: spaceMarines) {
                        CollectionManager.addFromJson(spaceMarine);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (SecurityException e) {
                System.out.println("You have no rights (to open file)");
            } catch (NullPointerException e) {
                System.out.println("You've entered the void: файл пуст");
            } catch (com.google.gson.JsonSyntaxException e) {
                System.out.println("Ошибка в содержании" + e.getMessage());
            }

        } else {
            System.out.println("Вы не задали значение переменной окружения, коллекция не загружена");
        }

    }
}


