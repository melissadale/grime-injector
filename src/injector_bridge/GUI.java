package injector_bridge;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JCheckBox;

import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;

import javassist.*;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;


public class GUI extends JFrame {
	
	/*===Sonar Stuff ===*/
	String sserver_path = "C:\\sonarqube-6.1\\bin\\windows-x86-64\\StartSonar.bat";
	String sscanner_path = "C:\\sonar-scanner-2.8\bin\\sonar-scanner.bat";	
	/*===Sonar Stuff ===*/
	
	/*===Injector Stuff ===*/
	private WarpDrive engage = new WarpDrive();
	private ArrayList<String> patterns = new ArrayList<String>();
	private ArrayList<String> not_patterns = new ArrayList<String>();
	/*===Injector Stuff ===*/
	
	private static final long serialVersionUID = 1L;
	protected static final Color BLACK = null;
	
	private JPanel contentPane;
	private JTextField npclass;
	private JTextField pclass;
	private JTextField txtPeag;
	private JTextField txtPeeg;
	private JTextField txtPig;
	private JTextField txtTeag;
	private JTextField txtTeeg;
	private JTextField txtTig;
	private JLabel lblOr;
	private JTextField txtSameOfEach;
	private JLabel lblRuns;
	private JLabel lblVersions;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton button_1;
	private JLabel lblPatternClasses;
	private JLabel lblNotPatternClasses;
	private JCheckBox chckbxNewCheckBox;
	
	
	
