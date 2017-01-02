package pojos;


public class Manage_reservations_model{
    public String pick_up_date;
    public String pick_up_time;
    public String drop_off_date;
    public String drop_off_time;
    public String total_car_rent;
	public String pick_up_location;
    public String confirm_num;
    public String book_status;
    public String car_id;


public void setPick_up_date(String pick_up_date) {
    	this.pick_up_date = pick_up_date;
    }

public String getPick_up_date() {
    	return pick_up_date;
    }

public void setPick_up_time(String pick_up_time) {
	this.pick_up_time = pick_up_time;
}

public String getPick_up_time() {
		return pick_up_time;
	}


public void setDrop_off_date(String drop_off_date) {
	this.drop_off_date = drop_off_date;
}

public String getDrop_off_date() {
		return drop_off_date;
	}

	
public void setDrop_off_time(String drop_off_time) {
	this.drop_off_time = drop_off_time;
}


public String getDrop_off_time() {
		return drop_off_time;
	}


public void setTotal_car_rent(String total_car_rent) {
	this.total_car_rent = total_car_rent;
}

public String getTotal_car_rent() {
		return total_car_rent;
}

public void setPick_up_location(String pick_up_location) {
	this.pick_up_location = pick_up_location;
}


public String getPick_up_location() {
		return pick_up_location;
}

public void setConfirm_num(String confirm_num) {
	this.confirm_num = confirm_num;
}

public String getConfirm_num() {
		return confirm_num;
}

public void setBook_status(String book_status) {
	this.book_status = book_status;
}

public String getBook_status() {
		return book_status;
}

public void setCar_id(String car_id) {
    	this.car_id = car_id;
    }

public String getCar_id() {
    	return car_id;
    }


}
