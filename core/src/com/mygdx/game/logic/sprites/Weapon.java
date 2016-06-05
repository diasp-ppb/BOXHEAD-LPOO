package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.logic.Animation;

import java.util.Vector;


public abstract class Weapon{
    protected int durability;
    protected int ammo;
    protected Animation idle;
    protected Animation reload;
   // protected Animation attack;
    protected Animation current_anim;

    public Weapon(int a,int d){
        ammo = a;
        durability = d;
    }
    public void decDurability(int d){
        durability--;
    }

    public int getDurability(){
        return durability;
    }

    public void setDurability(int d){
        durability = d;
    }


    public boolean use(){
        if(ammo == 0)
            return false;
        ammo--;
        return true;
    }

    public abstract void recharge(int level);

    public void setAnimation(char anim){
        switch (anim){
            case 'r':{ //reload
                current_anim = reload;
                break;
            }
            case 'i':{ //idle
                current_anim = idle;
                break;
            }
            default: {
                break;
            }
        }
    }

    public TextureRegion getFrame()
    {
        return current_anim.getFrame();
    }



}
