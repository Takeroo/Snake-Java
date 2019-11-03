package com.artatech;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    private Boolean isEmpty;
    private Boolean hasMeal;

    public Cell(){
        isEmpty = true;
        hasMeal = false;
        setBounds(0, 0, 0, 0);
    }

    public Boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;

        if(!hasMeal){
            if(isEmpty) setBackground(Color.WHITE);
            else setBackground(Color.BLACK);
        }
    }

    public Boolean getHasMeal() {
        return hasMeal;
    }

    public void setHasMeal(Boolean hasMeal) {
        this.hasMeal = hasMeal;
        if(this.hasMeal) setBackground(Color.GREEN);
    }
}
