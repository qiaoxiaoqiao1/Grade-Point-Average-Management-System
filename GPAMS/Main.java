package GPAMS;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main {

	private static LoadGPA GPAInfo;
	
	private static void printMainmenu() {
		System.out.println("IIT CS01 GPAMS Main menu");
		System.out.println("    1. Reoster Creation");
		System.out.println("    2. Grade input");
		System.out.println("    3. Record Search");
		System.out.println("    4. Show list");
		System.out.println("    5. Change record");
		System.out.println("    6. Statistics");
		System.out.println("    7. Exit");
		System.out.println();
		System.out.print("Select: ");
	}
	
	private static void menu1(Scanner sc) {
		DecimalFormat df=new DecimalFormat("00");
		System.out.println("1 Manual Data Feed");
		System.out.println("2 Default");
		System.out.print("    Select: ");
		int n = sc.nextInt();
		System.out.println();
		if(n == 1) {
			System.out.println("***Manual Data Input***");
			System.out.print("Class Name? ");
			String name = sc.next();
			System.out.print("Numbers of site? ");
			int numSites = sc.nextInt();
			System.out.println();
			System.out.println();
			List<Integer> stu = new ArrayList<>();
			for(int i = 0;i < numSites;i ++) {
				System.out.print("Numbers of student per site " + df.format(i) + " ? ");
				stu.add(sc.nextInt());
			}
			
			System.out.println();
			
			//compute total class size
			int sum = 0;
			for(int s : stu) {
				sum += s;
			}
			System.out.println("Total class size:" + sum);
			
			System.out.println();
			System.out.print("Numbers of Assignment? ");
			int assignment = sc.nextInt();
			System.out.print("Numbers of Project? ");
			int pro = sc.nextInt();
			System.out.print("Numbers of Exam? ");
			int exam = sc.nextInt();
			GPAInfo.getCourseInfo().addNewCourse(name,numSites,stu,assignment,pro,exam);
				
		}
		else {
			GPAInfo.getCourseInfo().loadFile();
		}
	}
	
	private static void menu2(Scanner sc) {
		System.out.println("1 Load Default");
		System.out.println("2 Load File");
		System.out.println();
		System.out.print("Select:  ");
		int n = sc.nextInt();
		if(n == 2) {
			System.out.print("File path: ");
			String path = sc.next();
			GPAInfo.LoadFile(path);
		}
		else {
			GPAInfo.LoadFile();
		}
	}
	
	private static void menu3(Scanner sc) {
		System.out.println("Menu3");
		System.out.println("1. Search by first name");
		System.out.println("2. Search by last name");
		System.out.println("3. Search by SID");
		System.out.println("4. Traveling list");
		System.out.println("5: Main menu");
		System.out.println();
		System.out.print("Select: ");
		int n = sc.nextInt();
		String name;
		switch (n) {
		case 1:
			System.out.print("First name: ");
			name = sc.next();
			menu3Print(GPAInfo.searchByFirstName(name));
			break;
		case 2:
			System.out.print("Last name: ");
			name = sc.next();
			menu3Print(GPAInfo.searchByLastName(name));
			break;
		case 3:
			System.out.print("SID: ");
			name = sc.next();
			menu3Print(GPAInfo.searchBySID(name));
			break;
		case 4:
			System.out.print("menu 3.4 where to travel? ");
			int p = sc.nextInt();
			name = sc.next();
			menu3Print(GPAInfo.travel(p, name));
			break;
		default:
			break;
		}
	}
	
	private static void menu3Print(List<GPA> gpaInfo) {
		System.out.printf("%-20s%-20s%-20s%-20s%-20s\n","Course Name","Site Number","First Name","Last Name","Grade");
		for(GPA gpa:gpaInfo) {
			System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",gpa.getCourseName(),gpa.getSiteID(),gpa.getStu().getFName(),gpa.getStu().getLName(),gpa.getGrade());
		}
	}
	
	private static void menu4(Scanner sc) {
		System.out.println("0. Scoring Weight per Item");
		System.out.println("1. Sorted list by last name");
		System.out.println("2. Rankings by total score");
		System.out.println("3. Ranking by homework average");
		System.out.println("4. Ranking by project score");
		System.out.println("5.Rankings by grade");
		System.out.println("6. Main Menu");
		System.out.println();
		System.out.print("Select: ");
		int n = sc.nextInt();
		String name;
		switch (n) {
		case 0:
			System.out.print("Class Name: ");
			name = sc.next();
			System.out.print("Final Exam:      ");
			int fExam = sc.nextInt();
			System.out.print("Midterm Exam:    ");
			int mExam = sc.nextInt();
			System.out.print("Project:         ");
			int pro = sc.nextInt();
			System.out.print("Homework:        ");
			int hw = sc.nextInt();
			System.out.println();
			while(fExam + mExam + pro + hw != 100) {
				System.out.println("The total weight must be 100!");
				System.out.print("Final Exam:      ");
				fExam = sc.nextInt();
				System.out.print("Midterm Exam:    ");
				mExam = sc.nextInt();
				System.out.print("Project:         ");
				pro = sc.nextInt();
				System.out.print("Homework:        ");
				hw = sc.nextInt();
			}
			System.out.println("Total           " + (fExam + mExam + pro + hw));
			List<Integer> weight = new ArrayList<>();
			weight.add(fExam);
			weight.add(mExam);
			weight.add(pro);
			weight.add(hw);
			GPAInfo.scoreWeight(name, weight);
			break;
		case 1:
			System.out.print("Class name: ");
			name = sc.next();
			menu4PrintfullInfo(GPAInfo.sortByLastName(name));
			break;
		case 2:
			System.out.print("Class name: ");
			name = sc.next();
			menu4PrintInfo(n,GPAInfo.sortByTotalScore(name));
			break;
		case 3:
			System.out.print("Class name: ");
			name = sc.next();
			menu4PrintInfo(n,GPAInfo.sortByHW(name));
			break;
		case 4:
			System.out.print("Class name: ");
			name = sc.next();
			menu4PrintInfo(n,GPAInfo.sortByProjectScore(name));
			break;
		case 5:
			System.out.print("Class name: ");
			name = sc.next();
			menu4PrintInfo(n,GPAInfo.sortByGrade(name));
			break;
		default:
			break;
		}
	}
	
	private static void menu4PrintfullInfo(List<GPA> gpaInfo) {
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n","Last Name","First Name","SID","Site","Howework Average","Project Score","Midterm Score","Final Score","Total Score","Grade");
		for(GPA gpa:gpaInfo) {
			System.out.printf("%-20s%-20s%-20s%-20d%-20.2f%-20.2f%-20d%-20d%-20.2f%-20c\n",gpa.getStu().getFName(),gpa.getStu().getLName(),gpa.getStu().getSID(),
					gpa.getSiteID(),gpa.getAveHW(),gpa.getProject(),gpa.getMidtermScore(),gpa.getFinalScore(),gpa.gettotalScore(),gpa.getGrade());
		}
	}
	
	private static void menu4PrintInfo(int type,List<GPA> gpaInfo) {
		System.out.printf("%-20s%-20s%-20s%-20s\n","Last Name","First Name","Site",(type == 2 ? "Total Score" : (type == 3 ? "Homework Average" :(type == 4 ? "Project Score" : "Garde"))));
		for(GPA gpa:gpaInfo) {
			System.out.printf("%-20s%-20s%-20d",gpa.getStu().getFName(),gpa.getStu().getLName(),gpa.getSiteID());
			switch (type) {
			case 2:
				System.out.println(gpa.gettotalScore());
				break;
			case 3:
				System.out.println(gpa.getAveHW());
				break;
			case 4:
				System.out.println(gpa.getProject());
				break;
			case 5:
				System.out.println(gpa.getGrade());
				break;
			default:
				break;
			}
		}
	}
	
	private static void menu5(Scanner sc) {
		System.out.println("1. Add record");
		System.out.println("2. Remove record");
		System.out.println("3. Change record");
		System.out.println("4. Main Menu");
		System.out.println();
		System.out.print("Select: ");
		int n = sc.nextInt(),site,mid,finalScore;
		float pro;
		String name,LName,FName,sID,hw;
		switch (n) {
		case 1:
			System.out.print("Class Name: ");
			name = sc.next();
			System.out.print("Last Name: ");
			LName = sc.next();
			System.out.print("First Name: ");
			FName = sc.next();
			System.out.print("SID: ");
			sID = sc.next();
			System.out.print("Site: ");
			site = sc.nextInt();
			System.out.print("Homework Score: ");
			hw = sc.nextLine();
			hw = sc.nextLine();
			System.out.print("Project Score: ");
			pro = sc.nextFloat();
			System.out.print("Midterm Score: ");
			mid = sc.nextInt();
			System.out.print("Final Score: ");
			finalScore = sc.nextInt();
			List<Integer> hwList = new ArrayList<>();
			String[] hws = hw.split(" ");
			for(String s : hws) {
				hwList.add(Integer.parseInt(s));
			}
			GPAInfo.addNewGPA(name, FName, LName, sID, site, hwList, pro, mid, finalScore);
			break;
		case 3:
			System.out.print("Class Name: ");
			name = sc.next();
			System.out.print("First Name: ");
			FName = sc.next();
			System.out.print("Last Name: ");
			LName = sc.next();
			System.out.print("SID: ");
			sID = sc.next();
			System.out.print("Site: ");
			site = sc.nextInt();
			System.out.print("Homework Score: ");
			hw = sc.nextLine();
			hw = sc.nextLine();
			System.out.print("Project Score: ");
			pro = sc.nextFloat();
			System.out.print("Midterm Score: ");
			mid = sc.nextInt();
			System.out.print("Final Score: ");
			finalScore = sc.nextInt();
			List<Integer> hwList1 = new ArrayList<>();
			String[] hws1 = hw.split(" ");
			for(String s : hws1) {
				hwList1.add(Integer.parseInt(s));
			}
			GPAInfo.changeGPA(sID, name, site, finalScore, mid, pro, hwList1);
			break;
		case 2:
			System.out.print("Class Name: ");
			name = sc.next();
			System.out.println("Site:");
			site = sc.nextInt();
			System.out.println("First Name:");
			FName = sc.next();
			System.out.println("Last Name: ");
			LName = sc.next();
			System.out.println("SID: ");
			sID = sc.next();
			GPAInfo.removeGPA(sID, name, site);
			break;
		default:
			break;
		}
	}
	
	private static void menu6(Scanner sc) {
		System.out.println("1. Average Score of class");
		System.out.println("2. Percentage of grade");
		System.out.println("3. Main Menu");
		System.out.println();
		System.out.print("Select: ");
		int n = sc.nextInt();
		String name;
		switch (n) {
		case 1:
			System.out.print("Class Name: ");
			name = sc.next();
			Map<String,List<Float>> map = GPAInfo.average(name);
			System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n","Class or Site","Average Homework","Average Project","Average Midterm","Average Final","Average Total Score");
			for(Entry<String,List<Float>> entry : map.entrySet()) {
				List<Float> list = entry.getValue();
				System.out.printf("%-20s%-20f%-20f%-20f%-20f%-20f\n",entry.getKey(),list.get(0),list.get(1),list.get(2),list.get(3),list.get(4));
			}
			break;
		case 2:
			System.out.print("Class Name: ");
			name = sc.next();
			List<Double> list = GPAInfo.percentageOfGrade(name);
			DecimalFormat df = new DecimalFormat("######0.00");
			System.out.println("A: " + df.format(list.get(0)) + "%");
			System.out.println("B: " + df.format(list.get(1)) + "%");
			System.out.println("C: " + df.format(list.get(2)) + "%");
			System.out.println("D: " + df.format(list.get(3)) + "%");
			break;
		default:
			break;
		}
	}
	
	
	public static void main(String[] args) {
		GPAInfo = new LoadGPA(new LoadCourse());
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			printMainmenu();
			int n = sc.nextInt();
			switch (n) {
			case 1:
				menu1(sc);
				break;
			case 2:
				menu2(sc);
				break;
			case 3:
				menu3(sc);
				break;
			case 4:
				menu4(sc);
				break;
			case 5:
				menu5(sc);
				break;
			case 6:
				menu6(sc);
				break;
			case 7:
				sc.close();
				return;
			default:
				break;
			}
		}
	}

}
