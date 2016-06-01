package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rita on 01/06/2016.
 */
public class Tools
{
    static float vectorLength(Vector2 v)
    {
        float result;
        result = (float)Math.sqrt(Math.pow(v.x,2) + Math.pow(v.y,2));

        return result;
    }

    static float cartesianProduct(Vector2 v1, Vector2 v2)
    {
        float result;
        result = (v1.x * v2.x) + (v1.y * v2.y);
        return result;
    }

    static float calcAngle(Vector2 v1, Vector2 v2)
    {
        float result;
        result = (float)(1/(Math.cos(cartesianProduct(v1,v2)/(vectorLength(v1)*vectorLength(v2)))));
        return result;
    }
}
