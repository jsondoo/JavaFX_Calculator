import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.LinkedList;

/**
 * Created by jsondoo on 2016-09-23.
 */
public class Calculator extends Application {
    private final int WIDTH = 145;
    private final int HEIGHT = 225;

    private Label textScreen = new Label("0");
    private Button filler = new Button("x");
    private Button backspace = new Button("←");
    private Button buttonC = new Button("C");
    private Button button1 = new Button("1");
    private Button button2 = new Button("2");
    private Button button3 = new Button("3");
    private Button button4 = new Button("4");
    private Button button5 = new Button("5");
    private Button button6 = new Button("6");
    private Button button7 = new Button("7");
    private Button button8 = new Button("8");
    private Button button9 = new Button("9");
    private Button button0 = new Button("0");
    private Button buttonPlus = new Button("+");
    private Button buttonMinus = new Button("-");
    private Button buttonMultiply = new Button("*");
    private Button buttonDivide = new Button("/");
    private Button buttonEqual = new Button("=");
    private Button buttonSign = new Button("±");
    private Button buttonDot = new Button(".");

    private boolean resetOnNextNumber = false;

    // store numbers and operands, calculates only when equal is pressed
    private LinkedList<Object> toCalculate = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(getTopPane());
        borderPane.setCenter(getBottomPane());

        // Event handling for button0-9
        button0.setOnAction(e -> handleNumberButton(e));
        button1.setOnAction(e -> handleNumberButton(e));
        button2.setOnAction(e -> handleNumberButton(e));
        button3.setOnAction(e -> handleNumberButton(e));
        button4.setOnAction(e -> handleNumberButton(e));
        button5.setOnAction(e -> handleNumberButton(e));
        button6.setOnAction(e -> handleNumberButton(e));
        button7.setOnAction(e -> handleNumberButton(e));
        button8.setOnAction(e -> handleNumberButton(e));
        button9.setOnAction(e -> handleNumberButton(e));

        // Event handling for +,-,/,*
        buttonMinus.setOnAction(e -> handleOperandButton(e));
        buttonPlus.setOnAction(e -> handleOperandButton(e));
        buttonMultiply.setOnAction(e -> handleOperandButton(e));
        buttonDivide.setOnAction(e -> handleOperandButton(e));

        // rest of the buttons
        buttonSign.setOnAction(e -> {
            if(textScreen.getText().equals("0"))
                return;

            if (textScreen.getText().contains("-")) {
                textScreen.setText(textScreen.getText().replace("-", ""));
            } else {
                textScreen.setText("-" + textScreen.getText());
            }
        });
        buttonDot.setOnAction(e -> {
            if (!textScreen.getText().contains(".")) {
                textScreen.setText(textScreen.getText() + ".");
            }
        });
        buttonC.setOnAction(e -> textScreen.setText("0"));
        backspace.setOnAction(e ->{
            String str = textScreen.getText();
            int length = str.length();
            // matches positive or negative integers
            if(str.matches("-?[0-9]")){
                textScreen.setText("0");
            }
            else{
                textScreen.setText(str.substring(0,length-1));
            }
        });

        // handling for equals button
        buttonEqual.setOnAction(e->calculate());

        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }

    /**
     * Sets up the top half of the GUI and its properties
     * @return VBox with text screen
     */
    private VBox getTopPane() {
        VBox topPane = new VBox();
        topPane.setAlignment(Pos.CENTER_RIGHT);
        topPane.setPadding(new Insets(10));
        topPane.getChildren().add(textScreen);
        textScreen.setFont(Font.font("Arial", 20));
        return topPane;
    }

    /**
     * Sets up bottom half of the GUI and its properties
     * @return grid pane with buttons
     */
    private GridPane getBottomPane() {
        GridPane bottomPane = new GridPane();
        bottomPane.setHgap(10);
        bottomPane.setVgap(10);
        bottomPane.setPadding(new Insets(10));

        bottomPane.add(filler, 0, 0);
        bottomPane.add(buttonC, 1, 0);
        bottomPane.add(backspace, 2, 0);
        bottomPane.add(buttonPlus, 3, 0);

        bottomPane.add(button1, 0, 1);
        bottomPane.add(button2, 1, 1);
        bottomPane.add(button3, 2, 1);
        bottomPane.add(buttonMinus, 3, 1);

        bottomPane.add(button4, 0, 2);
        bottomPane.add(button5, 1, 2);
        bottomPane.add(button6, 2, 2);
        bottomPane.add(buttonMultiply, 3, 2);

        bottomPane.add(button7, 0, 3);
        bottomPane.add(button8, 1, 3);
        bottomPane.add(button9, 2, 3);
        bottomPane.add(buttonDivide, 3, 3);

        bottomPane.add(buttonSign, 0, 4);
        bottomPane.add(button0, 1, 4);
        bottomPane.add(buttonDot, 2, 4);
        bottomPane.add(buttonEqual, 3, 4);

        buttonDot.setMaxWidth(Double.MAX_VALUE);
        buttonMinus.setMaxWidth(Double.MAX_VALUE);
        buttonMultiply.setMaxWidth(Double.MAX_VALUE);
        buttonDivide.setMaxWidth(Double.MAX_VALUE);

        return bottomPane;
    }

    /**
     * Responsible for handling number buttons
     * @param e
     */
    private void handleNumberButton(ActionEvent e) {
        if(resetOnNextNumber){
            this.resetOnNextNumber = false;
            textScreen.setText("0");
        }


        // get the string of the button
        String textToSet = ((Button) e.getSource()).getText();

        // special case for button0
        if (e.getSource().equals(button0)) {
            if (!textScreen.getText().equals("0")) {
                textScreen.setText(textScreen.getText() + textToSet);
            }
        }
        // for other number buttons, set the text to corresponding button
        else {
            if (textScreen.getText().equals("0")) {
                textScreen.setText(textToSet);

            } else {
                textScreen.setText(textScreen.getText() + textToSet);
            }
        }
    }

    /**
     * Responsible for handling "+","-","/","*" buttons
     * @param e
     */
    private void handleOperandButton(ActionEvent e) {
       if(this.resetOnNextNumber){
           return;
       }
        //get the number inputted so far
        double input;
        try {
            input = Double.parseDouble(textScreen.getText());
        }
        catch(NumberFormatException exp){
            // TODO: exception better handling
            System.out.println("Error");
            return;
        }

        // store the number and operand into the LinkedList
        toCalculate.add(input);
        if(e.getSource().equals(buttonPlus)){
            toCalculate.add(Operand.ADD);
        }
        else if(e.getSource().equals(buttonMinus)){
            toCalculate.add(Operand.SUB);
        }
        else if(e.getSource().equals(buttonMultiply)){
            toCalculate.add(Operand.MUL);
        }
        else if(e.getSource().equals(buttonDivide)){
            toCalculate.add(Operand.DIV);
        }
        this.resetOnNextNumber = true;
    }

    /**
     * Assume that this.toCalculate has contains alternating numbers and operands
     * Calculates and displays the result on this.textScreen
     */
    private void calculate(){
        if(resetOnNextNumber){
            return;
        }

        // gets the final input
        double input;
        try {
            input = Double.parseDouble(textScreen.getText());
        }
        catch(NumberFormatException exp){
            // TODO: better exception handling
            System.out.println("Error");
            return;
        }

        toCalculate.add(input);
        String concat = "";

        // create string form of the math expression
        for(Object o : toCalculate){
            concat += o.toString();
        }

        double result = Evaluator.eval(concat);
        textScreen.setText(Double.toString(result));
        toCalculate.clear();
        this.resetOnNextNumber = true;
    }



}



