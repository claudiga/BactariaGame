package com.claude.capsule;

public class Main {
    public static void main(String[] args) {
        BacteriaGame bacteriaGame = new BacteriaGame(new GridPrinter());
        bacteriaGame.simulateBacteriaGame(System.in);
    }
}