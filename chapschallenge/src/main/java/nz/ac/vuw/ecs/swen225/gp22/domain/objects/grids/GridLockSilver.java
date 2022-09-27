package nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Grid;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.Game;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;

public class GridLockSilver extends Grid {
    @Override
    public Sprite getSprite() {
        return Sprite.GridLockSilver;
    }

    @Override
    public boolean entityCanStep(Entity e) {
        return (e instanceof Player p && p.inventory().getOrDefault(Item.ItemKeySilver,0) > 0);
    }

    @Override
    public void onInteract(Entity e) {
        if (e instanceof Player p){
            Game.getInstance().getLevel().morphTile(this,new TileGrass());
        }
    }
}