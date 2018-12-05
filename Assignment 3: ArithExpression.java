/*
* Name: Noal Balint
* Date: Nov. 3 2018
* Filename: ArithExpression.java
* Details: CSC115 Assignment 3
*/

import java.util.regex.Pattern;
import java.util.regex.Matcher;



public class ArithExpression {

	public TokenList postfixTokens;
	public TokenList infixTokens;

	/**
	 * Sets up a legal standard Arithmetic expression.
	 * The only parentheses accepted are "(" and ")".
	 * @param word An arithmetic expression in standard infix order.
	 * 	An invalid expression is not expressly checked for, but will not be
	 * 	successfully evaluated, when the <b>evaluate</b> method is called.
	 * @throws InvalidExpressionException if the expression cannot be properly parsed,
	 *  	or converted to a postfix expression.
	 */

	

	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()",word)) { // check for legal standard Arithmetic expression
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	/*
	 * A private helper method that tokenizes a string by separating out
	 * any arithmetic operators or parens from the rest of the string.
	 * It does no error checking.
	 * The method makes use of Java Pattern matching and Regular expressions to
	 * isolate the operators and parentheses.
	 * The operands are assumed to be the substrings delimited by the operators and parentheses.
	 * The result is captured in the infixToken list, where each token is
	 * an operator, a paren or a operand.
	 * @param express The string that is assumed to be an arithmetic expression.
	 */
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());

		// regular expression that looks for any operators or parentheses.
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";

		// find all occurrences of a matched substring
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			// get the substring between matches
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim(); //removes outside whitespace
			// The very first '-' or a '-' that follows another operator is considered a negative sign
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 ||
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;  // ignore this match
				}
			}
			// nonMatchedBit can be empty when an operator follows a ')'
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		// parse the final substring after the last operator or paren:
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}

	/**
	 * Determines whether a single character string is an operator.
	 * The allowable operators are {+,-,*,/,^}.
	 * @param op The string in question.
	 * @return True if it is recognized as a an operator.
	 */
	public static boolean isOperator(String op) { // NOTE NOTE why is this static?
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
				return true;
			default:
				return false;
		}
	}




		//	A method to check if the token is numeric.NumberFormatException if not.
	 public static boolean isNumeric(String str)
	 {
			try
			{
		  	double d = Double.parseDouble(str);
			}
			catch(NumberFormatException nfe)
			{
		  	return false;
			}
	  	return true;
		}

		// A method to check the precedence of operators.
		public static int getPrecedence(String str)
		{
			if(str.equals("+") || str.equals("-"))
			{
				return 1;
			}
			else if(str.equals("*") || str.equals("/"))
			{
				return 2;
			}
			else if(str.equals("^"))
			{
				return 3;
			}
			else if(str.equals("(") || str.equals(")") )
			{
				return 4;
			}
			else
			{
				System.out.println("that is not a valid operator");
				return -1;
			}
		}


	public void infixToPostfix() {

	 postfixTokens = new TokenList();

	 StringStack stack = new StringStack();

		for(int i = 0; i < infixTokens.size(); i++)
	 // iterate through infifixTokens string

	 {
		 String token = infixTokens.get(i);


		 if(token.equals("("))
		 {
			 stack.push(token);
		 }

		 else if(token.equals(")"))
		 {
			 while(!stack.peek().equals("("))				//while top of stack is not "("
			 {
				 postfixTokens.append(stack.pop());
			 }
			 stack.pop(); // remove "(" from stack but to not append to postfix expression
		 }

		 else if(isOperator(token) != true) // in the case that is it an opperator.
		 																		// must check at this step to confirm that token is not ( or )
		 {
			 postfixTokens.append(token);
		 }


		 /*
		 			else if token is an operator
		  			while (stack is not empty and top of stack is not "("
		 	 					and token precedence <= precedence of stack top)
		 	 					pop operator off stack
		 	 					append operator to list
		  		push token onto stack
		  */
		 else if(isOperator(token) == true)
		 {

			 while( (stack.isEmpty() != true)
			 		&& (getPrecedence(stack.peek()) >= getPrecedence(token)) && !stack.peek().equals("(") )
			 {
				 postfixTokens.append(stack.pop());
			 }

			 stack.push(token);

			 if(stack.isEmpty() == true)
			 {
				stack.push(token);
			 }

		 }

	 }

	 // after entire string has been parsed, pop and append everything that's left in the stack
	 while(stack.isEmpty() != true)
	 {
		 postfixTokens.append(stack.pop());
	 } // testing concludes infixToPostfix works!


 }

	public String getInfixExpression() {

		return infixTokens.toString();
	}

	public String getPostfixExpression() {

	 return postfixTokens.toString();

	}

	public double evaluate() {

		StringStack stack = new StringStack();

		for(int i = 0; i < postfixTokens.size() ; i++)
		{
			String token = postfixTokens.get(i);

			// valid operand
			if((isOperator(token) != true) && (isNumeric(token) == true))
			{
				stack.push(token);
			}

			//non-numeric operand
			else if(isOperator(token) != true)
			{
				throw new InvalidExpressionException("non-numeric operand");
			}

			// for the "+ +" case. ie, if there is no operand for the operator to apply to.
			else if( (isOperator(token) == true) && (( stack.isEmpty() == true) || ( isOperator(stack.peek()) == true) ))
			{
				throw new InvalidExpressionException("cannot operate on that value");
			}

			// valid operator
			else if(isOperator(token) == true)
			{

				double a = 0;
				double b = 0;

				try
				{
					a = Double.parseDouble(stack.pop()); // parse the string into a double for math purposes
					b = Double.parseDouble(stack.pop());
				}
				catch(InvalidExpressionException e)
				{
					System.out.println("the token you tried to parse was not a number!");
				}

				double c = 0;

				try{

				if(token.equals("+"))
				{
					 c = a + b;
				}
				else if(token.equals("-"))
				{
					 c = b - a;
				}
				else if(token.equals("/"))
				{
					 c = b / a;
				}
				else if(token.equals("*"))
				{
					 c = a * b;
				}
				else if(token.equals("^"))
				{
					c = Math.pow(b, a); // ok to use this?
				}
				String result = Double.toString(c);
				stack.push(result);
				}
				catch(InvalidExpressionException e)
				{
					System.out.println("that was not a valid expression.");
				}
			}

		}
		return Double.parseDouble(stack.pop()); // convert string to double for return value
	}

	public static void main(String[] args) {

		// check if infixToPostfix method is working!
	 	ArithExpression str1 = new ArithExpression("(2 5) + 12");
	 	System.out.println(str1.postfixTokens);
		ArithExpression str2 = new ArithExpression("(2 + 50 / 5) * 2 ^2 + 20 / 4 + 2");
	 	System.out.println(str2.postfixTokens);
		ArithExpression str3 = new ArithExpression("2 + 5 - 2");
	 	System.out.println(str3.postfixTokens);
		ArithExpression str4 = new ArithExpression("(2 + 5) * 2^2");
	 	System.out.println(str4.postfixTokens);

	}

}
