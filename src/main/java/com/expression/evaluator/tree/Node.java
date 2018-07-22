package com.expression.evaluator.tree;

import com.expression.evaluator.operator.Operator;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public class Node {

    private Operator operator;
    private Operand operand;
    private Node left;
    private Node right;
    private boolean isOperator = false;
    private boolean isOperand = false;

    public Node(Operator operator) {
        this(operator, null, null, null);
        this.operator = operator;
        this.isOperator = true;
    }

    public Node(Operand operand) {
        this(null, operand, null, null);
        this.operand = operand;
        this.isOperand = true;
    }

    private Node(Operator operator, Operand operand, Node left, Node right) {
        this.operator = operator;
        this.operand = operand;
        this.left = left;
        this.right = right;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Operand getOperand() {
        return operand;
    }

    public void setOperand(Operand operand) {
        this.operand = operand;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isOperator() {
        return isOperator;
    }

    public boolean isOperand() {
        return isOperand;
    }
    
}
