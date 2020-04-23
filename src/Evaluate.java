/**
 * Modified Dijkstra's fully parenthesized two-stack algorithm
 * from Algorithms, Fourth Ed. by Robert Sedgewick and Kevin Wayne at
 * https://algs4.cs.princeton.edu/13stacks/Evaluate.java.html.
 * Accepts strings instead of a file (StdIn) and accepts multi-digit integers
 * and doubles.
 */
import java.util.Scanner;
import java.util.Stack;

public class Evaluate {
    
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        System.out.println("Is this your value => " + s + "?");
        String ans = scan.nextLine();
        if (ans.equalsIgnoreCase("Y")) {
            Evaluate eval = new Evaluate();
            System.out.println(eval.twoStack(s));
            scan.close();
            System.exit(0);
        }
        else 
            scan.close();
            System.exit(0);
    }

    public double twoStack(String in) throws Exception { 
        Stack<String> ops  = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        String num = "";
        
        for (int i = 0; i < in.length(); i++) {
            char s = in.charAt(i);
            System.out.println(s);
            if      (s == '(');
            else if (s == 'q');
            else if (s == 'r');
            else if (s == 't');
            else if (s == '+')    ops.push(Character.toString(s));
            else if (s == '-')    ops.push(Character.toString(s));
            else if (s == '*')    ops.push(Character.toString(s));
            else if (s == '/')    ops.push(Character.toString(s));
            else if (s == 's')    ops.push(Character.toString(s));
            else if (s == ')') {
                
                String op = ops.pop();
                double v = vals.pop();
                if      (op.equals("+"))    v = vals.pop() + v;
                else if (op.equals("-"))    v = vals.pop() - v;
                else if (op.equals("*"))    v = vals.pop() * v;
                else if (op.equals("/"))    v = vals.pop() / v;
                else if (op.equals("s")) v = Math.sqrt(v);
                vals.push(v);
            }
            else {
               if (Character.isDigit(in.charAt(i+1)) || in.charAt(i+1) == '.' || in.charAt(i) == '.') {
                  num += in.charAt(i);
               }
               else {
                   num += in.charAt(i);
                   vals.push(Double.parseDouble(num));
                   System.out.println("Num:" + num);
                   num = "";
               }
            }
        }
        
        return vals.pop();
    }
}