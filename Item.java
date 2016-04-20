package logic;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Vector;

public class Item extends Sprite{
    private int ammount;

    public Item(){};
    public Item(int n){
        ammount = n;
    };
    public void useItem(Vector<Integer> direction){ammount--;};  //fazer override depois
    public void setItem(int n){ammount = n;};
}
