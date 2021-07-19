package ScreenDemo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class QuizFrame extends JFrame implements ActionListener{
    static int n=2;//The third page defines n as the second
    int i=1;
    double m=0;//Number of stored right
    JPanel jp;
    JLabel jb1,jb2,jb3;
    JTextArea ja;
    JRadioButton jr1,jr2,jr3,jr4;
    ButtonGroup group;
    JButton but1,but2,but3;
    public QuizFrame()
    {
        n++;
        int m=n-2;//Number of stored right
         setBounds(500,150,900,750);//Location size of JFrame
        this.setVisible(true);//Page display
        this.setTitle("题目");
        
        jp=new JPanel();
        
        jb1=new JLabel();
        jb2=new JLabel("问题: 1 .. 20"); 
        jb3=new JLabel("问题"); 
       
      
        ja=new JTextArea("1、Which one of the followingg featyres of Java allows a program to simultaneously execute multiple task ?"+"\r\n"+"\r\n"+"1、Multitheading"+"\r\n"+"2、Protability"+"\r\n"+"3、Distributed"+"\r\n"+"4、Garbage colection");
        
        jr1=new JRadioButton("Answer 1");  
        jr1.addActionListener(this);
        jr2=new JRadioButton("Answer 2"); 
        jr2.addActionListener(this);
        jr3=new JRadioButton("Answer 3"); 
        jr3.addActionListener(this);
        jr4=new JRadioButton("Answer 4");
        jr4.addActionListener(this);
        
        group=new ButtonGroup();//Put the raidiobutton in a group
        group.add(jr1);
        group.add(jr2);
        group.add(jr3);
        group.add(jr4);
        
        but1=new JButton("保存");
        but1.addActionListener(this);//event listeners
        but2=new JButton("下一题");
        but2.addActionListener(this);
        but3=new JButton("提交");
        but3.addActionListener(this);
        
        jb1.setBounds(20,25,700,40);
        jb1.setFont(new Font("微软雅黑", Font.ITALIC, 20));
        jb2.setBounds(580,25,200,40);
        jb2.setFont(new Font("微软雅黑", Font.ITALIC, 20));
        jb3.setBounds(20,100,150,40);
        jb3.setFont(new Font("微软雅黑", Font.ITALIC, 20));
        
        ja.setBounds(170,115,640,300);
        ja.setFont(new Font("微软雅黑", Font.BOLD, 18));
        ja.setLineWrap(true);//Automatic line feed
        jr1.setBounds(200,430,150,30);
        jr1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        jr2.setBounds(540,430,150,30);
        jr2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        jr3.setBounds(200,480,150,30);
        jr3.setFont(new Font("微软雅黑", Font.BOLD, 20));
        jr4.setBounds(540,480,150,30);
        jr4.setFont(new Font("微软雅黑", Font.BOLD, 20));
        
        but1.setBounds(70,560,100,40);
        but1.setFont(new Font("微软雅黑", Font.ITALIC, 20));
        but2.setBounds(290,560,100,40);
        but2.setFont(new Font("微软雅黑", Font.ITALIC, 20));
        but3.setBounds(650,560,150,40);
        but3.setFont(new Font("微软雅黑", Font.ITALIC, 20));
        try{  //Run DataBase
            
        Class.forName("org.apache.derby.jdbc.ClientDriver");//Returns the class object of the class or interface associated with the given string name
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");//Call driver
        
        Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);//Database sends SQL statement to execute
        ResultSet rs=stm.executeQuery("select * from LOGIN");//Call the database to return the value
                  rs.relative(1);
                 jb1.setText("用户名:"+rs.getString(3));//Call database to display student name
                 rs.close(); 
                 con.close();
       }
       catch (Exception ex) {
                System.out.println(ex);
            }
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.add(ja);
        jp.add(jr1);
        jp.add(jr2);
        jp.add(jr3);
        jp.add(jr4);
        jp.add(but1);
        jp.add(but2);
        jp.add(but3);
        jp.setLayout(null);//custom layout
        this.add(jp);
        jp.setBackground(Color.cyan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new QuizFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
           int s=i;//i Control pointer movement
           int num=0;
           if(e.getActionCommand()=="保存")
           {
               if(s<=19)
               {
                   JOptionPane.showMessageDialog(jp, "请完成二十个问题", "提示",JOptionPane.ERROR_MESSAGE);//message box
               }
              else if(s==20)
               {
                   s++;
                   if(jr3.isSelected())
                       {
                           m++;
                       }
                   JOptionPane.showMessageDialog(jp,"保存成功，请点击提交按钮查看成绩","提示",JOptionPane.CANCEL_OPTION);
                }
               }
           else if(e.getActionCommand()=="下一题"&&(jr1.isSelected()||jr2.isSelected()||jr3.isSelected()||jr4.isSelected()))//You must select a railio button and click the button to go to the next topic
           {
               if(jr1.isSelected())
               {                 
                   
                   try{
                      Class.forName("org.apache.derby.jdbc.ClientDriver");
                       Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");
        
                       Statement stm=con.createStatement();
                       stm.executeUpdate("update ANSER set ANSWERS='answer1'");//Write answer1 to database
                       con.close();
                       stm.close();
                   }
                   catch(Exception xe)
                   {
                       System.out.println(xe);
                   }
                   i++;
               }
               else if(jr2.isSelected())
               {
                  
                   try{
                       Class.forName("org.apache.derby.jdbc.ClientDriver");
                       Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");
        
                       Statement stm=con.createStatement();
                       stm.executeUpdate("update ANSER set ANSWERS='answer2'");
                       con.close();
                       stm.close();
                   }
                   catch(Exception xe)
                   {
                       System.out.println(xe);
                   } 
                   i++;
               }
               else if(jr3.isSelected())
               {
                  
                   try{
                      Class.forName("org.apache.derby.jdbc.ClientDriver");
                       Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");
        
                       Statement stm=con.createStatement();
                       
                       stm.executeUpdate("update ANSER set ANSWERS='answer3'");
                       con.close();
                       stm.close();
                   }
                   catch(Exception xe)
                   {
                       System.out.println(xe);
                   }
                   i++;
               }
         
               else if(jr4.isSelected())
               {
                   
                   try{
                       Class.forName("org.apache.derby.jdbc.ClientDriver");
                       Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");
        
                       Statement stm=con.createStatement();
                       stm.executeUpdate("update ANSER set ANSWERS='answer4'");
                       con.close();
                       stm.close();
                   }
                   catch(Exception xe)
                   {
                       System.out.println(xe);
                   }
                   i++;
               }
               try{
                   Class.forName("org.apache.derby.jdbc.ClientDriver");
                   Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");
        
                   Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                   ResultSet rs=stm.executeQuery("select * from ANSER");                 
                   rs.relative(i);//Pointer movement of control table
                   ja.setText(rs.getString(1));
                   rs.close();
                   stm.close();
                   con.close();
               }
               catch (Exception ex) {
                System.out.println(ex);
            }
                
                jb2.setText("问题:"+i+" of 20");
                
                try{
                   Class.forName("org.apache.derby.jdbc.ClientDriver");
                   Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");
        
                   Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                   ResultSet rs=stm.executeQuery("select * from ANSER");                 
                   rs.relative(i);
                  
                   if(rs.getString(2).equals(rs.getString(3))&&s<=21)//Compare the contents of the second and third columns. If they are the same, m plus one
                       {
                           m++;
                       }
                   rs.close();
                   stm.close();
                   con.close();
                }
                 catch(Exception xe)
                 {
                     System.out.println(xe);
                 }
            if(i==20)
               {
                    JOptionPane.showMessageDialog(jp, "最后一题！请在选择后单击“保存”", "提示",JOptionPane.ERROR_MESSAGE);//The last question. Tips
                }
            group.clearSelection();
           }
           
           else if(e.getActionCommand()=="提交")
           {
              if(i==20)
              {
                  try{
                  
                  Class.forName("org.apache.derby.jdbc.ClientDriver");
                  Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");
                  con.setAutoCommit(false);
                 
                  PreparedStatement ps=con.prepareStatement("insert into DAAN values(?)");//Put the number of correct questions into the database
               
                while(true)
                {           
                ps.setInt(1, (int) m);                
                ps.executeUpdate();
                con.commit();                            
                break;
                 }
                con.commit(); 
                con.close();
                FeedBack h1=new FeedBack();//Go to the feedbank page
                setVisible(false);//Close this page
              
            } catch (Exception ex) {
                System.out.println(ex);
            }
                  
              }
              else
              {
                   JOptionPane.showMessageDialog(jp, "还有未完成的选项", "提示",JOptionPane.ERROR_MESSAGE); //Message prompt box               
              }
           }
    }
}
