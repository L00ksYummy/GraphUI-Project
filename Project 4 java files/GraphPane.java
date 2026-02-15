/*
 * Michael Allen
 * Project 4
 * 7/6/2025
 * This file contains the pane code so that the area where the graph is shown actually pops up. It also holds the code to allow
 * a left click to create a small bubble with a label that generates letters alphabetically up to a certain point. 
 * It also holds the code the add edge button needs
 */

package com.example.week8;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphPane extends Pane{
    private Graph graph;

    public GraphPane(Graph graph){
        this.graph = graph;

        setOnMouseClicked(e ->{
            double x = e.getX();
            double y = e.getY();
            String name = generateNextLetter();
            Vertex v = new Vertex(name, x, y);
            graph.addVertex(v);
            drawVertex(v);
        });
    }

    private void drawVertex(Vertex v) {
        Circle circle = new Circle(v.getX(), v.getY(), 6, Color.BLACK);
        Text label = new Text(v.getX() - 4, v.getY() - 10, v.getName());
        getChildren().addAll(circle, label);
    }

    private String generateNextLetter(){
        int index = graph.getVertices().size();
        return String.valueOf((char) ('A' + index));
    }

    public void drawEdge(Vertex v1, Vertex v2) {
        Line line = new Line(v1.getX(), v1.getY(), v2.getX(), v2.getY());
        getChildren().add(line);
    }
}
