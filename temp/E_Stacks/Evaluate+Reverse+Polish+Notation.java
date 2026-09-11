class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>(); 
        
        for(String token: tokens) {
            if (isOperator(token)) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                
                int result = evaluate(token, operand1, operand2);
                stack.push(result);
            }
            else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
    
    public boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
    
    public int evaluate(String token, int operand1, int operand2) {
        switch(token) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                return 0;
        }
    }
}
