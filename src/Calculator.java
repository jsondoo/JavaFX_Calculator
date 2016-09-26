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

import java.util.ArrayList;

/**
 * Created by jsondoo on 2016-09-23.
 */
public class Calculator extends Application {
    private Label labelA = new Label("");
    private Label labelB = new Label("0");

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

    private ArrayList<Object> toCalculate = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(getTopPane());
        borderPane.setCenter(getBottomPane());

        // Event handling for button0-9
        button0.setOnAction(e-> handleNumberButton(e));
        button1.setOnAction(e-> handleNumberButton(e));
        button2.setOnAction(e-> handleNumberButton(e));
        button3.setOnAction(e-> handleNumberButton(e));
        button4.setOnAction(e-> handleNumberButton(e));
        button5.setOnAction(e-> handleNumberButton(e));
        button6.setOnAction(e-> handleNumberButton(e));
        button7.setOnAction(e-> handleNumberButton(e));
        button8.setOnAction(e-> handleNumberButton(e));
        button9.setOnAction(e-> handleNumberButton(e));

        // Event handling for ±,+,-,/,*,=
        buttonSign.setOnAction(e->{
            if(labelB.getText().contains("-")){
                labelB.setText(labelB.getText().replace("-",""));
            }
            else{
                labelB.setText("-"+labelB.getText());
            }
        });
        buttonMinus.setOnAction(e->handleOperandButton(e));
        buttonPlus.setOnAction(e->handleOperandButton(e));
        buttonMultiply.setOnAction(e->handleOperandButton(e));
        buttonDivide.setOnAction(e->handleOperandButton(e));
        // TODO: operands

        Scene scene = new Scene(borderPane, 140 ,200);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }

    // top pane representing the label
    private VBox getTopPane(){
        VBox topPane = new VBox();
        topPane.setAlignment(Pos.CENTER_RIGHT);
        topPane.setPadding(new Insets(10));
        topPane.getChildren().addAll(labelA,labelB);
        labelA.setFont(Font.font("Arial",10));
        labelB.setFont(Font.font("Arial",20));
        return topPane;
    }

    // bottom pane with the number/operand buttons
    private GridPane getBottomPane(){
        GridPane bottomPane = new GridPane();
        bottomPane.setHgap(10);
        bottomPane.setVgap(10);
        bottomPane.setPadding(new Insets(10));
        bottomPane.add(button1,0,0);
        bottomPane.add(button2,1,0);
        bottomPane.add(button3,2,0);
        bottomPane.add(buttonPlus,3,0);

        bottomPane.add(button4,0,1);
        bottomPane.add(button5,1,1);
        bottomPane.add(button6,2,1);
        bottomPane.add(buttonMinus,3,1);

        bottomPane.add(button7,0,2);
        bottomPane.add(button8,1,2);
        bottomPane.add(button9,2,2);
        bottomPane.add(buttonMultiply,3,2);

        bottomPane.add(buttonSign,0,3);
        bottomPane.add(button0,1,3);
        bottomPane.add(buttonEqual,2,3);
        bottomPane.add(buttonDivide,3,3);

        buttonMinus.setMaxWidth(Double.MAX_VALUE);
        buttonMultiply.setMaxWidth(Double.MAX_VALUE);
        buttonDivide.setMaxWidth(Double.MAX_VALUE);

        return bottomPane;
    }

    private void handleNumberButton(ActionEvent e) {


        // special case for button0
        if (e.getSource().equals(button0)) {
            if (!labelB.getText().equals("0")) {
                labelB.setText(labelB.getText() + getButtonString((Button)e.getSource()));
            }
        }
        // for other number buttons, set the text to corresponding button
        else {
            if (labelB.getText().equals("0")) {
                labelB.setText(getButtonString((Button)e.getSource()));
            }
            else{
                labelB.setText(labelB.getText()+getButtonString((Button)e.getSource()));
            }
        }
    }

    private void handleOperandButton(ActionEvent e){
        /** this.state = true;
        if(num == null) {
            this.num = Integer.parseInt(labelB.getText());
            if(e.getSource().equals(buttonPlus)) {
                this.storedOperand = Operand.PLUS;
                this.savedOperand = Operand.PLUS;
            }
            else if(e.getSource().equals(buttonMultiply)){
                this.storedOperand = Operand.MULTIPLY;
                this.savedOperand = Operand.MULTIPLY;
            }
            else if(e.getSource().equals(buttonMinus)){
                this.storedOperand = Operand.MINUS;
                this.savedOperand = Operand.MINUS;
            }
            else{
                this.storedOperand = Operand.DIVIDE;
                this.savedOperand = Operand.DIVIDE;
            }
        }
        else {
            if(e.getSource().equals(buttonPlus)){
                Integer num = Integer.parseInt(labelB.getText()) + this.num;
                labelB.setText(num.toString());
            }





        }
*/

    }


    /**
     *
     * @param button
     * @return string form of the button (ex. "1","2","3")
     */
   private String getButtonString(Button button){
       if(button.equals(button0)){
           return "0";
       }
       else if(button.equals(button1)){
           return "1";
       }
       else if(button.equals(button2)){
           return "2";
       }
       else if(button.equals(button3)){
           return "3";
       }
       else if(button.equals(button4)){
           return "4";
       }
       else if(button.equals(button5)){
           return "5";
       }
       else if(button.equals(button6)){
           return "6";
       }
       else if(button.equals(button7)){
           return "7";
       }
       else if(button.equals(button8)){
           return "8";
       }
       else if(button.equals(button9)){
           return "9";
       }

       return "";
   }
}



