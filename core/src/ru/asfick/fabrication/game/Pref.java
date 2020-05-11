package ru.asfick.fabrication.game;

import com.badlogic.gdx.Gdx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Pref {
    private static String path;

    public Pref(){
        path = Gdx.files.getLocalStoragePath() + "map.bin";
    }

    static void save(Objects objects) throws IOException {
        FileOutputStream file = new FileOutputStream(path);
        ObjectOutputStream outputStream = new ObjectOutputStream(file);

        outputStream.writeObject(objects);

        outputStream.close();
        file.close();
    }

    static Objects load() throws IOException, ClassNotFoundException {
        Objects obj;

        FileInputStream file = new FileInputStream(path);
        ObjectInputStream inputStream = new ObjectInputStream(file);

        obj = (Objects) inputStream.readObject();

        inputStream.close();
        file.close();

        return obj;
    }
}
