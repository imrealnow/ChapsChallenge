package main.java.nz.ac.vuw.ecs.swen225.gp22.domain.Elements;
/**
 * The base interface for all game elements that need behaviour updated every frame.
 * Examples of such things include the player, monsters, and/or obstacles.
 */
interface Updatable{
    public void update();
}