
package ScreenDemo;

import static ScreenDemo.QuizFrame.n;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FeedBack extends JFrame implements ActionListener{
    static int n=3;
    JLabel jb1,jb2;
    JButton btn;
    JPanel jp;
    JTable tb;
    public FeedBack()
    {
        int c=0,e;
        double sum;
        try{//run DataBase
                  Class.forName("org.apache.derby.jdbc.ClientDriver");//Returns the class object of the class or interface associated with the given string name
                  Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");//Call driver
        
                  Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);//Database sends SQL statement to execute
                  ResultSet rs=stm.executeQuery("select * from DAAN");//Call the Da an table in the database
                  rs.last();
                  c=rs.getInt(1);//Assign the contents to C
                  rs.close();
                  con.close();        
           }
       catch (Exception ex) {
                System.out.println(ex);
            }
        e=20-c;
        sum=(c*1.0)/20*100;
        String c1=""+c;
        String e1=""+e;
        String sum1=""+sum;
        String[] l = {"information","achievement"};//Header

	String[][] h = new String[4][2];
	h[0] = new String[]{"题目共","20"};		
	h[1] = new String[]{"正确",c1};
        h[2] = new String[]{"错误",e1};
        h[3] = new String[]{"成绩",sum1};
        jb1=new JLabel();
        jb2=new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    
        btn=new JButton("关闭");
        btn.addActionListener(this);
        
        DefaultTableModel defaultTableModel = new DefaultTableModel(h,l);//Default table control model
	JTable jTable = new JTable(defaultTableModel);
	jTable.setBounds(60,120,750,320);//Table location
        jTable.setFont(new Font("微软雅黑", Font.BOLD, 25));//Set font
        jTable.setRowHeight(80);//Set cell height
        jTable.getColumnModel().getColumn(0).setPreferredWidth(200);//Set cell width
        jp=new JPanel();
        
          try{  
                  Class.forName("org.apache.derby.jdbc.ClientDriver");
                  Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");
        
                  Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                  ResultSet rs=stm.executeQuery("select * from LOGIN");
                  rs.last();
                  jb1.setText("用户名: "+rs.getString(3)+"， 下面是你的考试成绩:");
                  rs.close();
                  con.close();        
       }
       catch (Exception ex) {
                System.out.println(ex);
            }
          
        jb1.setBounds(30,15, 800, 40);
        jb1.setFont(new Font("隶书", Font.ITALIC, 25));
        jb2.setBounds(10,70, 900, 20);
        btn.setBounds(670,470,140,50);
        btn.setFont(new Font("楷书", Font.ITALIC, 25));
        jp.setLayout(null); 
        jp.add(jb1);
        jp.add(jb2);
        jp.add(btn);
        jp.add(jTable);
        jp.setBackground(Color.ORANGE);
        add(jp);
        setBounds(500,150,900,600);
      
        setVisible(true);
    }
    public static void main(String[] args) {
        FeedBack h1=new FeedBack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
          if(e.getActionCommand()=="关闭")
          {
              setVisible(false);
          }
    }
}
