package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;

/**
 * The Tools Class
 */
public class Tools
{
    /**
     * calculates vector length
     * @param v vector
     * @return vector length
     */
    static float vectorLength(Vector2 v)
    {
        float result;
        result = (float)Math.sqrt(Math.pow(v.x,2) + Math.pow(v.y,2));

        return result;
    }

    /**
     * calculates cartesian product of two vectors
     * @param v1 vector 1
     * @param v2 vector 2
     * @return cartesian product
     */
    static float cartesianProduct(Vector2 v1, Vector2 v2)
    {
        float result;
        result = (v1.x * v2.x) + (v1.y * v2.y);
        return result;
    }

    /**
     * calculates angle between two vectors
     * @param v1 vector 1
     * @param v2 vector 2
     * @return angle between the two vectors
     */
    static float calcAngle(Vector2 v1, Vector2 v2)
    {
        float result;
        result = (float)(1/(Math.cos(cartesianProduct(v1,v2)/(vectorLength(v1)*vectorLength(v2)))));
        return result;
    }
}
