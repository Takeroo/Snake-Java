package com.artatech;

import java.util.ArrayList;

public class Snake {

    private Integer size;
    private ArrayList<Coordinate> coordinates;

    public Snake(Integer x, Integer y){
        coordinates = new ArrayList<>();
        size = 1;
        coordinates.add(new Coordinate(x, y));
    }

    public void reSize(){
        coordinates.add(0, new Coordinate(getHead().getX(), getHead().getY()));
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinate getHead(){
        return coordinates.get(0);
    }
}
