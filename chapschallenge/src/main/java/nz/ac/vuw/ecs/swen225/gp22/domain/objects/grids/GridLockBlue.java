package nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Grid;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.objects.entities.Player;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Entity;
import nz.ac.vuw.ecs.swen225.gp22.domain.Game;

public class GridLockBlue extends Grid {
    @Override
    public Sprite getSprite() {
        return Sprite.GridLockBlue;
    }

    @Override
    public boolean entityCanStep(Entity e) {
        return (e instanceof Player p && p.inventory().getOrDefault(Item.ItemKeyBlue,0) > 0);
    }

    @Override
    public void onInteract(Entity e) {
        if (e instanceof Player p){
            p.inventory().put(Item.ItemKeyBlue,p.inventory().get(Item.ItemKeyBlue)-1);
            Game.getInstance().getLevel().morphTile(this,new TileGrass());
        }
    }
}