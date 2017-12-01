package company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeReported {

	private String personnelID;
	private String name;
	private Date date;
	private Date inTime;
	private Date outTime;
	private float salary;
	private DatabaseConnector database;
	private float salaryPerHour;
	
	public TimeReported(String id,String inName,String inDate,String inTime,String outTime,float inSalary){
		personnelID = id;
		name = inName;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			setDate(sdf.parse(inDate));
			sdf = new SimpleDateFormat("HH:mm");
			this.setInTime(sdf.parse(inTime));
			this.setOutTime(sdf.parse(outTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setSalary(inSalary);
		salaryPerHour = getSalary()/6;
		calHour();
	}
	
	private void calHour(){
		TimeSystem tsystem = new TimeSystem();
		Date[] clockin_out = tsystem.getClockin_out();;
		int checkIn = inTime.getHours();
		if(inTime.getMinutes()>=45){
			checkIn++;
		}
		if(checkIn > clockin_out[0].getHours()){
			setSalary(getSalary() - (checkIn - clockin_out[0].getHours())*salaryPerHour);
		}
		int checkOut = outTime.getHours();
		if(outTime.getMinutes()>=45){
			checkOut++;
		}
		if(checkOut > clockin_out[1].getHours()){
			setSalary(getSalary() + (checkOut - clockin_out[1].getHours())*salaryPerHour);
		}
		
	}

	public String getID() {
		return personnelID;
	}
	
	public String getName() {
		return name;
	}

	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(inTime);
	}

	private void setDate(Date date) {
		this.date = date;
	}

	public String getInTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(inTime);
	}

	private void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(outTime);
	}

	private void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public float getSalary() {
		return salary;
	}

	private void setSalary(float salary) {
		this.salary = salary;
	}
}
