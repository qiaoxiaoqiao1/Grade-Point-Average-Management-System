package GPAMS;

import java.util.ArrayList;
import java.util.List;

public class GPA {
	private Student stu;
	private String courseName;
	private int siteID;
	private List<Integer> HW;
	private float project,aveHW;
	private int midtermScore,finalScore;
	private float totalScore;
	private char grade;
	public GPA(Student stu, String courseName, int siteID, List<Integer> hW, float project, 
			int midtermScore,int finalScore) {
		super();
		this.stu = stu;
		this.courseName = courseName;
		this.siteID = siteID;
		HW = hW;
		this.project = project;
		this.midtermScore = midtermScore;
		this.finalScore = finalScore;
		this.grade = 0;			
	}
	
	public GPA() {
		this.stu = null;
		this.courseName = "";
		this.siteID = this.midtermScore = this.finalScore = 0;
		this.project = this.totalScore = 0;
		this.HW = new ArrayList();
	}

	
	public float computeHWScore(List<Integer> weight) {
		float hwScore = 0;
		for(int i = 0;i < HW.size();i ++) {
			hwScore += HW.get(i);
		}
		aveHW = hwScore / HW.size();
		return aveHW;
	}

	public void computeTS(List<Integer> weight) {
		totalScore = (float) ((float)(finalScore * weight.get(0) + midtermScore * weight.get(1) + project * weight.get(2) * 100 /20 + aveHW * weight.get(3) * 100 / 10)/100.0);
		this.grade = totalScore >= 90 ? 'A' : totalScore >= 80 ? 'B' : totalScore >= 70 ? 'C' : 'E';
	}
	
	public float getAveHW() {
		return aveHW;
	}

	public void setAveHW(float aveHW) {
		this.aveHW = aveHW;
	}

	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getSiteID() {
		return siteID;
	}
	public void setSiteID(int siteID) {
		this.siteID = siteID;
	}
	public List<Integer> getHW() {
		return HW;
	}
	public void setHW(List<Integer> hW) {
		HW = hW;
	}
	public float getProject() {
		return project;
	}
	public void setProject(float project) {
		this.project = project;
	}
	public int getMidtermScore() {
		return midtermScore;
	}
	public void setMidtermScore(int midtermScore) {
		this.midtermScore = midtermScore;
	}
	public int getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}
	public float gettotalScore() {
		return totalScore;
	}
	public void settotalScore(float grade) {
		this.totalScore = totalScore;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}
	
}
