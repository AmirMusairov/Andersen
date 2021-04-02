package shop.Bucket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class Saver {
    public static void saveBucket(Bucket bucket, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(bucket);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bucket load(String fileName) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Bucket) objectInputStream.readObject();
        } catch (Exception e) {
            return new Bucket();
        }
    }
}