package model;

public class HolidayVO {
	private int locdate;//날짜
	private String dateKind;//종류
	private String isHoliday;//공공기관 휴일여부
	private String dateName;//명칭
	private String memo;//메모
	
	public int getLocdate() {
		return locdate;
	}
	public void setLocdate(int locdate) {
		this.locdate = locdate;
	}

	public String getDateKind() {
		return dateKind;
	}
	public void setDateKind(String dateKind) {
		this.dateKind = dateKind;
	}
	public String getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(String isHoliday) {
		this.isHoliday = isHoliday;
	}
	public String getDateName() {
		return dateName;
	}
	public void setDateName(String dateName) {
		this.dateName = dateName;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "[" + locdate + ", " + dateKind + ", " + isHoliday
				+ ", " + dateName +", "+memo+ "]";
	}
	
	

}
