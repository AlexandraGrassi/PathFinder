package net.ucoz.talg.controller;

import net.ucoz.talg.controller.Algorithms.AlgorithmStupid;
import net.ucoz.talg.controller.Algorithms.DijkstraAlgorithm;

public class Main {

    public static void main(String[] args) {
        MainController mainController = new MainController(new DijkstraAlgorithm());
    }
}
