import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game implements ActionListener {

	Random random = new Random();
	
	JFrame frame;
	JPanel title_panel;
	JPanel button_panel;
	JTextField textField;
	JButton[] buttons = new JButton[9];
	boolean player1_turn = true;
	
	public Game() {
		frame = new JFrame("Tic Tac Toe");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setBackground(Color.GRAY);
		frame.setLocationRelativeTo(null);
		
		textField = new JTextField();
		textField.setBackground(Color.BLACK);
		textField.setForeground(Color.BLUE);
		textField.setText("Tic-Tac-Toe");
		textField.setFont(new Font("Wild West", Font.BOLD, 50));
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setOpaque(true);
		textField.setEditable(false);
		
		title_panel = new JPanel();
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0, 500, 100);
		
		title_panel.add(textField);
		
		button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(3, 3));
		button_panel.setBackground(Color.LIGHT_GRAY);
		
		for(int i=0; i<9; i++) {
			buttons[i] = new  JButton();
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setFont(new Font("wild west", Font.BOLD, 70));
			button_panel.add(buttons[i]);
		}
		
		frame.add(button_panel);
		frame.add(title_panel, BorderLayout.NORTH);
		
		frame.setVisible(true);
		
		firstTurn();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0; i<9; i++) {
			if(e.getSource() == buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText() == "") {
						buttons[i].setForeground(Color.GREEN);
						buttons[i].setText("X");
						player1_turn = false;
						textField.setText("O turn");
						checkWin();
					}
				}
				else {
					buttons[i].setForeground(Color.BLUE);
					buttons[i].setText("O");
					player1_turn = true;
					textField.setText("X turn");
					checkWin();
				}
			}
		}
		
	}
	
	public void firstTurn() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(random.nextInt(2) == 0) {
			player1_turn = true;
			textField.setText("X turn");
		}
		else {
			player1_turn = false;
			textField.setText("O turn");
		}
	}
	
	public void checkWin() {
		
		// check win for X
		
		if( (buttons[0].getText() == "X") && 
				(buttons[1].getText() == "X") && 
				(buttons[2].getText() == "X") ) {
			xWins(0, 1, 2);
		}
		if( (buttons[3].getText() == "X") && 
				(buttons[4].getText() == "X") && 
				(buttons[5].getText() == "X") ) {
			xWins(3, 4, 5);
		}
		if( (buttons[6].getText() == "X") && 
				(buttons[7].getText() == "X") && 
				(buttons[8].getText() == "X") ) {
			xWins(6, 7, 8);
		}
		if( (buttons[0].getText() == "X") && 
				(buttons[3].getText() == "X") && 
				(buttons[6].getText() == "X") ) {
			xWins(0, 3, 6);
		}
		if( (buttons[1].getText() == "X") && 
				(buttons[4].getText() == "X") && 
				(buttons[7].getText() == "X") ) {
			xWins(1, 4, 7);
		}
		if( (buttons[2].getText() == "X") && 
				(buttons[5].getText() == "X") && 
				(buttons[8].getText() == "X") ) {
			xWins(2, 5, 8);
		}
		if( (buttons[0].getText() == "X") && 
				(buttons[4].getText() == "X") && 
				(buttons[8].getText() == "X") ) {
			xWins(0, 4, 8);
		}
		if( (buttons[2].getText() == "X") && 
				(buttons[4].getText() == "X") && 
				(buttons[6].getText() == "X") ) {
			xWins(2, 4, 6);
		}
		
		// Check win for O
		
		if( (buttons[0].getText() == "O") && 
				(buttons[1].getText() == "O") && 
				(buttons[2].getText() == "O") ) {
			oWins(0, 1, 2);
		}
		if( (buttons[3].getText() == "O") && 
				(buttons[4].getText() == "O") && 
				(buttons[5].getText() == "O") ) {
			oWins(3, 4, 5);
		}
		if( (buttons[6].getText() == "O") && 
				(buttons[7].getText() == "O") && 
				(buttons[8].getText() == "O") ) {
			oWins(6, 7, 8);
		}
		if( (buttons[0].getText() == "O") && 
				(buttons[3].getText() == "O") && 
				(buttons[6].getText() == "O") ) {
			oWins(0, 3, 6);
		}
		if( (buttons[1].getText() == "O") && 
				(buttons[4].getText() == "O") && 
				(buttons[7].getText() == "O") ) {
			oWins(1, 4, 7);
		}
		if( (buttons[2].getText() == "O") && 
				(buttons[5].getText() == "O") && 
				(buttons[8].getText() == "O") ) {
			oWins(2, 5, 8);
		}
		if( (buttons[0].getText() == "O") && 
				(buttons[4].getText() == "O") && 
				(buttons[8].getText() == "O") ) {
			oWins(0, 4, 8);
		}
		if( (buttons[2].getText() == "O") && 
				(buttons[4].getText() == "O") && 
				(buttons[6].getText() == "O") ) {
			oWins(2, 4, 6);
		}
		
	}
	
	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false);
		}
		textField.setText("X Wins!!");
	}
	
	public void oWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false);
		}
		textField.setText("O Wins!!");
	}

}
