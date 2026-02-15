/*
 * Michael Allen
 * Project 4
 * 7/6/2025
 * This file is the immutable file to create the vertex's. It includes getters, constructors, and a to String method to help the dfs and bfs buttons.
 */

package com.example.week8;

public final class Vertex {
    
    String name;
    double x;
    double y;

    public Vertex(String name, double x, double y){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;  // or whatever field holds the vertex label
    }
}
