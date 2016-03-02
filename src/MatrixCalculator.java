 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MatrixCalculator {

   private JFrame mainFrame;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JPanel controlPanel2;
   private JPanel resultPanel;
   public static Matrix mat1;
   public static Matrix mat2;
   public JTextArea resultTextArea;
   public MatrixCalculator(){
      prepareGUI();
   }

   public static void main(String[] args){
      MatrixCalculator  swingControlDemo = new MatrixCalculator();      
      swingControlDemo.matrixArea1();
      swingControlDemo.matrixArea2();
      swingControlDemo.resultArea();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Matrix Calculator");
      mainFrame.setSize(450,200);
      mainFrame.setLayout(new GridLayout(1, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      statusLabel = new JLabel("",JLabel.CENTER);    

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      controlPanel2 = new JPanel();
      controlPanel2.setLayout(new FlowLayout());

      resultPanel = new JPanel();
      resultPanel.setLayout(new FlowLayout());

      mainFrame.add(controlPanel);
      mainFrame.add(controlPanel2);
      mainFrame.add(resultPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }


   private void matrixArea1(){
      JLabel  commentlabel= new JLabel("Matrix A: ", JLabel.RIGHT);
      final JTextArea commentTextArea = new JTextArea(5,7);
      JScrollPane scrollPane = new JScrollPane(commentTextArea);    
      JButton createMatrixButton = new JButton("Create Mat A");
      JButton determinantButton = new JButton("Determinant");
      createMatrixButton.setForeground(Color.RED);
      createMatrixButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { 
            String dem = commentTextArea.getText();
            String[] valuesInStr = dem.split("\\W+");
            int []values = new int[valuesInStr.length];
            for(int i =0;i<valuesInStr.length;i++){
               values[i] = Integer.parseInt(valuesInStr[i]);
            }
            int rows = commentTextArea.getLineCount();
            int columns = values.length/commentTextArea.getLineCount();
         	mat1 = new Matrix(rows,columns);
            mat1.populateMatrix(values);
         }
      }); 
      determinantButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { 
            resultTextArea.replaceRange(null,0,resultTextArea.getText().length());
            resultTextArea.append(""+mat1.getDeterminant());
         }
      }); 
      controlPanel.add(commentlabel);
      controlPanel.add(scrollPane);        
      controlPanel.add(createMatrixButton);
      controlPanel.add(determinantButton);
      controlPanel.setVisible(true);
      mainFrame.setVisible(true);  
   }


   private void matrixArea2(){
      JLabel  commentlabel= new JLabel("Matrix B: ", JLabel.RIGHT);
      final JTextArea commentTextArea = new JTextArea(5,7);
      JScrollPane scrollPane = new JScrollPane(commentTextArea);    
      JButton createMatrixButton = new JButton("Create Mat B");
      JButton determinantButton = new JButton("Determinant");
      createMatrixButton.setForeground(Color.RED);
      createMatrixButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { 
            String dem = commentTextArea.getText();
            String[] valuesInStr = dem.split("\\W+");
            int []values = new int[valuesInStr.length];
            for(int i =0;i<valuesInStr.length;i++){
               values[i] = Integer.parseInt(valuesInStr[i]);
            }
            int rows = commentTextArea.getLineCount();
            int columns = values.length/commentTextArea.getLineCount();
            mat2 = new Matrix(rows,columns);
            mat2.populateMatrix(values);
         }
      }); 
      determinantButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { 
            resultTextArea.replaceRange(null,0,resultTextArea.getText().length());
            resultTextArea.append(""+mat2.getDeterminant());
         }
      });
      controlPanel2.add(commentlabel);
      controlPanel2.add(scrollPane);        
      controlPanel2.add(createMatrixButton);
      controlPanel2.add(determinantButton);
      mainFrame.setVisible(true);  
   }


   private void resultArea(){
      JLabel commentlabel = new JLabel("Result",JLabel.RIGHT);
      resultTextArea = new JTextArea(5,7);
      JScrollPane scrollPane = new JScrollPane(resultTextArea);
      JButton sumButton = new JButton(" A + B ");
      JButton multiplyButton = new JButton(" A * B ");
      sumButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { 
            resultTextArea.replaceRange(null,0,resultTextArea.getText().length());
            Matrix res = mat1.addMatrices(mat2);
            resultTextArea.append(res.getStringRepresentation(res));
         }
      });
      multiplyButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { 
            resultTextArea.replaceRange(null,0,resultTextArea.getText().length());
            Matrix res = mat1.multiplyMatrices(mat2);
            resultTextArea.append(res.getStringRepresentation(res));
         }
      });
      resultPanel.add(commentlabel);
      resultPanel.add(scrollPane);
      resultPanel.add(sumButton);
      resultPanel.add(multiplyButton);
      mainFrame.setVisible(true);
   }
}