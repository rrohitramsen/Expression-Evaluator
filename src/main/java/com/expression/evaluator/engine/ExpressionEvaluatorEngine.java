package com.expression.evaluator.engine;

import com.expression.evaluator.api.EvaluatorExpressionException;
import com.expression.evaluator.entity.User;
import com.expression.evaluator.operator.Operator;
import com.expression.evaluator.operator.OperatorFactory;
import com.expression.evaluator.operator.OperatorNames;
import com.expression.evaluator.tree.Node;
import com.expression.evaluator.tree.Operand;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author rohitkumar
 * creation date 22/07/18
 * project name expression-evaluator
 */
public abstract class ExpressionEvaluatorEngine {

    private static final Logger LOGGER = Logger.getLogger(ExpressionEvaluatorEngine.class);

    /**
     * @implNote This util method evaluates the expression on the given Entity.
     * Format - [ OPERATOR, OPERAND, COMPARISON_VALUE(S) ]
     *          ["AND", ["EQ", "user.address.city", "Los Angeles"], ["GT", "user.age", 35]]
     * @param user
     * @param expression
     * @return true or false
     */
    public static boolean evaluateExpression(User user, ArrayList<Object> expression) throws EvaluatorExpressionException {

        LOGGER.debug("Evaluating expression :: "+expression+" on User :: "+user);

        /**
         * Build Expression tree using the expression.
         */
        Node root = buildTree(user, expression);

        /**
         * Evaluate the expression tree.
         */
        Object result = evaluateExpressionTree(root);

        return (Boolean) result;
    }

    /**
     * @implNote Evaluate expression tree using Post order traversal.
     * @param root
     * @return true or false
     */
    private static Object evaluateExpressionTree(Node root) {

        if (root != null) {

            /**
             * leaf node will contains the operand and substitution values..
             */
            if (root.getLeft() == null && root.getRight() == null) {
                return root.getOperand();
            }

            /**
             * Evaluate left subtree.
             */
            Object left = evaluateExpressionTree(root.getLeft());

            /**
             * Evaluate right subtree.
             */
            Object right = evaluateExpressionTree(root.getRight());

            /**
             * Since All non leaf nodes are Operator, Now evaluate the operator.
             */
            if (left instanceof Operand && right instanceof Operand) {

                return root.getOperator().execute(((Operand)left).getValue(), ((Operand)right).getValue());

            } else if (left instanceof Operand && !(right instanceof Operand)) {

                /**
                 * expression - ["AND", ["EQ", "user.address.city", "Los Angeles"], false]
                 */
                return root.getOperator().execute(((Operand)left).getValue(), right);

            } else if (!(left instanceof Operand) && (right instanceof Operand)) {

                /**
                 * expression - ["AND", ["EQ", "user.address.city", "Los Angeles"], false]
                 */
                return root.getOperator().execute(left, ((Operand)right).getValue());

            } else {

                return root.getOperator().execute(left, right);

            }


        }
        return false;
    }

    /**
     * @implNote Build Binary Tree of {@link com.expression.evaluator.operator.Operator} and {@link com.expression.evaluator.tree.Operand} using Pre order Traversal.
     * Format - [ OPERATOR, OPERAND, COMPARISON_VALUE(S) ]
     *          ["AND", ["EQ", "user.address.city", "Los Angeles"], ["GT", "user.age", 35]]
     *          ["OR", ["IN", "event.category", ["infant", "child", "teen"]], ["LT", "user.age", 18]]
     * @param user
     * @param expression
     * @return root node of the tree.
     */
    private static Node buildTree(User user, ArrayList<Object> expression) throws EvaluatorExpressionException {

        Node root = null;
        if (expression != null) {

            /**
             * Check whether its operator or not
             */
            Object value = expression.get(0);
            if (OperatorNames.isOperator(value.toString())) {

                Operator operator = OperatorFactory.getOperator(value.toString());
                if (Objects.isNull(operator)) {
                    throw new EvaluatorExpressionException("Invalid Operator Name,"+value.toString());
                }
                root = new Node(operator);

            }

            if ( (expression.get(1) instanceof ArrayList) && isExpression((ArrayList<Object>) expression.get(1))) {
                /**
                 * sub-expression, recursive call
                 */
                root.setLeft(buildTree(user, (ArrayList<Object>) expression.get(1)));

            } else {

                /**
                 * "user.address.city" , "event.category"
                 */
                Operand operand = buildOperandFromExpression(user, expression.get(1));
                Node leftNode = new Node(operand);
                root.setLeft(leftNode);
            }

            if ( (expression.get(2) instanceof ArrayList) && isExpression((ArrayList<Object>) expression.get(2))) {
                /**
                 * sub-expression, recursive call
                 */
                root.setRight(buildTree(user, (ArrayList<Object>) expression.get(2)));

            } else {

                /**
                 * Substitution-Values
                 */
                Node rightNode = new Node(new Operand(expression.get(2)));
                root.setRight(rightNode);
            }

        }
        return root;
    }

    /**
     * @implNote Build operand from given expression using the {@link User}
     * @param user
     * @param expression
     * @return Operand
     */
    private static Operand buildOperandFromExpression(User user, Object expression) throws EvaluatorExpressionException {

        if ( !(expression instanceof String)) {
            throw new EvaluatorExpressionException("Invalid Operand Name,"+expression.toString());
        }

        String [] values = ((String) expression).split("\\.");
        Object operandValue = user;

        String userClassName = User.class.getSimpleName().toLowerCase();
        boolean classNameFound = false;

        for (String value : values) {

            value = value.toLowerCase();

            /**
             * To handle cases where operand is like - "user.address.city"
             */
            if (!classNameFound && userClassName.contains(value)) {
                classNameFound = true;
                continue;

            } else {

                for (Method method : operandValue.getClass().getDeclaredMethods()) {

                    if (method.getName().toLowerCase().contains("get"+value)) {
                        try {
                            operandValue = method.invoke(operandValue, null);
                            break;
                        } catch (Exception e) {
                            throw new EvaluatorExpressionException("Invalid Operand Name,"+expression.toString()+e.getLocalizedMessage());
                        }
                    }
                }

            }
        }
        Operand operand = new Operand(operandValue);
        return operand;
    }

    /**
     * @implNote Its must be in FORMAT - [ OPERATOR, OPERAND, COMPARISON_VALUE(S) ]
     * @param expression
     * @return
     */
    public static boolean isExpression(ArrayList<Object> expression) {

        int length = expression.size();

        if (length != 3) {
            return false;
        }

        Object value1 = expression.get(0);
        if (!OperatorNames.isOperator(value1.toString())) {
            return false;
        }
        return true;
    }
}
