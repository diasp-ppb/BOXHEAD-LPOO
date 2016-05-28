package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Catarina Ramos on 12/05/2016.
 */
public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State st){
        states.push(st);
    }

    public void pop(){
        states.pop();
    }

    public void set(State st){
        states.pop();
        states.push(st);
    }

    public void update(float delta_time){   //delta_time between renders
        states.peek().update(delta_time);
    }

    public void render(SpriteBatch s_batch){
        states.peek().render(s_batch);
    }
}
