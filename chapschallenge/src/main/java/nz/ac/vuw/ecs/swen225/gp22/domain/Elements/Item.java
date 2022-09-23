package nz.ac.vuw.ecs.swen225.gp22.domain.Elements;

import nz.ac.vuw.ecs.swen225.gp22.util.Sprite;

/**
 * Items represent a stored version of a pickup.
 * They don't do anything by themselves, but we still need to store them.
 */
public enum Item{
    ItemKeyBlue{
        @Override
        public Sprite sprite() { return Sprite.KeyBlue; }
    },
    ItemKeyRed{
        @Override
        public Sprite sprite() { return Sprite.KeyRed; }
    },
    ItemKeyYellow{
        @Override
        public Sprite sprite() { return Sprite.KeyYellow; }
    },
    ItemFriend{
        @Override
        public Sprite sprite() { return Sprite.Friend; }
    };
    
    /**
     * All Items must have a representive sprite.
     * 
     * @return A sprite for representing a given item.
     */
    public abstract Sprite sprite();
}