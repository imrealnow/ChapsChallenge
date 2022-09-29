package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelLoader;

public class Main {
    public static void main(String[] a) {
        FuzzTest ft = new FuzzTest();
        ft.fuzzTestGame( LevelLoader.Level1.load() );
    }
}
