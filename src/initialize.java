import javax.swing.*;
import java.awt.Color;
import java.awt.EventQueue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class initialize
{
	public static void main(String[] args)
	{
		int gridSize = 6;
		playGame playGame = new playGame(gridSize);
		playGame.setVisible(true);
		
	}
}

class playGame extends JFrame implements ActionListener
{
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JButton btnSmall, btnMedium, btnLarge, btn[][];
	JLabel lblTitle;
	int size;
	
	public playGame(int size)
	{	
		//size = 6;
		setTitle("The Game of Chomp");
		setBounds(200, 150, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel1.setBackground(Color.ORANGE);
		panel1.setBounds(12, 12, 576, 93);
		getContentPane().add(panel1);
		panel1.setLayout(null);
		
		panel2.setBackground(Color.GRAY);
		panel2.setBounds(12, 120, 576, 243);
		getContentPane().add(panel2);
		panel2.setLayout(new GridLayout(size, size));
		
		btnSmall = new JButton("Small");
		btnSmall.setBounds(250, 32, 90, 35);
		btnSmall.addActionListener(this);
		panel1.add(btnSmall);
		
		btnMedium = new JButton("Medium");
		btnMedium.setBounds(350, 32, 90, 35);
		btnMedium.addActionListener(this);
		panel1.add(btnMedium);
		
		btnLarge = new JButton("Large");
		btnLarge.setBounds(450, 32, 90, 35);
		btnLarge.addActionListener(this);
		panel1.add(btnLarge);
		
		lblTitle = new JLabel("The Game of Chomp");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitle.setBounds(12, 32, 178, 35);
		panel1.add(lblTitle);
		
		JButton btn[][] = new JButton[size][size];
		
		
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
			{
				btn[r][c] = new JButton();
				btn[r][c].setBackground(Color.blue);
				btn[r][c].addActionListener(this);
				btn[r][c].setSize(40,40);
				panel2.add(btn[r][c]);
			}
		validate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnSmall))
		{
			setVisible(false);
			new playGame(3);
		}
		else if (e.getSource().equals(btnMedium))
		{
			setVisible(false);
			new playGame(6);
		}
		else if (e.getSource().equals(btnLarge))
		{
			setVisible(false);
			new playGame(9);
		}
		
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
			{
				if (e.getSource().equals(btn[r][c]))
				{
					btn[r][c].setBackground(Color.GREEN);
					setVisible(true);
				}
			}
	}
}
