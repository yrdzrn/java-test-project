package com.sust.farmhousemanage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmHouse {
    private String fhName;//农家乐名字
    private String fhAddr;//农家乐地址
    private String fhPername;//负责人名字
    private String fhPertel;//负责人电话
    public FarmHouse() {
    }
    public FarmHouse(String fhName, String fhAddr, String fhPername, String fhPertel) {
        super();
        this.fhName = fhName;
        this.fhAddr = fhAddr;
        this.fhPername = fhPername;
        this.fhPertel = fhPertel;
    }
    public String getfhName() {
        return fhName;
    }
    public void setfhName(String fhName) {
        this.fhName = fhName;
    }
    public String getfhAddr() {
        return fhAddr;
    }
    public void setfhAddr(String fhAddr) {
        this.fhAddr = fhAddr;
    }
    public String getfhPername() {
        return fhPername;
    }
    public void setfhPername(String fhPername) {
        this.fhPername = fhPername;
    }
    public String getfhPertel() {
        return fhPertel;
    }
    public void setfhPertel(String fhPertel) {
        this.fhPertel = fhPertel;
    }
    public Connection connectToMySql() {
    	Connection con;
		//驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名
		String url = "jdbc:mysql://localhost:3306/farmhouse?useUnicode=true&characterEncoding=utf-8&useSSL=false";
								//ip: 192.168.44.1
		//用户名
		String user = "root";
		//密码
		String password = "mysql";
		//遍历查询结果
		try{
			//加载驱动程序
			Class.forName(driver);
			//1.getConnection()方法，连接MySQL数据库！！
			con = DriverManager.getConnection(url, user, password);
			if (!con.isClosed()){
				System.out.println("Succeeded connecting the DataBase!");
			}
			return con;
		} catch (ClassNotFoundException e) {
			//数据库驱动异常处理
			System.out.println("Sorry, can't find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			//数据库连接失败异常处理
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
    //添加农家乐信息
    public boolean addStuInfo(String name, String addr, String pername, String pertel) {
        StringBuffer sqlInsert = new StringBuffer(); 
        sqlInsert.append("insert into farmhouse (fhname, fhaddr, fhpername, fhpertel) values");
        sqlInsert.append("('");
        sqlInsert.append(name);
        sqlInsert.append("',");
        sqlInsert.append("'");
        sqlInsert.append(addr);
        sqlInsert.append("',");
        sqlInsert.append("'");
        sqlInsert.append(pername);
        sqlInsert.append("',");
        sqlInsert.append("'");
        sqlInsert.append(pertel);
        sqlInsert.append("')");
        System.out.println(sqlInsert);
        System.out.println(name + addr + pername + pertel);
        Connection conn = connectToMySql();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sqlInsert.toString());
            ps.executeUpdate();
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("添加农家乐信息失败！");
            return false;
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    //删除农家乐信息
    public boolean deleteStuInfo(String name) {
        Connection conn = connectToMySql();
        StringBuffer deleteSql = new StringBuffer();
        deleteSql.append("delete from farmhouse where fhname=");
        deleteSql.append("'");
        deleteSql.append(name);
        deleteSql.append("'");
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(deleteSql.toString());
            ps.executeUpdate();
            return true;
        } catch (Exception e3) {
            e3.printStackTrace();
            System.out.println("数据库语句检查或执行出错");
            return false;
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    //查询农家乐信息
    public FarmHouse queryStuInfo(String name) {
        FarmHouse stu = new FarmHouse();
        StringBuffer querySql = new StringBuffer();
        querySql.append("select * from farmhouse where fhname=");
        querySql.append("'");
        querySql.append(name);
        querySql.append("'");
        Connection conn  = connectToMySql();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(querySql.toString());
            while(rs.next()) {
                stu.fhName = rs.getString("fhname");
                stu.fhAddr = rs.getString("fhaddr");
                stu.fhPername = rs.getString("fhpername");
                stu.fhPertel = rs.getString("fhpertel");
            }
            return stu;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
    }
    //修改农家乐信息
    public boolean modifyStuInfo(FarmHouse fh, String name, String addr, String pername, String pertel) {
        Connection conn = connectToMySql();
        //信息有改动才提交
        if(false == addr.equals(fh.fhAddr)) {
            StringBuffer alterSql = new StringBuffer();
            alterSql.append("update farmhouse set fhaddr='");
            alterSql.append(addr);
            alterSql.append("'where fhname = '");
            alterSql.append(name);
            alterSql.append("'");
            try {
                PreparedStatement ps = conn.prepareStatement(alterSql.toString());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        if(false == pername.equals(fh.fhPername)) {
        	StringBuffer alterSql = new StringBuffer();
            alterSql.append("update farmhouse set fhpername='");
            alterSql.append(pername);
            alterSql.append("'where fhname = '");
            alterSql.append(name);
            alterSql.append("'");
            try {
                PreparedStatement ps = conn.prepareStatement(alterSql.toString());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        if(false == pertel.equals(fh.fhPertel)) {
        	StringBuffer alterSql = new StringBuffer();
            alterSql.append("update farmhouse set fhpertel='");
            alterSql.append(pertel);
            alterSql.append("'where fhname = '");
            alterSql.append(name);
            alterSql.append("'");
            try {
                PreparedStatement ps = conn.prepareStatement(alterSql.toString());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public List<FarmHouse> queryAllStuInfo() {
        List<FarmHouse> stuList = new ArrayList<FarmHouse>();
        FarmHouse stu = null;
        ResultSet queryRS = null;
        Statement queryStatement = null;
        Connection queryConn = null;
        try{
            queryConn = connectToMySql();
            queryStatement = queryConn.createStatement();
            String sqlQuery = "select * from farmhouse";
            queryRS = queryStatement.executeQuery(sqlQuery);
            while(queryRS.next()) {
                stu = new FarmHouse();
                stu.fhName = queryRS.getString("fhname");
                stu.fhAddr = queryRS.getString("fhaddr");
                stu.fhPername = queryRS.getString("fhpername");
                stu.fhPertel = queryRS.getString("fhpertel");
                stuList.add(stu);
            }
            return stuList;
        }catch(Exception e2) {
            e2.printStackTrace();
            return null;
        }finally{
            try {
                queryRS.close();
                queryStatement.close();
                queryConn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