	ClassPool pool = ClassPool.getDefault();
	private JButton btnGetProjects;
	private JButton btnGetProjects_1;
	private JPanel panel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 972, 461);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(192, 192, 192));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblPatternClasses = new JLabel("Pattern Classes");
		lblPatternClasses.setForeground(Color.BLACK);
		lblPatternClasses.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPatternClasses = new GridBagConstraints();
		gbc_lblPatternClasses.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatternClasses.gridx = 6;
		gbc_lblPatternClasses.gridy = 0;
		contentPane.add(lblPatternClasses, gbc_lblPatternClasses);
		
		lblNotPatternClasses = new JLabel("Not Pattern Classes");
		lblNotPatternClasses.setForeground(Color.BLACK);
		lblNotPatternClasses.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNotPatternClasses = new GridBagConstraints();
		gbc_lblNotPatternClasses.gridwidth = 4;
		gbc_lblNotPatternClasses.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotPatternClasses.gridx = 7; //TODO: Adjust
		gbc_lblNotPatternClasses.gridy = 0;
		contentPane.add(lblNotPatternClasses, gbc_lblNotPatternClasses);
		
		pclass = new JTextField();
		GridBagConstraints gbc_pclass = new GridBagConstraints();
		gbc_pclass.insets = new Insets(0, 0, 5, 5);
		gbc_pclass.fill = GridBagConstraints.HORIZONTAL;
		gbc_pclass.gridx = 1;
		gbc_pclass.gridy = 1;
		contentPane.add(pclass, gbc_pclass);
		pclass.setColumns(10);
		
		npclass = new JTextField();
		GridBagConstraints gbc_npclass = new GridBagConstraints();
		gbc_npclass.insets = new Insets(0, 0, 5, 5);
		gbc_npclass.fill = GridBagConstraints.HORIZONTAL;
		gbc_npclass.gridx = 3;
		gbc_npclass.gridy = 1;
		contentPane.add(npclass, gbc_npclass);
		npclass.setColumns(10);
		
		/*==============================================BUTTONS=====================================================*/
		
		JButton btnAddPatternClass = new JButton("Add Pattern Class"); 
		btnAddPatternClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				patterns.add(pclass.getText());
				populate_patterns();	
		   		pclass.setText("");
			}
		});
		
		pclass.addKeyListener(
				new KeyListener()
				{
				public void keyReleased(KeyEvent e )
				{
				if( e.getKeyCode() == KeyEvent.VK_ENTER )
				{
					patterns.add(pclass.getText());
					populate_patterns();	
			   		pclass.setText("");
				}
				}

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				}
				);

		btnAddPatternClass.setForeground(new Color(0, 102, 0));
		btnAddPatternClass.setBackground(new Color(204, 255, 204));
		GridBagConstraints gbc_btnAddPatternClass = new GridBagConstraints();
		gbc_btnAddPatternClass.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddPatternClass.gridx = 1;
		gbc_btnAddPatternClass.gridy = 2;
		contentPane.add(btnAddPatternClass, gbc_btnAddPatternClass);
		
		JButton button = new JButton("Add Not Pattern");
		button.setForeground(new Color(0, 102, 0));
		button.setBackground(new Color(204, 255, 204));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				not_patterns.add(npclass.getText());
				populate_not_patterns();	
		   		npclass.setText("");
			}
		});
		
		npclass.addKeyListener(
				new KeyListener()
				{
				public void keyReleased(KeyEvent e )
				{
				if( e.getKeyCode() == KeyEvent.VK_ENTER )
				{
					not_patterns.add(npclass.getText());
					populate_not_patterns();	
			   		npclass.setText("");
				}
				}

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				}
				);
		
		button_1 = new JButton("Choose Project");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fChoose = new JFileChooser( 
						System.getProperty( "user.dir" ) );
					int returnVal = fChoose.showOpenDialog(getContentPane());

					if ( returnVal == JFileChooser.APPROVE_OPTION ) {
						System.out.println( "The file selected was: " + 
							fChoose.getSelectedFile().getName() );
					}	
			}
		});
		
		
		btnGetProjects = new JButton("Choose Project");
		btnGetProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fChoose = new JFileChooser( 
						System.getProperty( "user.dir" ) );
					int returnVal = fChoose.showOpenDialog(getContentPane());

					if ( returnVal == JFileChooser.APPROVE_OPTION ) {
						System.out.println( "The file selected was: " + 
							fChoose.getSelectedFile().getName() );
					}	
			}
		});
		
		
		
		/*==============================================/BUTTONS=====================================================*/


		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 2;
		contentPane.add(button, gbc_button);
		
		txtPeag = new JTextField();
		txtPeag.setForeground(new Color(192, 192, 192));
		txtPeag.setHorizontalAlignment(SwingConstants.CENTER);
		txtPeag.setText("PEAG");
		GridBagConstraints gbc_txtPeag = new GridBagConstraints();
		gbc_txtPeag.insets = new Insets(0, 0, 5, 5);
		gbc_txtPeag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPeag.gridx = 1;
		gbc_txtPeag.gridy = 4;
		contentPane.add(txtPeag, gbc_txtPeag);
		txtPeag.setColumns(10);
		
		txtTeag = new JTextField();
		txtTeag.setForeground(new Color(192, 192, 192));
		txtTeag.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeag.setText("TEAG");
		txtTeag.setColumns(10);
		GridBagConstraints gbc_txtTeag = new GridBagConstraints();
		gbc_txtTeag.insets = new Insets(0, 0, 5, 5);
		gbc_txtTeag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTeag.gridx = 3;
		gbc_txtTeag.gridy = 4;
		contentPane.add(txtTeag, gbc_txtTeag);
		
		txtPeeg = new JTextField();
		txtPeeg.setForeground(new Color(192, 192, 192));
		txtPeeg.setHorizontalAlignment(SwingConstants.CENTER);
		txtPeeg.setText("PEEG");
		txtPeeg.setColumns(10);
		GridBagConstraints gbc_txtPeeg = new GridBagConstraints();
		gbc_txtPeeg.insets = new Insets(0, 0, 5, 5);
		gbc_txtPeeg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPeeg.gridx = 1;
		gbc_txtPeeg.gridy = 5;
		contentPane.add(txtPeeg, gbc_txtPeeg);
		
		txtTeeg = new JTextField();
		txtTeeg.setForeground(new Color(192, 192, 192));
		txtTeeg.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeeg.setText("TEEG");
		txtTeeg.setColumns(10);
		GridBagConstraints gbc_txtTeeg = new GridBagConstraints();
		gbc_txtTeeg.insets = new Insets(0, 0, 5, 5);
		gbc_txtTeeg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTeeg.gridx = 3;
		gbc_txtTeeg.gridy = 5;
		contentPane.add(txtTeeg, gbc_txtTeeg);
		
		txtPig = new JTextField();
		txtPig.setForeground(new Color(192, 192, 192));
		txtPig.setHorizontalAlignment(SwingConstants.CENTER);
		txtPig.setText("PIG");
		txtPig.setColumns(10);
		GridBagConstraints gbc_txtPig = new GridBagConstraints();
		gbc_txtPig.insets = new Insets(0, 0, 5, 5);
		gbc_txtPig.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPig.gridx = 1;
		gbc_txtPig.gridy = 6;
		contentPane.add(txtPig, gbc_txtPig);
		
		txtTig = new JTextField();
		txtTig.setForeground(new Color(192, 192, 192));
		txtTig.setHorizontalAlignment(SwingConstants.CENTER);
		txtTig.setText("TIG");
		txtTig.setColumns(10);
		GridBagConstraints gbc_txtTig = new GridBagConstraints();
		gbc_txtTig.insets = new Insets(0, 0, 5, 5);
		gbc_txtTig.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTig.gridx = 3;
		gbc_txtTig.gridy = 6;
		contentPane.add(txtTig, gbc_txtTig);
		
		txtSameOfEach = new JTextField();
		txtSameOfEach.setForeground(new Color(192, 192, 192));
		txtSameOfEach.setHorizontalAlignment(SwingConstants.CENTER);
		txtSameOfEach.setText("SAME OF EACH");
		GridBagConstraints gbc_txtSameOfEach = new GridBagConstraints();
		gbc_txtSameOfEach.insets = new Insets(0, 0, 5, 5);
		gbc_txtSameOfEach.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSameOfEach.gridx = 1;
		gbc_txtSameOfEach.gridy = 8;
		contentPane.add(txtSameOfEach, gbc_txtSameOfEach);
		txtSameOfEach.setColumns(10);
		
		
		/*==============================================TextField Actions=====================================================*/		
		
			final Color night = new Color(0,0,0);
		
			txtTig.addMouseListener(new MouseListener(){
			  public void mouseClicked(MouseEvent e)
	            {
	                txtTig.setText("");
	                txtTig.setForeground(night);
	            }

			@Override
			public void mouseEntered(MouseEvent arg0) {				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {				
			}
	        });
			
			txtTeag.addMouseListener(new MouseListener(){
				  public void mouseClicked(MouseEvent e)
		            {
		                
		                txtTeag.setText("");
		                txtTeag.setForeground(night);
		                
		            }

				@Override
				public void mouseEntered(MouseEvent arg0) {				
				}

				@Override
				public void mouseExited(MouseEvent arg0) {				
				}

				@Override
				public void mousePressed(MouseEvent arg0) {				
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {				
				}
		        });
		
			
			txtTeeg.addMouseListener(new MouseListener(){
				  public void mouseClicked(MouseEvent e)
		            {
		                
		                txtTeeg.setText("");
		                txtTeeg.setForeground(night);
		            }

				@Override
				public void mouseEntered(MouseEvent arg0) {				
				}

				@Override
				public void mouseExited(MouseEvent arg0) {				
				}

				@Override
				public void mousePressed(MouseEvent arg0) {				
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {				
				}
		        });

			txtPig.addMouseListener(new MouseListener(){
				  public void mouseClicked(MouseEvent e)
		            {
		                
		                txtPig.setText("");
		                txtPig.setForeground(night);
		            }

				@Override
				public void mouseEntered(MouseEvent arg0) {				
				}

				@Override
				public void mouseExited(MouseEvent arg0) {				
				}

				@Override
				public void mousePressed(MouseEvent arg0) {				
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {				
				}
		        });
				
				txtPeag.addMouseListener(new MouseListener(){
					  public void mouseClicked(MouseEvent e)
			            {
			                
			                txtPeag.setText("");
			                txtPeag.setForeground(night);
			            }

					@Override
					public void mouseEntered(MouseEvent arg0) {				
					}

					@Override
					public void mouseExited(MouseEvent arg0) {				
					}

					@Override
					public void mousePressed(MouseEvent arg0) {				
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {				
					}
			        });
			
				
				txtPeeg.addMouseListener(new MouseListener(){
					  public void mouseClicked(MouseEvent e)
			            {
			                
			                txtPeeg.setText("");
			                txtPeeg.setForeground(night);
			            }

					@Override
					public void mouseEntered(MouseEvent arg0) {				
					}

					@Override
					public void mouseExited(MouseEvent arg0) {				
					}

					@Override
					public void mousePressed(MouseEvent arg0) {				
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {				
					}
			        });

				txtSameOfEach.addMouseListener(new MouseListener(){
					  public void mouseClicked(MouseEvent e)
			            {
			                
						  txtSameOfEach.setText("");
						  txtSameOfEach.setForeground(night);
			            }

					@Override
					public void mouseEntered(MouseEvent arg0) {				
					}

					@Override
					public void mouseExited(MouseEvent arg0) {				
					}

					@Override
					public void mousePressed(MouseEvent arg0) {				
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {				
					}
			        });

				
			
		/*==============================================/TextFields Actions=====================================================*/


		
		
		lblOr = new JLabel("OR");
		lblOr.setForeground(new Color(0, 102, 0));
		lblOr.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblOr = new GridBagConstraints();
		gbc_lblOr.insets = new Insets(0, 0, 5, 5);
		gbc_lblOr.gridx = 2;
		gbc_lblOr.gridy = 7;
		contentPane.add(lblOr, gbc_lblOr);
		

		
		lblRuns = new JLabel("RUNS");
		lblRuns.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRuns.setForeground(new Color(0, 102, 0));
		lblRuns.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblRuns = new GridBagConstraints();
		gbc_lblRuns.anchor = GridBagConstraints.EAST;
		gbc_lblRuns.insets = new Insets(0, 0, 5, 5);
		gbc_lblRuns.gridx = 1;
		gbc_lblRuns.gridy = 10;
		contentPane.add(lblRuns, gbc_lblRuns);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 10;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		lblVersions = new JLabel("VERSIONS");
		lblVersions.setForeground(new Color(0, 102, 0));
		lblVersions.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblVersions = new GridBagConstraints();
		gbc_lblVersions.anchor = GridBagConstraints.EAST;
		gbc_lblVersions.insets = new Insets(0, 0, 5, 5);
		gbc_lblVersions.gridx = 1;
		gbc_lblVersions.gridy = 11;
		contentPane.add(lblVersions, gbc_lblVersions);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 11;
		contentPane.add(textField_3, gbc_textField_3);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "SONARQUBE", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GREEN));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.gridwidth = 9;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 6;
		gbc_panel.gridy = 12;
		contentPane.add(panel, gbc_panel);

		
		chckbxNewCheckBox = new JCheckBox("Inject differnt grime types on top of each other");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.gridwidth = 3;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 13;
		contentPane.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		button_1.setForeground(new Color(0, 102, 0));
		button_1.setBackground(new Color(204, 255, 204));
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.insets = new Insets(0, 0, 0, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 15;
		contentPane.add(button_1, gbc_button_1);
		/*==================================================magic happens here====================================*/
		btnGetProjects_1 = new JButton("Inject");
		btnGetProjects_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				 int peag = setVals(txtPeag, 0);
				 int peeg = setVals(txtPeeg, 0);
				 int pig = setVals(txtPig, 0);
				 int teag = setVals(txtTeag, 0);
				 int teeg = setVals(txtTeeg, 0);
				 int tig = setVals(txtTig, 0);				
				 int all = setVals(txtSameOfEach, 0);
								
				 int repeats = setVals(textField_2, 1);
				 int versions = setVals(textField_3, 1);
								 
				try {
					if (all == 0){
						engage.inject(versions, repeats, peag, peeg, pig, teag, teeg, tig, patterns, not_patterns);
					}
					else{
						engage.inject(versions, repeats, all, patterns, not_patterns);
					}
					String path = System.getProperty("user.dir")+ "\\Lib\\jad_decompile.bat";
					Runtime.getRuntime().exec("cmd /c start " + path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				write_config_csv();
				
				System.out.println("DONE!");
				
			}
		});
		btnGetProjects_1.setBackground(new Color(204, 255, 204));
		btnGetProjects_1.setForeground(new Color(0, 102, 0));
		GridBagConstraints gbc_btnGetProjects = new GridBagConstraints();
		gbc_btnGetProjects.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGetProjects.insets = new Insets(0, 0, 0, 5);
		gbc_btnGetProjects.gridx = 3;
		gbc_btnGetProjects.gridy = 15;
		contentPane.add(btnGetProjects_1, gbc_btnGetProjects);
	}

	
	/*==============================================button methods========================================*/

	private void populate_patterns(){
		int size = patterns.size();
		JLabel labels[]=new JLabel[size]; 
		
		for(int i=0; i < size; i++){
			Color passed = new Color(0, 102, 0);
			Color failed = new Color(178,34,34);
			
			labels[i]=new JLabel(patterns.get(i));
			labels[i].setForeground(passed);
			labels[i].setVisible(true);
			
	   		GridBagConstraints gbc_chckbxTestpurposes = new GridBagConstraints();
	   		gbc_chckbxTestpurposes.gridx = 6;
	   		gbc_chckbxTestpurposes.gridy = 1+i;
	   		
			String name = "analyze_this."+pclass.getText();

			try{
				CtClass cc = pool.get(name);
				cc.detach();
			} catch (NotFoundException  e){
				remove_p_name(name);
				labels[i].setForeground(failed);
			} finally{
		   		contentPane.add(labels[i], gbc_chckbxTestpurposes);
		   		contentPane.revalidate(); 
			}
		}
	}
	
	
	private void populate_not_patterns(){
		int size = not_patterns.size();
		JLabel labels[]=new JLabel[size]; 
		
		for(int i=0; i < size; i++){
			labels[i]=new JLabel(not_patterns.get(i));
			labels[i].setVisible(true);
			
	   		GridBagConstraints gbc_chckbxTestpurposes = new GridBagConstraints();
	   		gbc_chckbxTestpurposes.gridx = 7;
	   		gbc_chckbxTestpurposes.gridy = 1+i;
	   		
			String name = "analyze_this."+npclass.getText();
			
			Color passed = new Color(0, 102, 0);
			Color failed = new Color(178,34,34);

	   		
			try{
				CtClass cc = pool.get(name);
				labels[i].setForeground(passed);
				cc.detach();
			} catch (Exception e){
				remove_np_name(name);
				labels[i].setForeground(failed);
			} finally{
		   		contentPane.add(labels[i], gbc_chckbxTestpurposes);
		   		contentPane.revalidate(); 
			}
		}
	}
	
	/*==============================================Helper========================================*/
	private void remove_p_name(String given){
		for (int i = 0; i<patterns.size(); i++){
			if (patterns.get(i)== given){
				patterns.remove(i);
			}
		}
	}
	
	private void remove_np_name(String given){
		for (int i = 0; i<not_patterns.size(); i++){
			if (not_patterns.get(i)== given){
				not_patterns.remove(i);
			}
		}
	}
	
	private int setVals(JTextField given, int default_val){
		int default_me;
		try{
			default_me = Integer.parseInt(given.getText());
		}catch (Exception e){
			default_me = default_val;
		}
		
		return default_me;
	}
	
	private void write_config_csv(){
		try
		{
		    FileWriter writer = new FileWriter("EnteredSettings.csv");
	 
		    writer.append("****Grime Type****");
		    writer.append(',');
		    writer.append("Counts");
		    writer.append('\n');
	 
		    writer.append("PEAG");
		    writer.append(',');
		    writer.append("PEEG");
		    writer.append(',');
		    writer.append("PIG");
		    writer.append(',');
		    writer.append("TEAG");
		    writer.append(',');
		    writer.append("TEEG");
		    writer.append(',');
		    writer.append("TIG");		    
	        writer.append('\n');
	 
	   	 	if (setVals(txtSameOfEach, 0) != 0){
	   	 		writer.append(Integer.toString(setVals(txtSameOfEach, 0)));
		        writer.append(',');
	   	 		writer.append(Integer.toString(setVals(txtSameOfEach, 0)));
		        writer.append(',');	   	 		
		        writer.append(Integer.toString(setVals(txtSameOfEach, 0)));
		        writer.append(',');	   	 		
		        writer.append(Integer.toString(setVals(txtSameOfEach, 0)));
		        writer.append(',');
	   	 		writer.append(Integer.toString(setVals(txtSameOfEach, 0)));
		        writer.append(',');	   	 		
		        writer.append(Integer.toString(setVals(txtSameOfEach, 0)));
		        writer.append('\n');	   	 	
	   	 	}
	   	 	else{
			    writer.append(Integer.toString(setVals(txtPeag, 0)));
			    writer.append(',');
			    writer.append(Integer.toString(setVals(txtPeeg, 0)));
			    writer.append(',');
			    writer.append(Integer.toString(setVals(txtPig, 0)));
			    writer.append(',');
			    writer.append(Integer.toString(setVals(txtTeag, 0)));
			    writer.append(',');
			    writer.append(Integer.toString(setVals(txtTeeg, 0)));
			    writer.append(',');
			    writer.append(Integer.toString(setVals(txtTig, 0)));		    
		        writer.append('\n');
	   	 	}
		    writer.append("****Runs and Versions****");
		    writer.append(',');
		    writer.append("Counts");
		    writer.append('\n');

		    writer.append("Runs");
		    writer.append(',');
		    writer.append("Versions");		    
	        writer.append('\n');
	        
		    writer.append(Integer.toString(setVals(textField_2, 1)));
		    writer.append(',');
		    writer.append(Integer.toString(setVals(textField_3, 1)));
		    writer.append('\n');
		    
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	}
}
