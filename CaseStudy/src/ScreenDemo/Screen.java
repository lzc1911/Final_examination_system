package ScreenDemo;

import static ScreenDemo.LoginScree.n;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Screen extends JFrame implements ActionListener {

    static int n = 0;//Represents the first interface
    JPanel jp;
    JLabel L1, L2, L3, L4, L5, L6, jpi;
    JTextField jb1, jb2, jb3, jb4;
    JButton but1, but2;

    public Screen() {
        n++;
        jb1 = new JTextField("");
        jb2 = new JTextField("");
        jb3 = new JTextField("");
        jb4 = new JTextField("");

        jp=new JPanel(){
        public void paintComponent(Graphics g)
        {
             ImageIcon icon=new ImageIcon("img/beijing.jpg");//The background image
             g.drawImage(icon.getImage(), 0, 0,this);
        }
       };
        L1 = new JLabel("注册信息");
        L2 = new JLabel("------------------------------------------------------------------------------------------------------");
        L3 = new JLabel("用户名");
        L4 = new JLabel("账号");
        L5 = new JLabel("密码");
        L6 = new JLabel("确认密码");

        ImageIcon img = new ImageIcon("pimg/suo1.png");//add images
        jpi = new JLabel();
        jpi.setIcon(img);

        but1 = new JButton("确认");
        but2 = new JButton("清除");

        L1.setBounds(140, 30, 500, 40);//Adjust the position in the interface
        L1.setFont(new Font("隶书", Font.ITALIC, 25));
        L1.setForeground(Color.MAGENTA);
        L2.setBounds(30, 90, 900, 20);
        L2.setFont(new Font("隶书", Font.ITALIC, 25));
        L3.setBounds(80, 130, 200, 40);
        L3.setForeground(Color.MAGENTA);
        L3.setFont(new Font("隶书", Font.ITALIC, 25));
        L4.setBounds(80, 210, 200, 40);
        L4.setForeground(Color.MAGENTA);
        L4.setFont(new Font("隶书", Font.ITALIC, 25));
        L5.setBounds(80, 290, 200, 40);
        L5.setForeground(Color.MAGENTA);
        L5.setFont(new Font("隶书", Font.ITALIC, 25));
        L6.setBounds(80, 370, 250, 40);
        L6.setForeground(Color.MAGENTA);
        L6.setFont(new Font("隶书", Font.ITALIC, 25));

        jpi.setBounds(40, 5, 90, 90);

        jb1.setBounds(340, 130, 400, 40);
        jb2.setBounds(340, 210, 400, 40);
        jb3.setBounds(340, 290, 400, 40);
        jb4.setBounds(340, 370, 400, 40);

        but1.setBounds(330, 450, 180, 50);
        but1.setFont(new Font("隶书", Font.ITALIC, 25));
        but1.addActionListener(this);
        but2.setBounds(570, 450, 180, 50);
        but2.setFont(new Font("隶书", Font.ITALIC, 25));
        but2.addActionListener(this);//Event listenners

        jp.setLayout(null);
        jp.add(L1);
        jp.add(L2);
        jp.add(L3);
        jp.add(L4);
        jp.add(L5);
        jp.add(L6);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.add(jb4);
        jp.add(but1);
        jp.add(but2);
        jp.add(jpi);
        jp.setLayout(null);
        this.add(jp);
        setBounds(500, 150, 900, 600);
        this.setTitle("用户注册");
        this.setVisible(true);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Exit JFrame
    }

    public static void main(String[] args) {
        new Screen();
    }

    @Override//Subtypes
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "确认") {
            try {
                String uname = jb1.getText().toString();//Pass the resulting output to uname
                String upass1 = jb2.getText().toString();
                String upass2 = jb3.getText().toString();
                String upass3 = jb4.getText().toString();

                Class.forName("org.apache.derby.jdbc.ClientDriver");//Returns the class object of the class or interface associated with the given string name
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/NIITP");//Call driver
                con.setAutoCommit(false);

                PreparedStatement ps = con.prepareStatement("insert into LOGIN values(?,?,?)");//Add data to the login table

                if (uname.equals("")) {
                    JOptionPane.showMessageDialog(jp, "请输入用户名", "提示", JOptionPane.ERROR_MESSAGE);//Prompt information
                } else if (upass1.equals("")) {
                    JOptionPane.showMessageDialog(jp, "请输入账号", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (upass2.equals("")) {
                    JOptionPane.showMessageDialog(jp, "请输入密码", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (upass3.equals(upass2) == false) {
                    JOptionPane.showMessageDialog(jp, "密码不一致", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    while (true) {
                        ps.setString(1, upass1);//Give the value back to the first column
                        ps.setString(2, upass2);
                        ps.setString(3, uname);
                        ps.executeUpdate();
                        con.commit();
                        break;
                    }
                    con.commit();
                    con.close();//Close the registration screen
                    LoginScree hf = new LoginScree();
                    setVisible(false);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getActionCommand() == "清除") {
            jb1.setText("");//Empty
            jb2.setText("");
            jb3.setText("");
            jb4.setText("");
        }
    }
}
