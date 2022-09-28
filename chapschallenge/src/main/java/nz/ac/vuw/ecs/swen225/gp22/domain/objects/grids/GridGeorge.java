package nz.ac.vuw.ecs.swen225.gp22.domain.objects.grids;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Grid;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Item;
import nz.ac.vuw.ecs.swen225.gp22.domain.elements.Updateable;
import nz.ac.vuw.ecs.swen225.gp22.domain.Game;

public class GridGeorge extends Grid implements Updateable {
    @Override
    public Sprite getSprite() {
        return Sprite.GridGeorge;
    }

    @Override
    public void update() {
        if (Game.getInstance().getLevel().getPlayer().inventory().get(Item.ItemFriend)
            == Game.getInstance().getLevel().getFriendsNeeded()) 
            Game.getInstance().getLevel().morphTile(this,new TileGrass());
    }
}