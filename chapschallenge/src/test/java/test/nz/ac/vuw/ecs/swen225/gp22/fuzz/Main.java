package test.nz.ac.vuw.ecs.swen225.gp22.fuzz;

import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelLoader;

public class Main {
    public static void main(String[] a) {
        FuzzTest level1 = new FuzzTest();
        level1.fuzzTestGame( LevelLoader.Level1.load() );

        // FuzzTest level2 = new FuzzTest();
        // level2.fuzzTestGame( LevelLoader.Level2.load() );
    }
}
