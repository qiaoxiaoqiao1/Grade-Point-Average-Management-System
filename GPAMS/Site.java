package GPAMS;

public class Site {
	private int id;
	private int stuNum;
	public Site(int id, int stuNum) {
		this.id = id;
		this.stuNum = stuNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
}
