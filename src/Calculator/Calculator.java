package Calculator;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Calculator extends Frame implements ActionListener 
{
	
	
	private static PreparedStatement pst = null;
	//Declare label , textfield and Buttons
	Label lblHead = new Label("Calculator",Label.CENTER);
	Label lblMsg = new Label("Enter Any Two Numbers",Label.CENTER);
	TextField txtnum1 = new TextField("0");
	TextField txtnum2 = new TextField("0");
	Button btnAdd = new Button("+");
	Button btnSub = new Button("-");
	Button btnMul = new Button("*");
	Button btnDiv = new Button("/");
	Label lblRes = new Label("",Label.CENTER);
	
	static Connection con = null;
	
	Calculator()
	{
		super("Calculator_Page");
		
		setVisible(true);
		setSize(500,500);
		setLocation(200,200);
		//Setting Font
		Font F = new Font("Times New Roman",Font.BOLD,15);
		setFont(F);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator","root"," ");
			 pst = con.prepareStatement("insert into calculator(operand1,operation,operand2,result)values(?,?,?,?)");
		} 
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(SQLException e)
		{
			
		}
		
		//Setting Layout Null
		setLayout(null);
		
		//Adding Components into Frame
		add(lblHead);
		add(lblMsg);
		add(txtnum1);
		add(txtnum2);
		add(btnAdd);
		add(btnSub);
		add(btnMul);
		add(btnDiv);
		add(lblRes);
		
		//Setting Bounds of Components
		lblHead.setBounds(0,20,500,60);
		lblMsg.setBounds(100,100,300,30);
		txtnum1.setBounds(130,170,80,30);
		txtnum2.setBounds(270,170,80,30);
		btnAdd.setBounds(140,240,40,30);
		btnSub.setBounds(190,240,40,30);
		btnMul.setBounds(240,240,40,30);
		btnDiv.setBounds(290,240,40,30);
		lblRes.setBounds(0,450,500,50);
		
		//Setting Background and Font
		lblHead.setBackground(Color.BLACK);
		lblHead.setForeground(Color.ORANGE);
		lblRes.setBackground(Color.BLACK);
		lblRes.setForeground(Color.ORANGE);
		lblMsg.setBackground(Color.BLACK);
		lblMsg.setForeground(Color.ORANGE);
		btnAdd.setBackground(Color.BLACK);
		btnAdd.setForeground(Color.ORANGE);
		btnSub.setBackground(Color.BLACK);
		btnSub.setForeground(Color.ORANGE);
		btnMul.setBackground(Color.BLACK);
		btnMul.setForeground(Color.ORANGE);
		btnDiv.setBackground(Color.BLACK);
		btnDiv.setForeground(Color.ORANGE);
		txtnum1.setBackground(Color.BLACK);
		txtnum1.setForeground(Color.ORANGE);
		txtnum2.setBackground(Color.BLACK);
		txtnum2.setForeground(Color.ORANGE);
		
		setBackground(Color.ORANGE);
		
		//WindowListener to close Frame
		WindowListener win = new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
				try {
					con.close();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		};
		
		addWindowListener(win);
		
		addWindowListener(win);
		
		btnAdd.addActionListener(this);
		btnSub.addActionListener(this);
		btnMul.addActionListener(this);
		btnDiv.addActionListener(this);
		
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String str = e.getActionCommand();
		double num1=Double.parseDouble(txtnum1.getText());
		double num2=Double.parseDouble(txtnum2.getText());
		double result;
		
		try
		{
			
			if(str=="+")
			{
				lblRes.setText(Double.toString(num1+num2));
				result=num1+num2;
				pst.setDouble(1, num1);
				pst.setString(2, "+");
				pst.setDouble(3, num2);
				pst.setDouble(4, result);
				pst.executeUpdate();
			}	
			else if(str=="-")
			{
				lblRes.setText(Double.toString(num1-num2));
				result=num1+num2;
				pst.setDouble(1, num1);
				pst.setString(2, "-");
				pst.setDouble(3, num2);
				pst.setDouble(4, result);
				pst.executeUpdate();
			}
			else if(str=="*")
			{
				lblRes.setText(Double.toString(num1*num2));
				result=num1+num2;
				pst.setDouble(1, num1);
				pst.setString(2, "*");
				pst.setDouble(3, num2);
				pst.setDouble(4, result);
				pst.executeUpdate();
			}
			else if(str=="/")
			{
				lblRes.setText(Double.toString(num1/num2));
				result=num1+num2;
				pst.setDouble(1, num1);
				pst.setString(2, "/");
				pst.setDouble(3, num2);
				pst.setDouble(4, result);
				pst.executeUpdate();
			}	
			else
			{
				lblRes.setText("Error!!!");
			}
			
		}
		catch(ArithmeticException ar)
		{
			lblRes.setText("Error!!! Cannot Divide By 0");
		}
		catch(NumberFormatException num)
		{
			lblRes.setText("Error!!! Enter The Two Integer Number");
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
		
	}

	public static void main(String args[])
	{
		Calculator cal = new Calculator();
		
		
	}
}