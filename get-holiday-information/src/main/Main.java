package main;

import java.util.ArrayList;
import java.util.Scanner;

import model.HolidayDAO;
import model.HolidayManager;
import model.HolidayVO;
import view.MainMenuChoice;

public class Main {
	public static Scanner input = new Scanner(System.in);
	public static ArrayList<HolidayVO> list=null;
	public static ArrayList<HolidayVO> selectList=null;
	
	
	public static void main(String[] args) {
		mainMenu();
	}

	private static void mainMenu() {
		boolean exitFlag = false;
		while (!exitFlag) {
			view.MainMenuViewer.mainMenuView();
			int choiceNum = input.nextInt();
			input.nextLine(); 				// 버퍼클리어
			switch (choiceNum) {
			case MainMenuChoice.LOAD:
//				System.out.println("검색할 년도와 월을 입력해주세요");
//				System.out.println("년도 (ex: 2024): ");
//				String year =input.nextLine();
//				System.out.println("월 (ex: 01): ");
//				String month =input.nextLine();
//				list=HolidayDAO.webConnection(year,month);
				list=HolidayDAO.webConnection();
				break;
			case MainMenuChoice.INSERT:
				if (list.size() < 1) {
					System.out.println("공공데이터로부터 가져온 자료가 없습니다.");
					continue;
				}
				HolidayDAO.insert(list);
				break;
			case MainMenuChoice.SELECT:
				selectList = HolidayDAO.select();
				HolidayManager.print(selectList);
				break;
			case MainMenuChoice.UPDATE:
				String seq = HolidayManager.update();
				System.out.println("남길 메모를 작성해주세요: ");
				String memo =input.nextLine();
				if (seq.length() != 0) {
					HolidayManager.update(seq,memo);
				}
				break;
			case MainMenuChoice.DELETE:
				HolidayManager.delete();
				break;
			case MainMenuChoice.SEARCH:
				HolidayDAO.search();
				break;
			case MainMenuChoice.EXIT:
				exitFlag = true;
				break;
			}
		}
		
	}
}
