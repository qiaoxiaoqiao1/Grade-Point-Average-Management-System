package GPAMS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LoadGPA {
	private ArrayList<GPA> GPAInfo;
	private LoadCourse courseInfo;
	private int size;
	private final String defaultPath = "E:\\grades.txt";
	private boolean defaultUsed;
	
	public LoadGPA(LoadCourse courseInfo) {
		GPAInfo = new ArrayList();
		this.size = 0;
		this.defaultUsed = false;
		this.courseInfo = courseInfo;
	}
	
	public LoadCourse getCourseInfo() {
		return courseInfo;
	}
	
	public void LoadFile(String fileName) {
		try {
			Scanner scanner = new Scanner(new FileInputStream(fileName));
			scanner.nextLine();
			while(scanner.hasNext()) {
				GPA score = new GPA();
				score.setStu(new Student(scanner.next(),scanner.next(),scanner.next()));
				score.setCourseName(scanner.next());
				score.setSiteID(scanner.nextInt());
				int i = 0;
				for(;i < courseInfo.getSize();i ++) {
					if(courseInfo.get(i).getCourseNmae().equals(score.getCourseName())) {
						break;
					}
				}
				if(i == courseInfo.getSize()) {
					throw new GPAMSException("No such a class named " + score.getCourseName());
				}
				ArrayList<Integer> hw = new ArrayList();
				for(int j = 0;j < courseInfo.get(i).getAssignmentNum();j++) {
					hw.add(scanner.nextInt());
				}
				score.setHW(hw);
				score.setProject(scanner.nextFloat());
				score.setMidtermScore(scanner.nextInt());
				score.setFinalScore(scanner.nextInt());
				GPAInfo.add(score);
				size ++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean LoadFile() {
		if(this.defaultUsed) {
			System.out.println("Can't load the default file more than one times!");
			return false;
		}
		try {
			Scanner scanner = new Scanner(new FileInputStream(defaultPath));
			scanner.nextLine();
			while(scanner.hasNext()) {
				GPA score = new GPA();
				score.setStu(new Student(scanner.next(),scanner.next(),scanner.next()));
				score.setCourseName(scanner.next());
				score.setSiteID(scanner.nextInt());
				int i = 0;
				for(;i < courseInfo.getSize();i ++) {
					if(courseInfo.get(i).getCourseNmae().equals(score.getCourseName())) {
						break;
					}
				}
				if(i == courseInfo.getSize()) {
					throw new GPAMSException("No such a class named " + score.getCourseName());
				}
				ArrayList<Integer> hw = new ArrayList();
				for(int j = 0;j < courseInfo.get(i).getAssignmentNum();j++) {
					hw.add(scanner.nextInt());
				}
				score.setHW(hw);
				score.setProject(scanner.nextFloat());
				score.setMidtermScore(scanner.nextInt());
				score.setFinalScore(scanner.nextInt());
				GPAInfo.add(score);
				size ++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.defaultUsed = true;
		return true;
	}
	
	private void compute() {
		List<Integer> weight = null;
		for(int i = 0;i < GPAInfo.size();i ++) {
			for(int j = 0;j < courseInfo.getSize();j ++) {
				if(GPAInfo.get(i).getCourseName().equals(courseInfo.get(j).getCourseNmae())) {
					weight = courseInfo.get(j).getWeight();
				}
			}
			if(weight.size() == 0) {
				continue;
			}
			GPAInfo.get(i).computeHWScore(weight);
			GPAInfo.get(i).computeTS(weight);
			
		}
	}
	
	public void addNewGPA() {
		Scanner scanner = new Scanner(System.in);
		GPA score = new GPA();
		score.setStu(new Student(scanner.next(),scanner.next(),scanner.next()));
		score.setCourseName(scanner.next());
		score.setSiteID(scanner.nextInt());
		int i = 0;
		for(;i < courseInfo.getSize();i ++) {
			if(courseInfo.get(i).getCourseNmae().equals(score.getCourseName())) {
				break;
			}
		}
		if(i == courseInfo.getSize()) {
			throw new GPAMSException("No such a class named " + score.getCourseName());
		}
		ArrayList<Integer> hw = new ArrayList();
		for(int j = 0;j < courseInfo.get(i).getAssignmentNum();j++) {
			hw.add(scanner.nextInt());
		}
		score.setHW(hw);
		score.setProject(scanner.nextFloat());
		score.setMidtermScore(scanner.nextInt());
		score.setFinalScore(scanner.nextInt());
		GPAInfo.add(score);
		size ++;
	}
	
	public void addNewGPA(String CName,String FName,String LName,String SID,int SiteID,List<Integer> hw,
			float pro,int mid,int finalScore) {
		GPA score = new GPA();
		score.setStu(new Student(FName,LName,SID));
		score.setCourseName(CName);
		score.setSiteID(SiteID);
		int i = 0;
		for(;i < courseInfo.getSize();i ++) {
			if(courseInfo.get(i).getCourseNmae().equals(score.getCourseName())) {
				break;
			}
		}
		if(i == courseInfo.getSize()) {
			throw new GPAMSException("No such a class named " + score.getCourseName());
		}
		score.setHW(hw);
		score.setProject(pro);
		score.setMidtermScore(mid);
		score.setFinalScore(finalScore);
		GPAInfo.add(score);
		size ++;
		compute();
	}
	
	public boolean removeGPA(String SID,String className,int site) {
		for(int i = 0;i < size;i ++) {
			GPA gpa = GPAInfo.get(i);
			if(gpa.getStu().getSID().equals(SID) && gpa.getCourseName().equals(className) && gpa.getSiteID() == site){
				GPAInfo.remove(i);
				size --;
				return true;
			}
		}
		return false;
	}
	
	public boolean changeGPA(String SID,String className,int site,int finalScore,int mid,float pro,List<Integer> hw) {
		for(int i = 0;i < size;i ++) {
			GPA gpa = GPAInfo.get(i);
			if(gpa.getStu().getSID().equals(SID) && gpa.getCourseName().equals(className) && gpa.getSiteID() == site){
				gpa.setFinalScore(finalScore);
				gpa.setMidtermScore(mid);
				gpa.setProject(pro);
				gpa.setHW(hw);
				return true;
			}
		}
		return false;
	}
	
	public int getSize() {
		return size;
	}

	public GPA get(int index) {
		return GPAInfo.get(index);
	}
	
	public List<GPA> searchByFirstName(String fName){
		ArrayList<GPA> gpaInfo = new ArrayList();
		for(int i = 0;i < size;i ++) {
			if(GPAInfo.get(i).getStu().getFName().equals(fName)) {
				gpaInfo.add(GPAInfo.get(i));
			}
		}
		Collections.sort(gpaInfo,new Comparator<GPA>() {

			@Override
			public int compare(GPA arg0, GPA arg1) {
				return arg0.getStu().getLName().compareTo(arg1.getStu().getLName());
			}
			
		});
		return gpaInfo;
	}
	
	public List<GPA> searchByLastName(String lName){
		ArrayList<GPA> gpaInfo = new ArrayList();
		for(int i = 0;i < size;i ++) {
			if(GPAInfo.get(i).getStu().getLName().equals(lName)) {
				gpaInfo.add(GPAInfo.get(i));
			}
		}
		
		Collections.sort(gpaInfo,new Comparator<GPA>() {

			@Override
			public int compare(GPA arg0, GPA arg1) {
				return arg0.getStu().getFName().compareTo(arg1.getStu().getFName());
			}
			
		});
		return gpaInfo;
	}
	
	public List<GPA> searchBySID(String SID){
		ArrayList<GPA> gpaInfo = new ArrayList();
		for(int i = 0;i < size;i ++) {
			if(GPAInfo.get(i).getStu().getSID().equals(SID)) {
				gpaInfo.add(GPAInfo.get(i));
			}
		}
		return gpaInfo;
	}
	
	public List<GPA> travel(int position,String direction){
		ArrayList<GPA> gpaInfo = new ArrayList();
		if(direction.equals("+")) {
			for(int i = position;i < size;i ++) {
				gpaInfo.add(GPAInfo.get(i));
			}
		}
		else {
			for(int i = position;i >= 0;i --) {
				gpaInfo.add(GPAInfo.get(i));
			}
		}
		return gpaInfo;
	}
	
	public boolean scoreWeight(String courseName,List<Integer> weight) {
		Course course = null;
		for(int i = 0;i < courseInfo.getSize();i ++) {
			if(courseInfo.get(i).getCourseNmae().equals(courseName)) {
				course = courseInfo.get(i);
				break;
			}
		}
		
		if(course == null) {
			throw new GPAMSException("No such a course named " + courseName);
		}
		
		if(course.isSetWeight()) {
			System.out.println("Can't socre weight more than one time!");
			return false;
		}
		course.setSetWeight();
		course.setWeight(weight);
		compute();
		return true;
	}
	
	
	public List<GPA> sortByLastName(String CName){
		ArrayList<GPA> gpaInfo = new ArrayList();
		for(int i = 0;i < size;i ++) {
			if(GPAInfo.get(i).getCourseName().equals(CName))
				gpaInfo.add(GPAInfo.get(i));
		}
		Collections.sort(gpaInfo,new Comparator<GPA>() {

			@Override
			public int compare(GPA o1, GPA o2) {
				return o1.getStu().getLName().compareTo(o2.getStu().getLName());
			}
			
		});
		return gpaInfo;
	}
	
	public List<GPA> sortByTotalScore(String CName){
		//compute();
		ArrayList<GPA> gpaInfo = new ArrayList();
		for(int i = 0;i < size;i ++) {
			if(GPAInfo.get(i).getCourseName().equals(CName))
				gpaInfo.add(GPAInfo.get(i));
		}
		Collections.sort(gpaInfo,new Comparator<GPA>() {

			@Override
			public int compare(GPA o1, GPA o2) {
				return (o2.gettotalScore() > o1.gettotalScore()) ? 1 : -1;
			}
			
		});
		return gpaInfo;
	}
	
	public List<GPA> sortByHW(String CName){
		//compute();
		ArrayList<GPA> gpaInfo = new ArrayList();
		for(int i = 0;i < size;i ++) {
			
			if(GPAInfo.get(i).getCourseName().equals(CName))
				gpaInfo.add(GPAInfo.get(i));
		}
		Collections.sort(gpaInfo,new Comparator<GPA>() {

			@Override
			public int compare(GPA o1, GPA o2) {
				return (o2.getAveHW() > o1.getAveHW()) ? 1 : -1;
			}
			
		});
		return gpaInfo;
	}
	
	public List<GPA> sortByProjectScore(String CName){
		ArrayList<GPA> gpaInfo = new ArrayList();
		for(int i = 0;i < size;i ++) {
			if(GPAInfo.get(i).getCourseName().equals(CName))
				gpaInfo.add(GPAInfo.get(i));
		}
		Collections.sort(gpaInfo,new Comparator<GPA>() {

			@Override
			public int compare(GPA o1, GPA o2) {
				return (o2.getProject() > o1.getProject()) ? 1 : -1;
			}
			
		});
		return gpaInfo;
	}
	
	public List<GPA> sortByGrade(String CName){
		//compute();
		ArrayList<GPA> gpaInfo = new ArrayList();
		for(int i = 0;i < size;i ++) {
			if(GPAInfo.get(i).getCourseName().equals(CName))
				gpaInfo.add(GPAInfo.get(i));
		}
		Collections.sort(gpaInfo,new Comparator<GPA>() {

			@Override
			public int compare(GPA o1, GPA o2) {
				return o1.getGrade() - o2.getGrade(); 
			}
			
		});
		return gpaInfo;
	}
	
	public Map<String,List<Float>> average(String courseName){
		Map<String,List<Float>> res = new LinkedHashMap();
		//List<List<Float>> res = new ArrayList();
		List<GPA> list = new ArrayList();
		//compute();
		Course course = null;
		for(int i = 0;i < courseInfo.getSize();i ++) {
			if(courseInfo.get(i).getCourseNmae().equals(courseName)) {
				course = courseInfo.get(i);
				break;
			}
		}
		
		if(course == null) {
			throw new GPAMSException("No such a course named " + courseName);
		}
		
		for(int i = 0;i < size;i ++) {
			if(GPAInfo.get(i).getCourseName().equals(courseName)) {
				list.add(GPAInfo.get(i));
			}
		}
		if(list.size() == 0) {
			throw new GPAMSException("No GPA information of the course named " + courseName);
		}
		
		//compute the average score of the whole class
		List<Float> StuAverScore = new ArrayList();
		GPA gpa = list.get(0);
		float aveHW = gpa.getAveHW();
		float avePro = gpa.getProject();
		float aveMid = gpa.getMidtermScore();
		float aveFinal = gpa.getFinalScore();
		float aveTotal = gpa.gettotalScore();
		//List<Float> otherItem = new ArrayList();
		
		int len = list.size();
		for(int i = 1;i < len;i ++) {
			gpa = list.get(i);
			aveHW += gpa.getAveHW();
			avePro += gpa.getProject();
			aveMid += gpa.getMidtermScore();
			aveFinal += gpa.getFinalScore();
			aveTotal += gpa.gettotalScore();
		}
		aveHW /= len;
		avePro /= len;
		aveMid /= len;
		aveFinal /= len;
		aveTotal /= len;
		StuAverScore.add(aveHW);
		StuAverScore.add(avePro);
		StuAverScore.add(aveMid);
		StuAverScore.add(aveFinal);
		StuAverScore.add(aveTotal);
		//res.add(StuAverScore);
		res.put("Class", StuAverScore);
		
		//compute the average score of each site
		for(int i = 0;i < course.getSiteNum();i ++) {
			aveHW = 0;
			avePro = 0;
			aveMid = 0;
			aveFinal = 0;
			aveTotal = 0;
			len = 0;
			for(int j = 0;j < list.size();j ++) {
				StuAverScore = new ArrayList();
				if(course.getSites().get(i).getId() == list.get(j).getSiteID()) {
					gpa = list.get(j);
					aveHW += gpa.getAveHW();
					avePro += gpa.getProject();
					aveMid += gpa.getMidtermScore();
					aveFinal += gpa.getFinalScore();
					aveTotal += gpa.gettotalScore();
					len ++;
				}
			}
			
			aveHW /= len;
			avePro /= len;
			aveMid /= len;
			aveFinal /= len;
			aveTotal /= len;
			StuAverScore.add(aveHW);
			StuAverScore.add(avePro);
			StuAverScore.add(aveMid);
			StuAverScore.add(aveFinal);
			StuAverScore.add(aveTotal);
			//res.add(StuAverScore);
			res.put("Site " + course.getSites().get(i).getId(), StuAverScore);
		}
		
		return res;
	}
	
	public List<Double> percentageOfGrade(String courseName){
		//compute();
		int aNum = 0, bNum = 0, cNum = 0, dNum = 0;
		ArrayList<Double> res = new ArrayList();
		
		for(int i = 0;i < size;i ++) {
			if(courseName.equals(GPAInfo.get(i).getCourseName())) {
				switch(GPAInfo.get(i).getGrade()) {
				case 'A':
					aNum ++;
					break;
				case 'B':
					bNum ++;
					break;
				case 'C':
					cNum ++;
					break;
				case 'D':
					dNum ++;
					break;
				}
			}			
		}
		
		res.add((aNum * 1.0)/size * 100);
		res.add((bNum * 1.0)/size * 100);
		res.add((cNum * 1.0)/size * 100);
		res.add((dNum * 1.0)/size * 100);
		
		return res;
	}
	
	public static void main(String[] argv) {
		LoadCourse loadCourse = new LoadCourse();
		loadCourse.loadFile();
		LoadGPA loadGPA = new LoadGPA(loadCourse);
		loadGPA.LoadFile();
		loadGPA.addNewGPA();
		for(int i = 0;i < loadGPA.getSize();i ++) {
			GPA gpa = loadGPA.get(i);
			System.out.print(gpa.getStu().getFName() + " " + gpa.getStu().getLName() + " " + gpa.getStu().getSID() + " "
					+ gpa.getCourseName() + " " + gpa.getSiteID() + " " );
			for(int j = 0;j < gpa.getHW().size();j ++) {
				System.out.print(gpa.getHW().get(j) + " ");
			}
			System.out.println(gpa.getProject() + " " + gpa.getMidtermScore() + " " + gpa.getFinalScore());
		}
	}
}
