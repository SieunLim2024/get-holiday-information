package model;

public class HolidayVO {
	private int locdate;//��¥
	private String dateKind;//����
	private String isHoliday;//������� ���Ͽ���
	private String dateName;//��Ī
	private String memo;//�޸�
	
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
