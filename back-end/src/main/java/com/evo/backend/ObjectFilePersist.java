package com.evo.backend;

import java.io.*;

public class ObjectFilePersist {
        private String fileName;

        public ObjectFilePersist(String fileName) {
            this.fileName = fileName;
        }

        public void save(Object object) {
            FileOutputStream f = null;
            try {
                f = new FileOutputStream(new File(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(object);
                o.close();
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Object retrieve(){
            FileInputStream fi = null;
            try {
                fi = new FileInputStream(new File(fileName));
            } catch (FileNotFoundException e) {
                return null;
            }
            try {
                ObjectInputStream oi = new ObjectInputStream(fi);
                return oi.readObject();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }