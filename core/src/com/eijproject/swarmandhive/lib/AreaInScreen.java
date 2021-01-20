package com.eijproject.swarmandhive.lib;

import com.eijproject.swarmandhive.SwarmAndHive;

public class AreaInScreen {
    private Float top;
    private Float right;
    private Float bottom;
    private Float left;

    public AreaInScreen(Float top, Float right, Float bottom, Float left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public boolean checkIfInArea(int x, int y) {
        y = SwarmAndHive.getHeight() - y;

        if(x < right && x > left && y < bottom && y > top) return true;
        else return false;
    }
}
