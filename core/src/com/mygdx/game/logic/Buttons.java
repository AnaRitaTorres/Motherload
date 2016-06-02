package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Motherload;

/**
 * Created by Rita on 02/06/2016.
 */
public class Buttons
{


        private Texture buttonTex;
        private Vector2 coordinates;
        private float width,height;



        public Buttons (String texture, float x, float y)
        {
            buttonTex = new Texture(texture);
            coordinates = new Vector2(x,y);
            width = buttonTex.getWidth();
            height = buttonTex.getHeight();
        }

        public boolean Touched(float x, float y)
        {
            double graphical_y = Motherload.V_HEIGHT - y - height;

            if(x > Math.abs(coordinates.x) && x < Math.abs(coordinates.x + width) && graphical_y < Math.abs(coordinates.y) && graphical_y > Math.abs(coordinates.y - height))
            {
                return true;
            }
            else return false;
        }

        public Texture getButtonTex()
        {
            return buttonTex;
        }


        public float getHeight()
        {
            return height;
        }

        public float getWidth()
        {
            return width;
        }


}
