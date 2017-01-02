<div name = "autofillform" >
	
	<br/>
	<form action='ProcessLocation' method='POST'> 
	<p><label for='pickLocation'>Pick up location :</label>
	<input type = "text" class="tag" name = "pickLocation" class="input" id = "pickLocation"  style = "padding;font-size:16px;" placeholder="Enter a city name" />
	</p> 
	
	<p><label for='dropLocation'>Drop location :</label>
	<input type = "text" class="tag" name = "dropLocation" class="input" id = "dropLocation"  style = "padding;font-size:16px;"  placeholder="Enter a city name"  />
	</p> 
	
	<p><label for='pickDate'>Pick up date :</label>
	<input type = "date" class="tag" name = "pickDate" class="input" id = "pickDate"  style = "padding;font-size:16px;"  />
	</p>
	
	<p><label for='pickTime'>Pick up Time :</label>
	<input type = "time" class="tag" name = "pickTime" class="input" id = "pickTime" step="3600"  style = "padding;font-size:16px;"  />
	</p>
	
	<p><label for='dropDate'>Drop date :</label>
	<input type = "date" class="tag" name = "dropDate" class="input" id = "dropDate"  style = "padding;font-size:16px;"  />
	</p> 
	
	
	<p><label for='dropTime'>Drop Time :</label>
	<input type = "time" class="tag" name = "dropTime" class="input" id = "dropTime" step="3600" style = "padding;font-size:16px;"  />
	</p>
	
	<br/>
	 <p><input name='send' style='margin-left: 150px;' class='formbutton' value='Send' type='submit' /></p> 
	</form>
</div>
