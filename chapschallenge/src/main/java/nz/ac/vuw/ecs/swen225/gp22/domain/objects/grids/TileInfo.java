package nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Interactable;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;

public class TileInfo extends Tile implements Interactable {
    @Override
    public Sprite getSprite() {
        return Sprite.TileInfo;
    }

    @Override
    public boolean entityCanStep(Entity e) {
        // A player should always be able to walk onto this tile
        return true;
    }

    @Override
    public void onInteract(Entity e) {}

    public Sprite getInfoGraphic(){
        try{
            return Sprite.valueOf("Info"+Game.getInstance().getLevel().getLevelID());
        } catch(IllegalArgumentException e){}
        return Sprite.InfoError;
    }
}