<script type="text/javascript">
	$put(
		'useraccount',
		new function() {
			
			this.logout = function() {
				$ctx('session').logout();
			}
			
		}
	);
</script>

<div style="padding:1px;font-size:12px;"> 
	<a context="useraccount">Edit Profile</a><br/>
	<a context="useraccount">Change Password</a><br/>
	<hr>
	<a context="useraccount" name="logout">Logout</a>
</div>

