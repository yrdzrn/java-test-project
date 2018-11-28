package com.sust.farmhousemanage;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Modifier;
import java.util.List;
import javax.swing.*;
import java.sql.*;
/**
 * @author 25868
 */
public class ShowFarmHouseInfoFrame extends JFrame {
    FarmHouse fh = new FarmHouse();
    JTextArea fhInfoListJTA = null;
    //对输出进行处理
    public StringBuffer setStringLen(int len) {
        StringBuffer string = new StringBuffer();
        for(int i = 0; i < 80-len; i++) {
            string.append(" ");
        }
        return string;
    }
    /**
     * 显示所有农家乐信息
     */
    public void showAllStuInfo(){
        fhInfoListJTA.setText("");
        List<FarmHouse> list = fh.queryAllStuInfo();
        for(FarmHouse farmhouse:list) {
            String name = farmhouse.getfhName();
            String addr = farmhouse.getfhAddr();
            String pername = farmhouse.getfhPername();
            String pertel = farmhouse.getfhPertel();
            fhInfoListJTA.setText(fhInfoListJTA.getText()
            		+name + setStringLen(name.getBytes().length).toString()
            		+ addr + setStringLen(addr.getBytes().length).toString()
            		+ pername + setStringLen(pername.getBytes().length).toString()
            		+ pertel + "\n");
        }
    }
    /**
     * 构造函数
     */
    public ShowFarmHouseInfoFrame() {
        this.setSize(1300, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        //界面标题部分
        JLabel titleJL = new JLabel("农家乐信息管理系统");
        Font font = new Font("微软雅黑", 1, 20);
        titleJL.setFont(font);
        titleJL.setLayout(null);
        titleJL.setBounds(550, 10, 200, 20);
        //按钮部分
        JPanel buttonJP = new JPanel();
        buttonJP.setLayout(null);
        buttonJP.setBackground(Color.blue);
        buttonJP.setBounds(0, 50, 1300, 40);
        JButton jbAdd = new JButton("添加农家乐信息");
        jbAdd.setBounds(0, 10, 175, 30);
        jbAdd.setBackground(Color.green);
        JButton jbDelete = new JButton("删除农家乐信息");
        jbDelete.setBounds(275, 10, 175, 30);
        jbDelete.setBackground(Color.green);
        JButton jbModify = new JButton("修改农家乐信息");
        jbModify.setBounds(550, 10, 175, 30);
        jbModify.setBackground(Color.green);
        JButton jbQueryByname = new JButton("查询农家乐信息");
        jbQueryByname.setBounds(825, 10, 175, 30);
        jbQueryByname.setBackground(Color.green);
        JButton jbQuery = new JButton("显示农家乐信息");
        jbQuery.setBounds(1100, 10, 175, 30);
        jbQuery.setBackground(Color.GREEN);
        //buttonJP.setLayout(new FlowLayout());
        buttonJP.add(jbAdd);
        buttonJP.add(jbDelete);
        buttonJP.add(jbModify);
        buttonJP.add(jbQueryByname);
        buttonJP.add(jbQuery);
        JPanel listHeadJP = new JPanel();
        listHeadJP.setBackground(Color.white);
        listHeadJP.setLayout(null);
        listHeadJP.setBounds(0, 120, 1300, 30);
        JLabel nameHeadJL = new JLabel(" 名 字");
        nameHeadJL.setBackground(Color.gray);
        nameHeadJL.setBounds(50, 0, 175, 25);
        JLabel addrHeadJL = new JLabel(" 地 址");
        addrHeadJL.setBackground(Color.gray);
        addrHeadJL.setBounds(400, 0, 175, 25);
        JLabel pernameHeadJL = new JLabel("负责人");
        pernameHeadJL.setBackground(Color.gray);
        pernameHeadJL.setBounds(660, 0, 175, 25);
        JLabel pertelHeadJL = new JLabel(" 电 话");
        pertelHeadJL.setBackground(Color.gray);
        pertelHeadJL.setBounds(1000, 0, 175, 25);
        listHeadJP.add(nameHeadJL);
        listHeadJP.add(addrHeadJL);
        listHeadJP.add(pernameHeadJL);
        listHeadJP.add(pertelHeadJL);
        Font fhInfoListFont = new  Font("微软雅黑", 0, 13);
        fhInfoListJTA = new JTextArea(20,20); 
        fhInfoListJTA.setBounds(10, 152, 1300, 400);
        fhInfoListJTA.setBorder (BorderFactory.createEmptyBorder (1,1,1,5));
        fhInfoListJTA.setColumns (20);
        fhInfoListJTA.setRows (20);//相当于设置文本区组件的初始大小,并不是说一行只能写0
        fhInfoListJTA.setFont(fhInfoListFont);
        this.add(titleJL);
        this.add(buttonJP);
        this.add(listHeadJP);
        this.add(fhInfoListJTA);
        showAllStuInfo();
        this.setVisible(true);
        //添加农家乐信息
        jbAdd.addMouseListener(new MouseAdapter(){
            //匿名内部类使用方法的临时变量需要提前，负责会让你改为final，改为final是为了延长变量的声明周期
            JTextField numJTF = null;
        	JTextField nameJTF = null;
            JTextField classJTF = null;
            JTextField professionalJTF = null;
            @Override
            public void mouseClicked(MouseEvent e) {
                final JFrame addJF = new JFrame();
                addJF.setSize(310, 300);
                addJF.setBackground(Color.gray);
                addJF.setLocationRelativeTo(null);
                addJF.setVisible(true);
                addJF.setLayout(new FlowLayout());
                JLabel numJL = new JLabel("名  字：");
                numJTF = new JTextField(20);
                JLabel nameJL = new JLabel("地  址：");
                nameJTF = new JTextField(20);
                JLabel classJL = new JLabel("负责人：");
                classJTF = new JTextField(20);
                JLabel professionalJL = new JLabel("电  话：");
                professionalJTF = new JTextField(20);
                //addJF.dispose();//销毁子窗口
                addJF.add(numJL);
                addJF.add(numJTF);
                addJF.add(nameJL);
                addJF.add(nameJTF);
                addJF.add(classJL);
                addJF.add(classJTF);
                addJF.add(professionalJL);
                addJF.add(professionalJTF);
                JButton addJB = new JButton("添加");
                addJF.add(addJB);
                addJB.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String nameAdd = numJTF.getText();
                        String addrAdd = nameJTF.getText();
                        String pernameAdd = classJTF.getText();
                        String pertelAdd = professionalJTF.getText();
                        //输入的信息为空的处理
                        if(nameAdd == "" | addrAdd == "" | pernameAdd == "" | pertelAdd == "") {
                            JFrame tipJF = new JFrame("提示");
                            tipJF.setVisible(true);
                            tipJF.setSize(200,100);
                            tipJF.setLocationRelativeTo(null);
                            JLabel tipJL = new JLabel("请填入了所有的信息之后再点击提交");
                            tipJF.add(tipJL);
                        }else {
                                fh = new FarmHouse();
                                fh.addStuInfo(nameAdd, addrAdd, pernameAdd, pertelAdd);
                                addJF.dispose();
                                JFrame addSuccessfulJF = new JFrame("提示");
                                addSuccessfulJF.setLocationRelativeTo(null);
                                addSuccessfulJF.setSize(260, 100);
                                addSuccessfulJF.setVisible(true);
                                addSuccessfulJF.setLayout(new FlowLayout());
                                JLabel SuccessfulJL = new JLabel("添加农家乐信息成功！");
                                addSuccessfulJF.add(SuccessfulJL);
                        }
                    }
                });
            }
        });
        //删除农家乐信息
        jbDelete.addMouseListener(new MouseAdapter() {
            JTextField numJTF = null;
            @Override
            public void mouseClicked(MouseEvent e) {
                final JFrame deleteJF = new JFrame("删除农家乐信息");
                deleteJF.setLayout(new FlowLayout());
                deleteJF.setSize(300, 80);
                deleteJF.setVisible(true);
                deleteJF.setLocationRelativeTo(null);
                JLabel numJL = new JLabel("名字:");
                numJTF = new JTextField(15);
                JButton numJB = new JButton("提交");
                deleteJF.add(numJL);
                deleteJF.add(numJTF);
                deleteJF.add(numJB);
                numJB.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                                String nameI = numJTF.getText();
                                fh.deleteStuInfo(nameI);
                                deleteJF.dispose();
                                JFrame deleteSuccessfulJF = new JFrame("提示");
                                deleteSuccessfulJF.setLocationRelativeTo(null);
                                deleteSuccessfulJF.setSize(260, 100);
                                deleteSuccessfulJF.setVisible(true);
                                deleteSuccessfulJF.setLayout(new FlowLayout());
                                JLabel SuccessfulJL = new JLabel("删除农家乐信息成功！");
                                deleteSuccessfulJF.add(SuccessfulJL);
                    }
                });
            }
        });
        //修改农家乐信息
        jbModify.addMouseListener(new MouseAdapter(){
            JTextField numbJTF = null;
            @Override
            public void mouseClicked(MouseEvent e) {
                final JFrame modifyJF = new JFrame("修改农家乐信息");
                modifyJF.setSize(300, 110);
                modifyJF.setLocationRelativeTo(null);
                modifyJF.setLayout(new FlowLayout());
                modifyJF.setVisible(true);
                JLabel numbJL = new JLabel("名字：");
                numbJTF = new JTextField(20);
                JButton numbJB = new JButton("提交");
                modifyJF.add(numbJL);
                modifyJF.add(numbJTF);
                modifyJF.add(numbJB);
                numbJB.addMouseListener(new MouseAdapter() {
                    JTextField numJTF = null;
                    JTextField nameJTF = null;
                    JTextField classJTF = null;
                    JTextField professionalJTF = null;
                    String tempNum = null;
                    String tempName = null;
                    String tempClass = null;
                    String tempProfessional = null;
                    Connection conn = null;
                    @Override
                    public void mouseClicked(MouseEvent e) {
//////////////////////////////////////////////////////////////
//////////////显示名字对应的农家乐信息///////////////////////////////
/////////////////////////////////////////////////////////////
                        final JFrame modifyJF1 = new JFrame();
                        modifyJF1.setSize(310, 300);
                        modifyJF1.setBackground(Color.gray);
                        modifyJF1.setLocationRelativeTo(null);
                        modifyJF1.setVisible(true);
                        modifyJF1.setLayout(new FlowLayout());
                        JLabel numJL = new JLabel("名  字：");
                        numJTF = new JTextField(20);
                        JLabel nameJL = new JLabel("地  址：");
                        nameJTF = new JTextField(20);
                        JLabel classJL = new JLabel("负责人：");
                        classJTF = new JTextField(20);
                        JLabel professionalJL = new JLabel("电  话：");
                        professionalJTF = new JTextField(20);
                        modifyJF1.add(numJL);
                        modifyJF1.add(numJTF);
                        modifyJF1.add(nameJL);
                        modifyJF1.add(nameJTF);
                        modifyJF1.add(classJL);
                        modifyJF1.add(classJTF);
                        modifyJF1.add(professionalJL);
                        modifyJF1.add(professionalJTF);
                        //查询农家乐信息
                        String name = numbJTF.getText();
                        final FarmHouse stuRet = fh.queryStuInfo(name);
                        numJTF.setText(stuRet.getfhName());
                        nameJTF.setText(stuRet.getfhAddr());
                        classJTF.setText(stuRet.getfhPername());
                        professionalJTF.setText(stuRet.getfhPertel());
//////////////////////////////////////////////////////////////
//////////////提交修改后的农家乐信息///////////////////////////////
/////////////////////////////////////////////////////////////
                        JButton submitJB = new JButton("提交修改");
                        modifyJF1.add(submitJB);
                        submitJB.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                String addr = nameJTF.getText();
                                String pername = classJTF.getText();
                                String pertel = professionalJTF.getText();
                                fh.modifyStuInfo(stuRet, stuRet.getfhName(), addr, pername, pertel);
                                modifyJF1.dispose();
                                JFrame upStringSucessfulJF = new JFrame("提示");
                                upStringSucessfulJF.setLocationRelativeTo(null);
                                upStringSucessfulJF.setSize(260, 100);
                                upStringSucessfulJF.setVisible(true);
                                upStringSucessfulJF.setLayout(new FlowLayout());
                                JLabel SuccessfulJL = new JLabel("修改农家乐信息成功！");
                                upStringSucessfulJF.add(SuccessfulJL);
                            }
                        });
                        modifyJF.dispose();
                    }
                });
            }
        });
        //查询农家乐信息
        jbQueryByname.addMouseListener(new MouseAdapter(){
            JTextField numbJTF = null;
            @Override
            public void mouseClicked(MouseEvent e) {
                final JFrame QueryBynameJF = new JFrame("查询农家乐信息");
                QueryBynameJF.setSize(300, 110);
                QueryBynameJF.setLocationRelativeTo(null);
                QueryBynameJF.setLayout(new FlowLayout());
                QueryBynameJF.setVisible(true);
                JLabel numbJL = new JLabel("名字：");
                numbJTF = new JTextField(20);
                JButton numbJB = new JButton("提交");
                QueryBynameJF.add(numbJL);
                QueryBynameJF.add(numbJTF);
                QueryBynameJF.add(numbJB);
                numbJB.addMouseListener(new MouseAdapter() {
                    JTextField numJTF = null;
                    JTextField nameJTF = null;
                    JTextField classJTF = null;
                    JTextField professionalJTF = null;
                    String tempNum = null;
                    String tempName = null;
                    String tempClass = null;
                    String tempProfessional = null;
                    Connection conn = null;
                    @Override
                    public void mouseClicked(MouseEvent e) {
//////////////////////////////////////////////////////////////
//////////////显示名字对应的农家乐信息///////////////////////////////
/////////////////////////////////////////////////////////////
                        final JFrame QueryBynameJF1 = new JFrame();
                        QueryBynameJF1.setSize(310, 300);
                        QueryBynameJF1.setBackground(Color.gray);
                        QueryBynameJF1.setLocationRelativeTo(null);
                        QueryBynameJF1.setVisible(true);
                        QueryBynameJF1.setLayout(new FlowLayout());
                        JLabel numJL = new JLabel("名  字：");
                        numJTF = new JTextField(20);
                        JLabel nameJL = new JLabel("地  址：");
                        nameJTF = new JTextField(20);
                        JLabel classJL = new JLabel("负责人：");
                        classJTF = new JTextField(20);
                        JLabel professionalJL = new JLabel("电  话：");
                        professionalJTF = new JTextField(20);
                        QueryBynameJF1.add(numJL);
                        QueryBynameJF1.add(numJTF);
                        QueryBynameJF1.add(nameJL);
                        QueryBynameJF1.add(nameJTF);
                        QueryBynameJF1.add(classJL);
                        QueryBynameJF1.add(classJTF);
                        QueryBynameJF1.add(professionalJL);
                        QueryBynameJF1.add(professionalJTF);
                        //查询农家乐信息
                        String name = numbJTF.getText();
                        final FarmHouse stuRet = fh.queryStuInfo(name);
                        numJTF.setText(stuRet.getfhName());
                        nameJTF.setText(stuRet.getfhAddr());
                        classJTF.setText(stuRet.getfhPername());
                        professionalJTF.setText(stuRet.getfhPertel());
                        QueryBynameJF.dispose();
                    }
                });
            }
        });
        
        //查询农家乐信息
        jbQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //显示所有的农家乐信息
                showAllStuInfo();
            }
        });
    }
    public static void main(String[] args) {
        new ShowFarmHouseInfoFrame();
    }
}
