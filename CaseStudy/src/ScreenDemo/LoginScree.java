
package ScreenDemo;

import static com.sun.awt.SecurityWarning.getSize;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class LoginScree extends JFrame implements ActionListener{
    static int n=1;
    JPanel jp;
    JLabel jb1,jb2,jb3,jb4,jpi1,jpi2;
    JTextField jf1,jf2;
    JButton btn1,btn2,btn3;
    public LoginScree()
    {
        n++;
        int x=0,y=0;
        setBounds(500,150,900,400);
        this.setVisible(true);
        this.setTitle("用户登录");
        
        ImageIcon img = new ImageIcon("img/suo1.png"); 
        ImageIcon img1 = new ImageIcon("img/bi1.png"); //Add picture     
        jp=new JPanel(){
        public void paintComponent(Graphics g)//Add background picture
        {
            ImageIcon icon=new ImageIcon("img/beijing.jpg");
            g.drawImage(icon.getImage(), x, y,this);
        }
       };
        jb1=new JLabel("输入账号以及密码");
        jb2=new JLabel("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jb3=new JLabel("账号");
        jb4=new JLabel("密码");
        
        jpi1=new JLabel();
        jpi2=new JLabel();
        jpi1.setIcon(img);
        jpi2.setIcon(img1);
        
        jf1=new JTextField();
        jf2=new JTextField();
        
        btn1=new JButton("登录");
        btn1.addActionListener(this);
        btn2=new JButton("注册");
        btn2.addActionListener(this);
        btn3=new JButton("清除");
        btn3.addActionListener(this);
        
        jb1.setBounds(140,50,500,40);
        jb1.setFont(new Font("隶书", Font.ITALIC, 25));
        jb2.setBounds(20,95,900,5);
        jb3.setBounds(350,130,100,40);
        jb3.setFont(new Font("微软雅黑", Font.BOLD, 25));
        jb4.setBounds(350,200,120,40);
        jb4.setFont(new Font("微软雅黑", Font.BOLD, 25));
        
        jpi1.setBounds(40,5,90,90);
        jpi2.setBounds(40,110,260,150);
        jf1.setBounds(490,130,250,40);
        jf2.setBounds(490,200,250,40);
        btn1.setBounds(60,280,210,50);
        btn1.setFont(new Font("微软雅黑", Font.ITALIC, 20));
        btn2.setBounds(305,280,210,50);
        btn2.setFont(new Font("微软雅黑", Font.ITALIC, 20));
        btn3.setBounds(545,280,210,50);
        btn3.setFont(new Font("微软雅黑", Font.ITALIC, 20));
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.add(jb4);
        jp.add(jf1);
        jp.add(jf2);
        jp.add(btn1);
        jp.add(btn2);
        jp.add(btn3);
        jp.add(jpi1);
        jp.add(jpi2);
        jp.setLayout(null);
        add(jp);
        setLocationRelativeTo(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        LoginScree obj=new LoginScree();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getActionCommand() == "登录") {
            try {//Run DataBase
                String uid = jf1.getText().toString();
                String upass = jf2.getText().toString();
            
                Class.forName("org.apache.derby.jdbc.ClientDriver");//Returns the class object of the class or interface associated with the given string name
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");//Call driver

                PreparedStatement pst = con.prepareStatement("select * from LOGIN where USERID=?");//USERID of calling database to table login
                pst.setString(1, uid);

                ResultSet rs = pst.executeQuery();//Call the database to return the value

                while (rs.next()) {
                    
                   if(upass.equals(rs.getString(2))==false)
                    {
                        JOptionPane.showMessageDialog(jp,"Your Password is incorrect","error",JOptionPane.ERROR_MESSAGE);//Message Box                       
                    }
                   else if(uid.equals(rs.getString(1))==false)
                    {
                        JOptionPane.showMessageDialog(jp,"Your ID is incorrect","error",JOptionPane.ERROR_MESSAGE);                       
                    }
                    else if (uid.equals(rs.getString(1)) && upass.equals(rs.getString(2))) {
                         QuizFrame g2=new QuizFrame();
                         setVisible(false);
                       
                    }

                }

            } 
            catch (Exception ex) {
                System.out.println(ex);
            }
            
        } else if (e.getActionCommand() == "注册") {
             try{
                      Class.forName("org.apache.derby.jdbc.ClientDriver");
                       Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");
        
                       Statement stm=con.createStatement();
                       stm.executeUpdate("delete from LOGIN");
                       con.close();
                   }
                   catch(Exception xe)
                   {
                       System.out.println(xe);
                   }
             n=n-2;
             Screen g1=new Screen();
             setVisible(false);
        }
        else if(e.getActionCommand()=="清除")
        {
            jf1.setText("");
            jf2.setText("");
        }
    }
    }

