$(document).ready(function() {
	$(function() {
		$("#pickLocation").prop('required',true);
		$("#dropLocation").prop('required',true);
		$("#pickDate").prop('required',true);
		$("#pickTime").prop('required',true);
		$("#dropDate").prop('required',true);
		$("#dropTime").prop('required',true);
		
		$("#pickLocation").autocomplete({   
		source : function(request, response) {
		$.ajax({
				url : "autocomplete",
				type : "GET",
				data : {
						searchId : pickLocation.value.toLowerCase(),
						action : "complete"
						
				},
				dataType : "json",
				success : function(data) {
						response(data);
					}
				});
		}
		});
	});

	$(function() {
		$("#dropLocation").autocomplete({   
		source : function(request, response) {
		$.ajax({
				url : "autocomplete",
				type : "GET",
				data : {
						searchId : dropLocation.value.toLowerCase(),
						action : "complete"
						
				},
				dataType : "json",
				success : function(data) {
						response(data);
					}
				});
		}
		});
	});
	
	$('form').on('submit', function (e) {
	var startDate = new Date($('#pickDate').val());
	var endDate = new Date($('#dropDate').val());

	if (startDate > endDate){
		$("#dropDate").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter a pick up date earlier than drop off date</div>");
		e.preventDefault();
	}
	});
	
	$("#pickLocation").keypress(function(event){
        var inputValue = event.which;
        // allow letters and whitespaces only.
        if(!(inputValue >= 65 && inputValue <= 120) && (inputValue != 32 && inputValue != 0)) { 
			$("#pickLocation").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter a location name in letters</div>");
            event.preventDefault(); 
        }
    });
	
	$("#dropLocation").keypress(function(event){
        var inputValue = event.which;
        // allow letters and whitespaces only.
        if(!(inputValue >= 65 && inputValue <= 120) && (inputValue != 32 && inputValue != 0)) { 
			$("#dropLocation").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please enter a location name in letters</div>");
            event.preventDefault(); 
        }
    });
	
	
	
	
	
});

