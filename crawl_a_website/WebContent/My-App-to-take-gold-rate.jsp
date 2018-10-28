<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.net.URL"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.LineNumberReader"%>
<%@page import="sun.net.www.URLConnection"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	try {
		URL url = new URL("http://goldratecity.com/gold_price_today.php");
		InputStream is = url.openConnection().getInputStream();
		BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );

		   
	    String line = null;
	    int line_number=0;
	    
	    
	    Calendar col=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String mm_dd_year=sdf.format(col.getTime());
		String[] string_mm_dd_year=mm_dd_year.split("/");
		String date=string_mm_dd_year[0];
		String month=string_mm_dd_year[1];
		String year=string_mm_dd_year[2];
		String today=date+"-"+month+"-"+year;
		
		
		
		Connection connection=null;
		Statement st=null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			 
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			st=connection.createStatement();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	    
	    int count_line=0;
	    int temp_line_count=0;
	    int flag=0;
	    while( ( line = reader.readLine() ) != null )  {
	    	System.out.println(line);
	    		
	    		if(line.contains("Rs.")){
	    			
	    			String te=line.substring(line.indexOf(".")+1, line.indexOf(".")+9);
	    			System.out.println(today+" "+te.trim());
	    			//st.execute("create table gold_rate(date varchar(20),rate varchar(20))");
	    			st.execute("insert into gold_rate(date,rate) values('"+today+"',"+te.trim()+")");
	    			System.out.println("Added");
	    			break;
	    		}
	    	}
	    	   
	    reader.close();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	%>

</body>
</html>