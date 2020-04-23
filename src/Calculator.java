/**
 * Dijkstra Two-Stack calculator GUI Swing implementation. 
 * 
 * @author Anne Landrum
 * @version 1.0
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Calculator extends JFrame {
     
    public static final int FRAME_WIDTH = 300;
    public static final int FRAME_HEIGHT = 320;
    public static final int FIELD_WIDTH = 20; 
    private JButton enter = new JButton("Enter");
    private JButton clear = new JButton("Clear");
    private JButton back = new JButton("<-");
    private JButton add = new JButton("+");
    private JButton sub = new JButton("-");
    private JButton mult = new JButton("*");
    private JButton div = new JButton("/");
    private JButton rBrace = new JButton(")");
    private JButton lBrace = new JButton("(");
    private JButton sqrt = new JButton("sqrt");
    private JButton period = new JButton(".");
    private JButton[] vals = new JButton[10];
    private JButton[] ops = new JButton[] {add, sub, mult, div, lBrace, rBrace, sqrt, period};
    private JTextField entry = new JTextField(FIELD_WIDTH);
    
    public Calculator(JFrame frame) {
        
        // Basic functions
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setTitle("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Textfield Entry
        JPanel num = new JPanel();
        num.add(entry);
    
        // Number key entry
        JPanel panel = new JPanel(new GridLayout(4,0));
        for (int i = 0; i < vals.length; i++) {
            String nums = i + "";
            vals[i] = new JButton(nums);
            panel.add(vals[i]);
            vals[i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    entry.setText(entry.getText() + nums);
                }  
            });
        }
        
        // Operators
        JPanel operators = new JPanel(new GridLayout(3,0));
        
        for (int i = 0; i < ops.length; i++) {
            String s = ops[i].getText();
            ops[i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    entry.setText(entry.getText() + s);
                }                  
            });
        }
        
        for (int i = 0; i < ops.length; i++) {
            operators.add(ops[i]);
        }
        
        // Calculate and Clear buttons
        JPanel func = new JPanel();

        clear.addActionListener(new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
            clear(e);
        }  
        });
        
        enter.addActionListener(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate(e);
            }  
        });
        
        back.addActionListener(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                back(e);
            }  
        });
        
        func.add(enter);
        func.add(clear);
        func.add(back);
        
        // Add JPanels
        JPanel main = new JPanel();
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));  
        main.add(num);
        main.add(operators);
        main.add(panel);
        main.add(func);
        frame.add(main);
        
    }

    public void calculate(ActionEvent e) {
        String in = entry.getText();
        if (in.isEmpty()) {
            entry.setText("Empty expression.");
        }
        else if (!checkEntry(in)) {
            entry.setText("Not a fully parenthesized expression.");
        }
        else  {
            Evaluate eval = new Evaluate();
            try {
                entry.setText(Double.toString(eval.twoStack(in)));
            } catch (Exception e1) {
                entry.setText("ERROR");
                e1.printStackTrace();
            }
        }
    }
    
    public void clear(ActionEvent e) {
        entry.setText("");
    }
    
    public void back(ActionEvent e) {
        if (entry.getText().length() > 0) {
            entry.setText(entry.getText().substring(0, entry.getText().length()-1));
        }
    }
    
    public boolean checkEntry(String check) {
        int count = 0;
        for (int i = 0; i < check.length(); i++) {
            if (check.charAt(i) == ')' ||
                check.charAt(i) == '(') {
                count++;
            }
        }
        
        if (count%2 != 0 || count == 0) {
            entry.setText("Enter valid expression.");
            return false;
        }
        return true;
    }
    
    
    public static void main(String[] args){
        JFrame frame = new JFrame();
        new Calculator(frame);
        frame.setVisible(true);
    }
}
