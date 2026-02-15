/*
 * Michael Allen
 * Project 4
 * 7/6/2025
 * This file is the main file that holds the majority of the GUI code. This includes text fields, buttons and their functions, the scene, and handlers for errors 
 * if something goes wrong when hitting any of the buttons.
 */

package com.example.week8;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Graph graph = new Graph();
    private GraphPane graphPane = new GraphPane(graph);

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setCenter(graphPane);

        // Input fields and add edge button
        TextField vertex1Field = new TextField();
        TextField vertex2Field = new TextField();
        Button addEdgeBtn = new Button("Add Edge");
        HBox edgeInputBox = new HBox(5, new Label("V1:"), vertex1Field, new Label("V2:"), vertex2Field, addEdgeBtn);
        edgeInputBox.setStyle("-fx-padding: 10;");
        edgeInputBox.setAlignment(Pos.CENTER);

       // Other graph operation buttons
        Button isConnectedBtn = new Button("Is Connected");
        Button hasCyclesBtn = new Button("Has Cycles");
        Button dfsBtn = new Button("DFS");
        Button bfsBtn = new Button("BFS");
        HBox graphOpsBox = new HBox(10, isConnectedBtn, hasCyclesBtn, dfsBtn, bfsBtn);
        graphOpsBox.setStyle("-fx-padding: 10;");
        graphOpsBox.setAlignment(Pos.CENTER);

        // Output text field
        TextField outputField = new TextField();
        outputField.setEditable(false);
        outputField.setPrefWidth(400);
        HBox outputBox = new HBox(outputField);
        outputBox.setStyle("-fx-padding: 10;");
        outputBox.setAlignment(Pos.CENTER);

        // Combine controls vertically
        VBox controls = new VBox(10, edgeInputBox, graphOpsBox, outputBox);
        controls.setStyle("-fx-background-color:rgb(255, 255, 255); -fx-padding: 10;");
        root.setBottom(controls);

        // Button actions
        addEdgeBtn.setOnAction(e -> {
            String name1 = vertex1Field.getText();
            String name2 = vertex2Field.getText();
            Vertex v1 = graph.findVertexByName(name1);
            Vertex v2 = graph.findVertexByName(name2);
            if (v1 != null && v2 != null) {
                graph.addEdge(v1, v2);
                graphPane.drawEdge(v1, v2);
                outputField.setText("Edge added.");
            } else {
                outputField.setText("Vertex name(s) not found.");
            }
        });

        isConnectedBtn.setOnAction(e -> {
            outputField.setText(graph.isConnected() ? "Graph is connected." : "Graph is NOT connected.");
        });

        hasCyclesBtn.setOnAction(e -> {
            outputField.setText(graph.hasCycle() ? "Graph has cycles." : "Graph has NO cycles.");
        });

        dfsBtn.setOnAction(e -> {
            List<Vertex> order = graph.dfs();
            outputField.setText("DFS: " + order);
        });

        bfsBtn.setOnAction(e -> {
            Vertex start = graph.findVertexByName("A");
            if (start != null) {
                List<Vertex> order = graph.bfs(start);
                outputField.setText("BFS: " + order);
            } else {
                outputField.setText("No start vertex A found.");
            }
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Graph Visualizer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}