import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextEditor extends JFrame implements ActionListener{
	
	JTextArea  textArea;
	
	JScrollPane scrollPane;
	
	JSpinner spinner;
	
	JComboBox<String> fontBox;
	
	JMenuBar menuBar;
	
	JMenuItem load_fileMenu;
	JMenuItem save_fileMenu;
	JMenuItem exit_fileMenu;
	
	JMenuItem fontColor_editMenu;
	
	JMenu fileMenu;
	JMenu editMenu;
	JMenu helpMenu;
	
	public TextEditor() {
		// Setup of the frame
		this.setTitle("Text Editor");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		
		// Setup of the menuBar
		menuBar = new JMenuBar();
		
		// Making menu to add in menu bar
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		helpMenu = new JMenu("Help");
		
		// Menu items
		load_fileMenu = new JMenuItem("Load");
		save_fileMenu = new JMenuItem("Save");
		exit_fileMenu = new JMenuItem("Exit");
		
		// Adding actionListener to the items
		load_fileMenu.addActionListener(this);
		save_fileMenu.addActionListener(this);
		exit_fileMenu.addActionListener(this);
		
		// Making shortcut for the menus
		fileMenu.setMnemonic(KeyEvent.VK_F);		// Alt + f is the shortcut for file menu
		editMenu.setMnemonic(KeyEvent.VK_E);		// Alt + e is the shortcut for edit menu
		helpMenu.setMnemonic(KeyEvent.VK_H);		// Alt + h is the shortcut for help menu
		
		// Making shortcut for the menu items
		load_fileMenu.setMnemonic(KeyEvent.VK_L);	// l is the shortcut for load item
		save_fileMenu.setMnemonic(KeyEvent.VK_S);	// s is the shortcut for load item
		exit_fileMenu.setMnemonic(KeyEvent.VK_E);	// e is the shortcut for load item
		
		// Adding menu items to the fileMenu
		fileMenu.add(load_fileMenu);
		fileMenu.add(save_fileMenu);
		fileMenu.add(exit_fileMenu);
		
		// Adding menu to the menuBar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		// Setup of textArea
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Wild West", Font.PLAIN, 20));
		
		// Making a scroll pane
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(580, 550));
		
		// Setup of the spinner
		spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(50, 25));
		spinner.setValue(20);
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int)spinner.getValue()));
				
			}
		});
		
		fontColor_editMenu = new JMenuItem("Font Color");
		fontColor_editMenu.addActionListener(this);
		
//		fontStyle_editMenu = new JMenuItem("Font Style");
//		fontStyle_editMenu.addActionListener(this);
		
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontBox = new JComboBox<String>(fonts);
		fontBox.setSelectedItem("Wild West");
		fontBox.addActionListener(this);
		
		editMenu.add(spinner);
		editMenu.add(fontColor_editMenu);
		editMenu.add(fontBox);
		
		this.setJMenuBar(menuBar);
		this.add(scrollPane);
//		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == load_fileMenu) {
			
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				
				Scanner fileIn = null;
				try {
					fileIn = new Scanner(file);
					if(file.isFile()) {
						String line = fileIn.nextLine() + "\n";
						textArea.append(line);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					fileIn.close();
				}
			}
		}
		if(e.getSource() == save_fileMenu) {
			
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showSaveDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file;
				PrintWriter fileOut = null;
				
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				try {
					fileOut = new PrintWriter(file);
					fileOut.println(textArea.getText());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					fileOut.close();
				}
			}
		}
		if(e.getSource() == exit_fileMenu) {
			System.exit(0);
		}
		
		if(e.getSource() == fontColor_editMenu) {
			
			JColorChooser colorChooser = new JColorChooser();
			
			Color color = colorChooser.showDialog(null, "Choose a color", Color.BLACK);
			
			textArea.setForeground(color);
		}
		if(e.getSource() == fontBox) {
			textArea.setFont(new Font((String)fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
			
		}
		
	}
}
