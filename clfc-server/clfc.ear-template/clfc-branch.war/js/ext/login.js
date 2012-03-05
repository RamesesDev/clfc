$put("login",
	new function() {
		this.data = {};
		
		this.login = function() {
			var svc = ProxyService.lookup('SessionService');
			var user = svc.login(this.data);
			if(user.sessionid != null) {
				var date = new Date();
                date.setTime(date.getTime() + (60 * 1000 * 5));
				$.cookie("sessionid",user.sessionid, { expires: date });
				
				//redirect w/ the first role's objid if specified
				if( user && user.roles )
					window.location = "home.jsp?roleid=" + escape(user.roles[0].objid);
				else
		        	window.location = "home.jsp";
		    }
		}
		
		this.onload = function() {
			var sid = $.cookie("sessionid");
			if(sid!=null) {
		    	window.location = "home.jsp";
		    }
		}
	}
);
