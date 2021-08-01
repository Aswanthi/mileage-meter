/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelcalc;

import java.awt.Component;
    import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Aswanthi
 */
public class FuelCalc {

    Component rootPane;
   
    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        //DB connection Obj
         DbOperations dbObj = new DbOperations();
         FuelCalc mileageMeter = new FuelCalc();
         
          JFrame f= new JFrame("Mileage Management");  
          JLabel title;  
        
          title=new JLabel();
          title.setText("M - METER");
          title.setBounds(100,15,100,100);  
         
          JTextField date,initialReading,finalReading,fuelUsed;
          JLabel dateLabel = new JLabel("Date");
          date=new JTextField();  
          dateLabel.setBounds(100, 100, 200,30);
         date.setBounds(200,100, 200,30); 
        
         
         JLabel initialReadingLabel = new JLabel("Initial Reading");
         initialReading=new JTextField();  
         initialReadingLabel.setBounds(100,150, 200,30);  
         initialReading.setBounds(200,150, 200,30);  
         
         JLabel fuelUsedLabel = new JLabel("Litre of Fuel Filled");
         fuelUsed=new JTextField(); 
         fuelUsedLabel.setBounds(100,200, 200,30);
         fuelUsed.setBounds(200,200, 200,30);
         
         JButton startButton=new JButton("Start");  
         startButton.setBounds(200,250,95,30); 
         
         JLabel finalReadingLabel = new JLabel("Final Reading");
         finalReading=new JTextField ();
         finalReadingLabel.setBounds(100,300,200,30);
         finalReading.setBounds(200,300,200,30);
         
         JButton reserveButton=new JButton("Reserve");
         reserveButton.setBounds(200,350,95,30);
         
         JButton showReportButton=new JButton("Show Report");
         showReportButton.setBounds(300,350,120,30);
         
         
         
         
         f.add(dateLabel);
         f.add(date);
         f.add(initialReadingLabel);
         f.add(initialReading); 
         f.add(fuelUsedLabel);
         f.add(fuelUsed);
         f.add(startButton);  
         f.add(finalReadingLabel);
         f.add(finalReading);
         f.add(reserveButton);
         f.add(showReportButton);
         
       
       
          
        //add Components
        f.add(title);
         
        f.setSize(600,600); 
        f.setLayout(null);  
        f.setVisible(true);

        startButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
            String d = date.getText();
            String iR = initialReading.getText();
            String fuel = fuelUsed.getText();
            dbObj.insertinitialReadings(d,iR,fuel,mileageMeter);
            }
           });
        
        reserveButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
            String d = date.getText();
            String fR = finalReading.getText();
            
            dbObj.insertfinalReadings(d,fR,mileageMeter);
            }
           });
        
        showReportButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
            dbObj.fetchResults(mileageMeter,f);
            }
           });
        
    }
    public void displayResults(int mileage,int fuel,int distance ) 
    { 
         JFrame f= new JFrame("Mileage Management"); 
         
         JLabel title;  
        
          title=new JLabel();
          title.setText("RESULTS");
          title.setBounds(100,15,100,100);
         
       JLabel kilometrestraveledLabel = new JLabel("Kilometres Traveled");
       JTextField kilometrestraveled=new JTextField (String.valueOf(distance));
        kilometrestraveledLabel.setBounds(50, 100, 200,30);
        kilometrestraveled.setBounds(200, 100, 200,30);  
        
         JLabel fuelLabel = new JLabel("Fuel Used");
         JTextField fuelValue =new JTextField (String.valueOf(fuel));
        fuelLabel.setBounds(50,200, 200,30);
         fuelValue.setBounds(200, 200, 200,30);
         
         
        JLabel mileageLabel = new JLabel("Mileage ");
         JTextField mileageValue =new JTextField (String.valueOf(mileage));
        mileageLabel.setBounds(50, 300, 200,30);
         mileageValue.setBounds(200,300,200,30);
         
         f.add(kilometrestraveledLabel);
         f.add(kilometrestraveled);
         f.add(fuelLabel);
         f.add(fuelValue);
         f.add(mileageLabel);
         f.add(mileageValue);
        f.add(title);
        f.setSize(600,600); 
        f.setLayout(null);  
        f.setVisible(true);
         
    
    }
       //method to show an error alert
    public void alert(String msg, String title) {
        JOptionPane.showMessageDialog(rootPane, msg, title, JOptionPane.ERROR_MESSAGE);
    }
}
