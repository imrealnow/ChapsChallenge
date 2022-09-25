package test.nz.ac.vuw.ecs.swen225.gp22;

import javax.swing.JFrame;

import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.persistence.LevelLoader;
import nz.ac.vuw.ecs.swen225.gp22.renderer.GameView;

public class RendererTests {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chap's Challenge");
        GameView view = new GameView(LevelLoader.Level1.load());
        frame.add(view);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
