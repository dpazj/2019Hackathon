package Main.GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Button;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Main.Driver;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duckoder {

	private JFrame frame;
	private JTextArea textField;
	private JTextField beforeDir;
	private JTextField afterDir;
	private JLabel beforeImg;
	private JLabel afterImg;
	private BufferedImage newImage;
        private Driver driver;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Duckoder window = new Duckoder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
                                
			}
		});
	}
	
	/**
	 * Sets new (after) image
	 * @param image new image
	 */
	public void setNewImage(BufferedImage image) {
		newImage = image;
		if (afterImg!=null) {
			frame.getContentPane().remove(afterImg);
		}
		afterImg = new JLabel(new ImageIcon(image.getScaledInstance(221, 358, Image.SCALE_DEFAULT)));
		afterImg.setBounds(244, 10, 211, 358);
		frame.getContentPane().add(afterImg);
		frame.getContentPane().repaint();
	}

	/**
	 * Create the application.
	 */
	public Duckoder() {
		initialize();
                driver = new Driver();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(112, 128, 144));
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Duckoder");
		
		textField = new JTextArea();
		textField.setLineWrap(true);
		textField.setBounds(478, 10, 211, 358);
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		beforeDir = new JTextField();
		beforeDir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("image","png");
				chooser.setFileFilter(filter);
				
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					String path = chooser.getSelectedFile().getAbsolutePath();
					File file = new File(path);
					if( file.exists())
					{
						beforeDir.setText(path);
					}
				}
			}
		});
		
		beforeDir.setBounds(10, 379, 346, 20);
		beforeDir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		frame.getContentPane().add(beforeDir);
		beforeDir.setColumns(10);
		
		afterDir = new JTextField();
		
		afterDir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				JFileChooser chooser = new JFileChooser();
				
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					String path = chooser.getSelectedFile().getAbsolutePath();
					File file = new File(path);
					if( file.exists())
					{
						afterDir.setText(path);
					}
				}
				
			}
		});
		
		afterDir.setColumns(10);
		afterDir.setBounds(10, 410, 346, 20);
		afterDir
		.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		frame.getContentPane().add(afterDir);
		
		JButton beforeBtn = new JButton("Add Encode file");
		beforeBtn.setForeground(new Color(0, 0, 0));
		beforeBtn.setBackground(new Color(211, 211, 211));
		beforeBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		beforeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String path = beforeDir.getText(); 
                                driver.encodePath = path;
				BufferedImage beforeImage;
				try {
					beforeImage = ImageIO.read(new File(path));
					if (beforeImg!=null) {
						frame.getContentPane().remove(beforeImg);
					}
					beforeImg = new JLabel(new ImageIcon(beforeImage.getScaledInstance(221, 358, Image.SCALE_DEFAULT)));
					beforeImg.setBounds(10, 10, 211, 358);
					frame.getContentPane().add(beforeImg);
					frame.getContentPane().repaint();
				} catch (IOException e1) {
					// TODO print error
					System.out.println(e1.toString());
				}
			}
		});
		
		beforeBtn.setBounds(366, 379, 89, 20);
		frame.getContentPane().add(beforeBtn);
		
		JButton afterBtn = new JButton("Add secret file");
		afterBtn.setBackground(new Color(211, 211, 211));
		afterBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		afterBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (newImage!=null) {
					String path = afterDir.getText();
                                        driver.secrectFilePath = path;
				}
			}
		});
		
		afterBtn.setBounds(366, 409, 89, 20);
		frame.getContentPane().add(afterBtn);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.setBackground(new Color(220, 220, 220));
		submitBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO: REMOVE TEST IMAGE
				try {
					BufferedImage test = driver.encode());
					setNewImage(test);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		submitBtn.setBounds(478, 379, 211, 50);
		frame.getContentPane().add(submitBtn);
	}
}
