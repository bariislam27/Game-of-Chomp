import javax.swing.*;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Font;
import java.util.concurrent.TimeUnit;

public class Chomp 
{
    public static void main(String[] args) 
    {
        playGame playGame = new playGame(8);
    }
}

class playGame extends JFrame implements ActionListener 
{
    int size, player, choice, compRow, compCol; 
    int clickedRowCol1[] = {0, 0}; // Help to decide the computer which button not to select
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JButton btnSmall, btnMedium, btnLarge, btn[][];
    JLabel lblTitle, lblStatus, lblComp;
    Color myColor1 = new Color(234, 112, 30);
    Color myColor2 = new Color(52, 246, 77);
    Random rand = new Random();


    public playGame(int size) 
    {
        this.size = size;
        
        setTitle("Game of Chomp");
        //setBounds(100, 90, 750, 550);
	setSize(750, 550);
	setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        panel1.setBackground(myColor2);
        panel1.setBounds(12, 12, 726, 143);
        getContentPane().add(panel1);
        panel1.setLayout(null);

        panel2.setBackground(Color.BLACK);
        panel2.setBounds(12, 170, 726, 335);
        getContentPane().add(panel2);
        panel2.setLayout(new GridLayout(size, size));

        btnSmall = new JButton("Small");
        btnSmall.setBounds(400, 32, 90, 35);
        btnSmall.addActionListener(this);
        panel1.add(btnSmall);

        btnMedium = new JButton("Medium");
        btnMedium.setBounds(500, 32, 90, 35);
        btnMedium.addActionListener(this);
        panel1.add(btnMedium);

        btnLarge = new JButton("Large");
        btnLarge.setBounds(600, 32, 90, 35);
        btnLarge.addActionListener(this);
        panel1.add(btnLarge);

        lblTitle = new JLabel("The Game of Chomp");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 15));
        lblTitle.setBounds(12, 12, 178, 35);
        panel1.add(lblTitle);
        
        lblStatus = new JLabel();
        lblStatus.setFont(new Font("Dialog", Font.ITALIC, 14));
        lblStatus.setBounds(12, 42, 178, 35);
        panel1.add(lblStatus);
        
        lblComp = new JLabel();
        lblComp.setFont(new Font("Dialog", Font.ITALIC, 14));
        lblComp.setBounds(12, 72, 270, 35);
        panel1.add(lblComp);
        
        // Making of JButtons in a grid
        btn = new JButton[size][size];
        
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++) 
            {
            	if ((r != 0) || (c != 0))
				{
            		btn[r][c] = new JButton(r + ", " + c);
                    btn[r][c].setBackground(myColor1);
                    btn[r][c].addActionListener(this);
                    panel2.add(btn[r][c]);
				}
            	else 
            	{
            		btn[r][c] = new JButton();
                    btn[r][c].setBackground(Color.blue);
                    btn[r][c].setText("Soap");
                    btn[r][c].setEnabled(false);
                    panel2.add(btn[r][c]);
				}
            }
        clickedRowCol1[0] = size;
        clickedRowCol1[1] = size;
        player = 1; // User will play first
        lblStatus.setText("Your turn");
        
        validate();
        setResizable(false);
        setVisible(true);
    }
    
    public void computerAI()
    {
    	compRow = rand.nextInt(clickedRowCol1[0] + 1);
		compCol = rand.nextInt(clickedRowCol1[1] + 1);
		
		// Additional condition for first row and column
		// Else, the computer might choose an illegal move
		if (compRow == 0 && compCol != 0)
		{
			int temp = compRow;
			compRow = compCol;
			compCol = temp;
		}
		else if (compRow != 0 && compCol == 0)
		{
			{
				int temp = compRow;
				compRow = compCol;
				compCol = temp;
			}
		}
		
    	if (compRow == 0 && compCol == 0)
		{
			if (btn[0][1].isEnabled())
			{
				compRow = 0;
				compCol = 1;
			}
			else 
			{
				compRow = 1;
				compCol = 0;
			}
		}
    	
    	lblComp.setText("Computer's selection: " + compRow + ", " + compCol);
    }
    
    public void gameStatus()
    {   	
    	// Computers's turn
    	if (player % 2 == 0)
    	{
    		lblStatus.setText("Computer's turn");
    		
    		if (!(btn[0][1].isEnabled()) && !(btn[1][0].isEnabled()))
    		{
    			lblStatus.setText("You WON!");
    			choice = JOptionPane.showConfirmDialog(null, "You WON! \nDo "
    					+ "you want to play again?", "Result", JOptionPane.YES_NO_OPTION);
    			if (choice == JOptionPane.YES_OPTION)
    			{
    				player = 1;
    				setVisible(false);
    				new playGame(8);
    			}
    			else 
    			{
    				System.exit(0);
    			}
    		}
    		player++;
    	}
    	
    	// User's turn
    	if (player % 2 == 1)
    	{
    		lblStatus.setText("Your turn");

    		computerAI();
    		
    		for (int i = compRow; i < size; i++) 
                for (int j = compCol; j < size; j++) 
                {
                	btn[i][j].setBackground(Color.lightGray);
                    btn[i][j].setEnabled(false);
                }
    		
    		if (!(btn[0][1].isEnabled()) && !(btn[1][0].isEnabled()))
    		{
    			lblStatus.setText("Computer WON!");
    			choice = JOptionPane.showConfirmDialog(null, "Computer WON! \nDo "
    					+ "you want to play again?", "Result", JOptionPane.YES_NO_OPTION);
    			if (choice == JOptionPane.YES_OPTION)
    			{
    				setVisible(false);
    				new playGame(8);
    			}
    			else 
    			{
    				System.exit(0);
    			}
    		}
    		
    		player++;
    	}
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource().equals(btnSmall)) 
        {
            setVisible(false);
            new playGame(5);
        } 
        else if (e.getSource().equals(btnMedium)) 
        {
            setVisible(false);
            new playGame(8);
        } 
        else if (e.getSource().equals(btnLarge)) 
        {
            setVisible(false);
            new playGame(10);
        }

        for (int r = 0; r < size; r++) 
            for (int c = 0; c < size; c++) 
            {
          
                if (e.getSource().equals(btn[r][c])) 
                {
                	// Updating row and col number, which will be exempt to click
                	if (clickedRowCol1[0] > r)
					{
                		clickedRowCol1[0] = r;
					}
                	if (clickedRowCol1[1] > c)
					{
                		clickedRowCol1[1] = c;
					}
                	
                	
                	for (int i = r; i < size; i++) 
                        for (int j = c; j < size; j++) 
                        {
                        	btn[i][j].setBackground(Color.lightGray);
                            btn[i][j].setEnabled(false);
                        }
                }
                
            }
        gameStatus();
    }
}