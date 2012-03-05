<%@ page import="com.rameses.util.*" %>
<%@ page import="java.io.*" %>

<%
	
	String branchcode = request.getParameter("b");
	String appid = request.getParameter("a");
	String objid = request.getParameter("id");
	
	if( branchcode == null ) branchcode = "";
	if( appid == null ) appid = "";
	if( objid == null ) objid = "";
	
	String filename = branchcode + "/" + appid.hashCode() + "/" + objid.hashCode();
	InputStream is = null;
	OutputStream os = null;
	
	try {
		is = application.getResourceAsStream( filename );
		os = response.getOutputStream();
		response.setContentType( application.getMimeType(filename) );
		int i = -1;
		while( (i = is.read()) != -1 ) {
			os.write( i );
		}
		os.flush();
	}
	catch(Exception e) {
		out.write( e.getMessage() );
	}
	finally {
		if( is != null ) try { is.close(); }catch(Exception e){;}
		if( os != null ) try { os.close(); }catch(Exception e){;}
	}
	
%>
