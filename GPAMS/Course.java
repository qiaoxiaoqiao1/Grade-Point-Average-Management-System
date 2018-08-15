package GPAMS;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private String courseNmae;
	private int siteNum;
	private List<Site> sites;
	private int AssignmentNum,ProjectNum,ExamNum;
	private List<Integer> weight;
	private boolean setWeight;
	public Course(String courseNmae, int siteNum, List<Site> sites, int assignmentNum, 
			int projectNum, int examNum) {
		super();
		this.courseNmae = courseNmae;
		this.siteNum = siteNum;
		this.sites = sites;
		AssignmentNum = assignmentNum;
		ProjectNum = projectNum;
		ExamNum = examNum;
		weight = new ArrayList();
		setWeight = false;
	}
	public Course() {
		this.courseNmae = "";
		this.siteNum = this.AssignmentNum = this.ProjectNum = this.ExamNum = 0;
		this.sites = new ArrayList();
		setWeight = false;
		weight = new ArrayList();
	}
	public boolean isSetWeight() {
		return setWeight;
	}
	public void setSetWeight() {
		this.setWeight = true;
	}
	public List<Integer> getWeight() {
		return weight;
	}
	public void setWeight(List<Integer> weight) {
		this.weight = weight;
	}
	public String getCourseNmae() {
		return courseNmae;
	}
	public void setCourseNmae(String courseNmae) {
		this.courseNmae = courseNmae;
	}
	public int getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(int siteNum) {
		this.siteNum = siteNum;
	}
	public List<Site> getSites() {
		return sites;
	}
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	public int getAssignmentNum() {
		return AssignmentNum;
	}
	public void setAssignmentNum(int assignmentNum) {
		AssignmentNum = assignmentNum;
	}
	public int getProjectNum() {
		return ProjectNum;
	}
	public void setProjectNum(int projectNum) {
		ProjectNum = projectNum;
	}
	public int getExamNum() {
		return ExamNum;
	}
	public void setExamNum(int examNum) {
		ExamNum = examNum;
	}
	
}
