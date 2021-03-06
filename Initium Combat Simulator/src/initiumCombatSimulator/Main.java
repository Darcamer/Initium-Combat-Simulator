package initiumCombatSimulator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 * public class Main - this is the main class for the Initium combat simulator. It pulls all of the other classes together into a working program.
 * @author Evanosity
 * @date June 18 2017
 */
public class Main {
	private static JFrame GUI;
	private static JButton runSim;
	private static JTextField attackerFile;
	private static JTextField defenderFile;
	private static JTextField numberOfRuns;
	private static JTextArea results;
	private static JScrollPane upgradeYo;
	
	private static JButton editAttacker;
	private static JButton editDefender;
	private static JButton newEntity;
	
	@SuppressWarnings("unused")

	public static void main(String[]args){
		//set up the GUI.
		GUI=new JFrame("Initium Combat Simulator");
		GUI.getContentPane();
		GUI.setLayout(null);
		GUI.setDefaultCloseOperation(3);
		GUI.setResizable(false);
		GUI.setPreferredSize(new Dimension(400,600));
		GUI.pack();
		
		//this button, when clicked, will run the simulation.
		runSim=new JButton("Run Simulation");
		runSim.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {		
				int runs=0;
				try{
					runs=Integer.parseInt(numberOfRuns.getText());
				}
				catch(Exception e){
				}
				
				Entity Attacker=new Entity(attackerFile.getText(), results);
				Entity Defender=new Entity(defenderFile.getText(), results);
				if(runs<=0){
					results.append("That number of runs doesn't make sense...\n\n");
				}

				else if(!results.getText().endsWith(".")){

					Combat Battle=new Combat(Attacker, Defender, results);
					results.append("Running Simulation. This may take a while....\n\n");
					Battle.runSim(runs);
				}
			}	
		});
		runSim.setBounds(100,450,200,100);
		
		//this is a base text field for whatever I need to convey to the user, be it simulation results or error messages.
		results=new JTextArea();
		//results.setBounds(50,175,300,200);
		results.setEditable(false);
		results.setLineWrap(true);
		results.setWrapStyleWord(true);
		
		//this is a scroll pane that will rule the universe
		upgradeYo=new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		upgradeYo.setBounds(50,225,300,200);
		upgradeYo.setVisible(true);
		
		//this is a text field for the file name of the attacker
		//the focus listener emulates the look for real text fields on websites and such, how there's text saying what to do for the field,
		//then you click it and it disappears and you can put whatever you want.
		attackerFile=new JTextField("Attacker File Name here!");
		attackerFile.setBounds(25, 75, 200, 25);
		attackerFile.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent arg0) {
				if(attackerFile.getText().equals("Attacker File Name here!")){
					attackerFile.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(attackerFile.getText().equals("")){
					attackerFile.setText("Attacker File Name here!");
				}
			}
		});
		
		//this is a text field for the file name of the defender
		//the focus listener emulates the look for real text fields on websites and such, how there's text saying what to do for the field,
		//then you click it and it disappears and you can put whatever you want.
		defenderFile=new JTextField("Defender File Name here!");
		defenderFile.setBounds(25, 125, 200, 25);
		defenderFile.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent arg0) {
				if(defenderFile.getText().equals("Defender File Name here!")){
					defenderFile.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(defenderFile.getText().equals("")){
					defenderFile.setText("Defender File Name here!");
				}
			}
		});
		
		
		//this is a text field for the number of repetitions to do
		//the focus listener emulates the look for real text fields on websites and such, how there's text saying what to do for the field,
		//then you click it and it disappears and you can put whatever you want.
		numberOfRuns=new JTextField("Number of runs here!");
		numberOfRuns.setBounds(100, 175, 200, 25);
		numberOfRuns.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent arg0) {
				if(numberOfRuns.getText().equals("Number of runs here!")){
					numberOfRuns.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(numberOfRuns.getText().equals("")){
					numberOfRuns.setText("Number of runs here!");
				}
			}
		});
		
		editAttacker=new JButton("Edit this Entity");
		editAttacker.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				EntityEditor attackerEditor=new EntityEditor(results, attackerFile.getText());
			}			
		});
		editAttacker.setBounds(225, 75, 150, 25);
		
		editDefender=new JButton("Edit this Entity");
		editDefender.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				EntityEditor defenderEditor=new EntityEditor(results, defenderFile.getText());
			}			
		});
		editDefender.setBounds(225, 125, 150, 25);
		
		newEntity=new JButton("Create a new Entity");
		newEntity.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				EntityEditor newEntity=new EntityEditor(results);
			}
		});
		newEntity.setBounds(100, 25, 200, 25);
		
		GUI.add(runSim);
		GUI.add(upgradeYo);
		GUI.add(defenderFile);
		GUI.add(attackerFile);
		GUI.add(numberOfRuns);
		GUI.add(editAttacker);
		GUI.add(editDefender);
		GUI.add(newEntity);
		GUI.setVisible(true);
	}
}