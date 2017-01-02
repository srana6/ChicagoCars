package pojos;

import java.sql.*;
import java.util.*;

import dboperations.CreateDBEntities;
import utilities.Request;

public class CarsUnavailable {

	public static Map<String, Integer[]> m = new HashMap<String, Integer[]>();
    public static List<String> cun = new ArrayList<String>();
	
	public static void loadHashMap(String action){
        ResultSet rs;
        CreateDBEntities cd = new CreateDBEntities();
        try{
            if(action.equals("load")){
                rs = cd.getAllBookedCars(Request.pickdate, Request.dropdate);
                while(rs.next()){
                    setSlot(m, rs.getString(1), Integer.valueOf(rs.getString(3).split(":")[0]), Integer.valueOf(rs.getString(5).split(":")[0]));
                }
            }else{
                rs = cd.getAllBookedCars(Request.pickdate, Request.dropdate);
                while(rs.next()){
                    cun.add(setSlot(m, rs.getString(1), Integer.valueOf(Request.picktime.split(":")[0]), Integer.valueOf(Request.droptime.split(":")[0])));
                }
                /*
                for(String s:cun){
                   System.out.println(s); 
                }
                */
            }
        }catch(Exception e){
            e.printStackTrace();
        }
		//System.out.println(setSlot(m, key, 20, 23));
	}
	
	public static String setSlot(Map<String, Integer[]> m, String k, int s, int e){
		int set = 1;
		Integer [] iarr;
		if(m.get(k)==null)
			iarr = new Integer[24];
		else{
			iarr = m.get(k);
			//System.out.println(iarr);
		}
		for(int i=0;i<iarr.length;i++){
			if(iarr[i]==null ||iarr[i]!=1)
				iarr[i]=0;
		}
		int i = s;
		int me = e;
		if(e<=i)
			me = 24+e;
		while(i<me){
			//System.out.println(iarr[i]);
			if(i>=24){
				i = i - 24;
				me = e;
			}
			if(iarr[i]==1){
				set = 0;
				break;
			}
			iarr[i]=1;
			i++;
		}
		if(set == 1){
			m.put(k,iarr);
			return "NULL";
		}
		return k;
	}
}