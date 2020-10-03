package reznik;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class fileMain {

	private JFrame frame;
	public JButton fileBTN;
	public String pathway;
	public int totallinecount;
	public int countcomments;
	public int countblanklines;
	public int finallinecount;
	public int countfor;
	public int countif;
	public int countwhile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileMain window = new fileMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public fileMain() throws IOException {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		fileBTN = new JButton("Select File");
		fileBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fileBTN.setBounds(156, 38, 117, 29);
		frame.getContentPane().add(fileBTN);
	}

	public void createEvents() throws IOException {
		fileBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totallinecount = 0;
				countcomments = 0;
				countblanklines = 0;
				finallinecount = 0;
				countfor = 0;
				countif = 0;
				countwhile =0;
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("C:/Users/thomasreznik/desktop"));
				fc.setDialogTitle("File Directory");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fc.setFileFilter(filter);
				if (fc.showOpenDialog(fileBTN) == JFileChooser.APPROVE_OPTION) {
					pathway = fc.getSelectedFile().getAbsolutePath();
					System.out.println("You chose: " + fc.getSelectedFile().getAbsolutePath());
					File file = new File(pathway);
					try {
						FileReader fr = new FileReader(file); // Creation of File Reader object
						BufferedReader br = new BufferedReader(fr); // Creation of File Reader object
						String s ;
					
						while ((s = br.readLine()) != null) // Reading Content from the file line by line
						{
							s = s.replaceAll("\\s+", "");
							totallinecount++; // For each line increment linecount by one
							if(s.contains("for(") || s.contains("for (")) {
								countfor++;
							}
							if(s.contains("if(") || s.contains("if (")) {
								countif++;
							}
							if(s.contains("while(") || s.contains("while (")) {
								countwhile++;
							}
							if(s.equals("")) {
								countblanklines++;
							}
							
							if ((s.length() >= 2) && (s.startsWith("//"))) {
								countcomments++;
							} else if (s.contains("/*")) {
								countcomments++;
								while (((s = br.readLine()) != null) && !(s.endsWith("*/"))) {
									totallinecount++;
									countcomments++;
									
								}
								totallinecount++;
								countcomments++;
								
							}
								
							
						}
						fr.close();
						System.out.println("Number of total lines in the file: " + totallinecount); // Print the line count
						System.out.println("Number of lines with comments: " + countcomments); //Print number of lines of code with comments
						System.out.println("Number of blank lines: " + countblanklines); //Print number of lines of code with comments
						finallinecount = totallinecount - countcomments - countblanklines;
						System.out.println("Number of lines of code without comments and blank lines: " + finallinecount);
						System.out.println("Number of for loops: " + countfor);
						System.out.println("Number of if statements: " + countif);
						System.out.println("Number of while loops: " + countwhile);
					}

					catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else {
					System.out.println("No file chosen");
				}
			}

		});
	}
}
