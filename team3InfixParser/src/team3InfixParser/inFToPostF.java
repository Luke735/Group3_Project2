package team3InfixParser;

import java.util.Scanner;

public class inFToPostF {

	
	public static String infixToPostfix(String infixExp) {
        Scanner scanner = new Scanner(infixExp);
        // Initialize a stack.
        Stack<String> stack = new Stack<>();
        // Initialize the post-fix expression.
        StringBuilder postfixExp = new StringBuilder();
        // Parse each token in the infix expression.
        
        for (int i = 0; i < infixExp.length(); i++) {
        	
        	/* If the current character is an operand, append it to the postfix expression string
        	   and continue to do so until the next character is not, then append ' ' before continuing */
        	while ((Character.isDigit(infixExp.charAt(i))) && (i < infixExp.length())) {
        		postfixExp.append(infixExp.charAt(i));
            	i++;
            	if (!(Character.isDigit(infixExp.charAt(i)))) { postfixExp.append(' '); }
            }
        	
        	//Ignore whitespace
        	if (infixExp.charAt(i) != ' ') {
	            // If the current character is opening parenthesis, push it onto the stack.
	            if (infixExp.charAt(i) == '(') { stack.push("("); }
	            
	            // The current character is closing parenthesis.
	            else if (infixExp.charAt(i) == ')') {
	                // Pop all operators from the stack, until an opening parenthesis is seen on top of the stack.
	                // Append each popped operator to the post-fix expression string.
	                while (!stack.peek().equals("(")) { postfixExp.append(stack.pop()).append(' '); }
	                // Pop the opening parenthesis from the stack.
	                stack.pop();
	            }
	            else if (!(Character.isDigit(infixExp.charAt(i)))) {
	                // The current token is an operator.
	                // Keep popping operators (and append them to post-fix expression string) from the stack, until:
	                // 1) the stack is empty,
	                // 2) an opening parenthesis is seen on top of the stack, or
	                // 3) the current operator has higher precedence than the operator on top of the stack.
	                while (!stack.isEmpty() && !stack.peek().equals("(") && precedence(Character.toString(infixExp.charAt(i))) <= precedence(stack.peek())) {
	                    postfixExp.append(stack.pop()).append(' ');
	                }
	                
	                // Push the current operator onto the stack.
	                /* If the current operator is two characters long, we need special code to recognize it,
	                   either by skipping the char after the first one is recognized (| is guaranteed to be follow by another |, so
	                   use | to represent || and skip the second |) or by using a different symbol to recognize it (< can be < or <=, if it
	                   is <=, use # to represent it)
	                   We increment i to skip the next character, if the next character does in fact make it a two character long operator
	                 */
	                if (infixExp.charAt(i) == '>') { 
	                	i++;
	                	if (infixExp.charAt(i) == '=') { stack.push("#"); }
	                	else { 
	                		stack.push(">");
	                		i--;
	                	}
	            		
	            	}
	                else if (infixExp.charAt(i) == '<') { 
	                	i++;
	                	if (infixExp.charAt(i) == '=') { stack.push("$"); }
	                	else { 
	                		stack.push("<");
	                		i--;
	                	}
	            		
	            	}
	                else if (infixExp.charAt(i) == '=') { 
	                	stack.push("=");
	            		i++;
	            	}
	                else if (infixExp.charAt(i) == '!') { 
	                	stack.push("!");
	            		i++;
	            	}
	                else if (infixExp.charAt(i) == '&') { 
	                	stack.push("&");
	            		i++;
	            	}
	                else if (infixExp.charAt(i) == '|') { 
	                	stack.push("|");
	            		i++;
	            	}
	                else  { stack.push(Character.toString(infixExp.charAt(i))); }
	               
	            }
        	}     
//            System.out.println(postfixExp.toString() + " postfixExp");
        }//end for
        
        // Pop the remaining operators from the stack and append them to the post-fix expression string.
        while (!stack.isEmpty()) { postfixExp.append(stack.pop()).append(' '); }
        scanner.close();
        return postfixExp.toString();
	}//end infixToPostfix
	
	public static int precedence(String operator) {
		if (operator.equals("^")) { return 7; }
        if (operator.equals("*") || operator.equals("/") || operator.equals("%")) { return 6; }
        if (operator.equals("+") || operator.equals("-")) { return 5; }
        if (operator.equals(">") || operator.equals(">=") || operator.equals("<") || operator.equals("<=")) { return 4; }
        if (operator.equals("=") || operator.equals("!")) { return 3; }
        if (operator.equals("&")) { return 2; }
        if (operator.equals("|")) { return 1; }
        
        throw new IllegalArgumentException(String.format("Operator %s is not supported.", operator));
    }  // Time complexity: O(n)
	
	
}//end class