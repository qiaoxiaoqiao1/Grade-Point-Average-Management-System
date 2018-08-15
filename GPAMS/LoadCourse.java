package GPAMS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadCourse {

	private ArrayList<Course> courses;
	private int size;
	private final String defaultPath = "E:\\Course.txt";
	private boolean defaultUsed;
	
	public LoadCourse() {
		courses = new ArrayList();
		defaultUsed = false;
		size = 0;
	}
	
	//load course information from menu
	public void addNewCourse() {
		Scanner scanner = new Scanner(System.in);
		Course course = new Course();
		course.setCourseNmae(scanner.next());
		course.setSiteNum(scanner.nextInt());
		for(int j = 0;j < course.getSiteNum();j ++) {
			course.getSites().add(new Site(j,scanner.nextInt()));
		}
		course.setAssignmentNum(scanner.nextInt());
		course.setProjectNum(scanner.nextInt());
		course.setExamNum(scanner.nextInt());
		courses.add(course);
		size ++;
	}
	
	public void addNewCourse(String courseName,int numSites,List<Integer> stu,int assignment,int pro,int exam) {
		Course course = new Course();
		course.setCourseNmae(courseName);
		course.setSiteNum(numSites);
		for(int j = 0;j < stu.size();j ++) {
			course.getSites().add(new Site(j,stu.get(j)));
		}
		course.setAssignmentNum(assignment);
		course.setProjectNum(pro);
		course.setExamNum(exam);
		courses.add(course);
		size ++;
	}
	
	public boolean loadFile() {
		if(this.defaultUsed) {
			System.out.println("Can't load the default file more than one times!");
			return false;
		}
		try {
			Scanner scanner = new Scanner(new FileInputStream(defaultPath));
			scanner.nextLine();
			while(scanner.hasNext()) {
				Course course = new Course();
				course.setCourseNmae(scanner.next());
				course.setSiteNum(scanner.nextInt());
				for(int j = 0;j < course.getSiteNum();j ++) {
					course.getSites().add(new Site(j + 1,scanner.nextInt()));
				}
				course.setAssignmentNum(scanner.nextInt());
				course.setProjectNum(scanner.nextInt());
				course.setExamNum(scanner.nextInt());
				courses.add(course);
				size ++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.defaultUsed = true;
		return true;
	}
	
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public Course get(int index) {
		return courses.get(index);
	}
	
	public static void main(String[] args) {
		LoadCourse loadCourse = new LoadCourse();
		loadCourse.loadFile();
		loadCourse.addNewCourse();
		for(int i = 0;i < loadCourse.getSize();i ++) {
			Course course = loadCourse.getCourses().get(i);
			System.out.print(course.getCourseNmae() + " " + course.getSiteNum() + " ");
			for(int j = 0;j < course.getSiteNum();j ++) {
				System.out.print(course.getSites().get(j).getStuNum() + " ");
			}
			System.out.println(course.getAssignmentNum() + " " + course.getProjectNum() + " " + course.getExamNum());
		}
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	

}
