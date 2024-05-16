package sample;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.DecimalFormat;
import java.util.Stack;

public class Controller {
    //declare needed variables
    Stack<Float> displayStack = new Stack<>();

    @FXML
    private Label displayText = new Label();

    @FXML
    Button b_1 = new Button(),
           b_2 = new Button(),
           b_3 = new Button(),
           b_4 = new Button(),
           b_5 = new Button(),
           b_6 = new Button(),
           b_7 = new Button(),
           b_8 = new Button(),
           b_9 = new Button(),
           b_0 = new Button(),
           b_add = new Button(),
           b_subtract = new Button(),
           b_multiply = new Button(),
           b_divide = new Button(),
           b_clear = new Button(),
           b_space = new Button(),
           b_calculate = new Button();


    //Create the functions to handle when each choice is clicked
    @FXML
    private void b_1Clicked() {
        appendToDisplay("1");
    }

    @FXML
    private void b_2Clicked() {
        appendToDisplay("2");
    }

    @FXML
    private void b_3Clicked() {
        appendToDisplay("3");
    }

    @FXML
    private void b_4Clicked() {
        appendToDisplay("4");
    }

    @FXML
    private void b_5Clicked() {
        appendToDisplay("5");
    }

    @FXML
    private void b_6Clicked() {
        appendToDisplay("6");
    }

    @FXML
    private void b_7Clicked() {
        appendToDisplay("7");
    }

    @FXML
    private void b_8Clicked() {
        appendToDisplay("8");
    }

    @FXML
    private void b_9Clicked() {
        appendToDisplay("9");
    }

    @FXML
    private void b_0Clicked() {
        appendToDisplay("0");
    }

    @FXML
    private void addClicked() {
        appendToDisplay("+");
    }

    @FXML
    private void subtractClicked() {
        appendToDisplay("-");
    }

    @FXML
    private void multiplyClicked() {
        appendToDisplay("*");
    }

    @FXML
    private void divideClicked() {
        appendToDisplay("/");
    }

    @FXML
    private void spaceClicked() {
        appendToDisplay(" ");
    }

    @FXML
    private void clearClicked() {
        displayText.setText("");
    }

    @FXML
    private void calculateClicked() {
        calculateDisplay(displayStack);
    }

    private void appendToDisplay(String input) {
        displayText.setText(displayText.getText() + input);
    }

    //Function to handle calculations and display them
    private void calculateDisplay(Stack<Float> displayStack) {
        String[] displayString = displayText.getText().split(" ");
        float userNumerical, operand1, operand2;

        //read displayString until the end
        for (String symbol : displayString) {
            if (symbol.equals("+")) {
                try {
                    //pop the last 2 values from the stack
                    operand2 = displayStack.pop();
                    operand1 = displayStack.pop();

                    //push back their sum
                    displayStack.push(operand1 + operand2);
                } catch (Exception exception) {
                    displayText.setText("Err: Need 2 operands");
                    displayStack.clear();
                    break;
                }

            } else if (symbol.equals("-")) {
                try {
                    //pop the last 2 values from the stack
                    operand2 = displayStack.pop();
                    operand1 = displayStack.pop();

                    //push back the difference (second value – first value)
                    displayStack.push(operand1 - operand2);
                } catch (Exception exception) {
                    displayText.setText("Err: Need 2 operands");
                    displayStack.clear();
                    break;
                }
            } else if (symbol.equals("*")) {
                try {
                    //pop the last 2 values from the stack
                    operand2 = displayStack.pop();
                    operand1 = displayStack.pop();

                    //push back their product
                    displayStack.push(operand1 * operand2);
                } catch (Exception exception) {
                    displayText.setText("Err: Need 2 operands");
                    displayStack.clear();
                    break;
                }
            } else if (symbol.equals("/")) {
                try {
                    //pop the last 2 values from the stack
                    operand2 = displayStack.pop();
                    operand1 = displayStack.pop();

                    //Throw an exception if dividing by zero
                    try {
                        if (operand2 == 0) {
                            Exception exception = new Exception("Err: Divide by 0!");
                            throw exception;
                        }

                        //push back the quotient (second value / first value)
                        displayStack.push(operand1 / operand2);
                    } catch (Exception exception) {
                        displayText.setText(exception.getMessage());
                        displayStack.clear();
                        break;
                    }
                } catch (Exception exception) {
                    displayText.setText("Err: Need 2 operands");
                    displayStack.clear();
                    break;
                }
            } else {
                // convert to a Float and push it on the stack
                try {
                    userNumerical = Float.parseFloat(symbol);
                    displayStack.push(userNumerical);
                }
                catch (Exception exception) {
                    displayText.setText("Err: Check spaces");
                    displayStack.clear();
                    break;
                }
            }
        }

        //Check the stack only hold the result
        if(displayStack.size() > 1) {
            displayText.setText("Err: Too many operands");
        }
        else {
            DecimalFormat df = new DecimalFormat("#.#####");
            float result_f = displayStack.pop();
            String result_s = df.format(result_f);
            displayText.setText(result_s);
        }
    }
}