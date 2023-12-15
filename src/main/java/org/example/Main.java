package org.example;


public class Main {
    public static void main(String[] args) {
        Container.scannerInit();

        new App().run();

        Container.scannerClose();
    }
}
