package logic;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Vector;

import logic.Item;

public class Player extends Sprite {
    private ArrayList<Item> bag = new ArrayList<Item>();
    int life;
    int damage = 1; //init
    Item inUse;
    Vector<Integer> direction;

    public Player(int life,int x,int y){    //inicializar a arma
        this.life = life;
        super.setPosition(x,y);
    }



    public void equipItem(int i){
        inUse = bag.get(i);
        if(inUse.getClass() == Weapon.class){
            damage = ((Weapon)inUse).getDamage();
        }
        else
            damage = 0;
    }

    public void setLife(int life){
        life = life;
    }

    public int getLife(){
        return life;
    }

    public boolean addItem(Item i){
        if(!bag.contains(i))
        {
            bag.add(i);
            return true;
        }
        return false;
    }


}
