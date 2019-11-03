package com.artatech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JFrame {

    private Cell[][] cells;
    JPanel boardPanel;
    private Integer ROWS = 40;
    private Timer timer;
    private TimerTask timerTask;
    private Direction direction;
    private Snake snake;
    private Coordinate meal;

    public Board(){
        boardPanel = new JPanel(new GridLayout(ROWS, ROWS));
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                setDirection(e);
            }
        });
        snake = new Snake(ROWS/2, ROWS/2);
        cells = new Cell[ROWS][ROWS];
        generateCells();
        drawSnake();
        meal = assignMeal();
        add(boardPanel);
        init();
    }

    public void init(){
        timerTask = new TimerTask() {
            public void run() {
                move();
            }
        };
        timer = new Timer("Timer");
        long delay = 500L;
        long period = 150L;
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }

    public void generateCells() {
        cells = new Cell[ROWS][ROWS];
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < ROWS; j++){
                cells[i][j] = new Cell();
                boardPanel.add(cells[i][j]);
            }
        }
    }

    public void drawSnake(){
        emptyCells();
        ArrayList<Coordinate> coordinates = snake.getCoordinates();
        for(Coordinate coordinate : coordinates){
            cells[coordinate.getY()][coordinate.getX()].setEmpty(false);
        }
    }

    public void emptyCells(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < ROWS; j++){
                cells[i][j].setEmpty(true);
            }
        }
    }

    public void move(){
        if(direction != null){
            switch (direction){
                case NORTH: moveUp();
                break;
                case EAST: moveRight();
                break;
                case SOUTH: moveDown();
                break;
                case WEST: moveLeft();
                break;
            }
            checkMeal();
            drawSnake();
        }
    }

    public void setDirection(KeyEvent event) {
        switch( event.getKeyCode() ) {
            case KeyEvent.VK_UP: direction = Direction.NORTH;
                break;
            case KeyEvent.VK_DOWN: direction = Direction.SOUTH;
                break;
            case KeyEvent.VK_LEFT: direction = Direction.WEST;
                break;
            case KeyEvent.VK_RIGHT : direction = Direction.EAST;
                break;
        }
    }

    public void moveUp() {
        ArrayList<Coordinate> coordinates = snake.getCoordinates();
        Coordinate head = coordinates.get(0);
        Coordinate tail = coordinates.get(coordinates.size() - 1);
        Integer x, y;
        x = head.getX();
        if(head.getY() == 0) y = ROWS - 1;
        else y = head.getY() - 1;
        Coordinate newHead = new Coordinate(x, y);
        coordinates.remove(tail);
        coordinates.add(0, newHead);
    }

    public void moveRight() {
        ArrayList<Coordinate> coordinates = snake.getCoordinates();
        Coordinate head = coordinates.get(0);
        Coordinate tail = coordinates.get(coordinates.size() - 1);
        Integer x, y;
        y = head.getY();
        if(head.getX() == ROWS - 1) x = 0;
        else x = head.getX() + 1;
        Coordinate newHead = new Coordinate(x, y);
        coordinates.remove(tail);
        coordinates.add(0, newHead);
    }

    public void moveDown() {
        ArrayList<Coordinate> coordinates = snake.getCoordinates();
        Coordinate head = coordinates.get(0);
        Coordinate tail = coordinates.get(coordinates.size() - 1);
        Integer x, y;
        x = head.getX();
        if(head.getY() == ROWS - 1) y = 0;
        else y = head.getY() + 1;
        Coordinate newHead = new Coordinate(x, y);
        coordinates.remove(tail);
        coordinates.add(0, newHead);
    }

    public void moveLeft() {
        ArrayList<Coordinate> coordinates = snake.getCoordinates();
        Coordinate head = coordinates.get(0);
        Coordinate tail = coordinates.get(coordinates.size() - 1);
        Integer x, y;
        y = head.getY();
        if(head.getX() == 0) x = ROWS - 1;
        else x = head.getX() - 1;
        Coordinate newHead = new Coordinate(x, y);
        coordinates.remove(tail);
        coordinates.add(0, newHead);
    }

    public void checkMeal(){
        Coordinate head = snake.getHead();
        if(cells[head.getY()][head.getX()].getHasMeal()){
            snake.reSize();
            meal = assignMeal();
        }
    }

    public Coordinate assignMeal(){
        Integer x, y;
        Random random = new Random();
        do {
           x = random.nextInt(ROWS);
           y = random.nextInt(ROWS);
        } while (!cells[x][y].getEmpty());

        if(meal != null){
            cells[meal.getX()][meal.getY()].setHasMeal(false);
        }
        cells[x][y].setHasMeal(true);
        return new Coordinate(x, y);
    }
}
