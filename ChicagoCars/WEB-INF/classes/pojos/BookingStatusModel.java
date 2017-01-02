package pojos;

public class BookingStatusModel{

public String book_car_id;
public String book_car_pick_date;
public String book_car_pick_time;
public String book_car_drop_date;
public String book_car_drop_time;
public String book_car_user_id;
public String book_car_status;
public String book_car_confirm_num;
public String pickup_date_time;
public String drop_date_time;
public String username;

public BookingStatusModel(){
	
}

public BookingStatusModel(String book_car_id, String book_car_pick_date, String book_car_pick_time, String book_car_drop_date, String book_car_drop_time, String book_car_user_id,
						String book_car_status, String book_car_confirm_num, String pickup_date_time, String drop_date_time, String username){
							
this.book_car_id=book_car_id;
this.book_car_pick_date=book_car_pick_date;
this.book_car_pick_time=book_car_pick_time;
this.book_car_drop_date=book_car_drop_date;
this.book_car_drop_time=book_car_drop_time;
this.book_car_user_id=book_car_user_id;
this.book_car_status=book_car_status;
this.book_car_confirm_num=book_car_confirm_num;
this.pickup_date_time=pickup_date_time;
this.drop_date_time=drop_date_time;
this.username=username;

						}
						
public void setBookCarId(String carId){
	book_car_id=carId;
}
						
public void setBookCarPickDate(String carPickDate){
	book_car_pick_date=carPickDate;
}

public void setBookCarPickTime(String carPickTime){
	book_car_pick_time=carPickTime;
}
public void setBookCarDropDate(String carDropDate){
	book_car_drop_date=carDropDate;
}
						
public void setBookCarDropTime(String carDropTime){
	book_car_drop_time=carDropTime;
}

public void setBookCarUserId(String carUserId){
	book_car_user_id=carUserId;
}

public void setBookCarStatus(String carStatus){
	book_car_status=carStatus;
}
public void setBookCarConfirmNum(String carConfirmNum){
	book_car_confirm_num=carConfirmNum;
}
public void setPickupDateTime(String pickDateTime){
	pickup_date_time=pickDateTime;
}
public void setDropDateTime(String dropDateTime){
	drop_date_time=dropDateTime;
}
public void setUsername(String user){
	username=user;
}




public String getBookCarId(){
	return book_car_id;
}
						
public String getBookCarPickDate(){
	return book_car_pick_date;
}

public String getBookCarPickTime(){
	return book_car_pick_time;
}
public String getBookCarDropDate(){
	return book_car_drop_date;
}
						
public String getBookCarDropTime(){
	return book_car_drop_time;
}

public String getBookCarUserId(){
	return book_car_user_id;
}

public String getBookCarStatus(){
	return book_car_status;
}
public String getBookCarConfirmNum(){
	return book_car_confirm_num;
}
public String getPickupDateTime(){
	return pickup_date_time;
}
public String getDropDateTime(){
	return drop_date_time;
}
public String getUsername(){
	return username;
}

}