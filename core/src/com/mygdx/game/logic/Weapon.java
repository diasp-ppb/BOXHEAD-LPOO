package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Vector;


public class Weapon{
    private int damage;
    private int ammo;
    protected Animation idle;
    protected Animation reload;
   // protected Animation attack;
    protected Animation current_anim;


    public Weapon(){};
    public Weapon(int d){
        damage = d;
    }
    public void setDamage(int d){
        damage =d;
    }
    public int getDamage(){
        return damage;
    }


    public boolean shoot(){
        if(ammo == 0)
            return false;
        ammo--;
        return true;
    }

    public void recharge(int level){
        ammo = level*3/2;    //tmp
        damage = damage*3/2;
    }

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
