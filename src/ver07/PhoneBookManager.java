package ver07;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class PhoneBookManager implements SubMenuItem {
	//1개 이상의 지역에서 사용할 변수 및 객체
	HashSet<PhoneInfo> save;
	Iterator<PhoneInfo> itr;
	int menuCount;
	boolean tOrF;
	Scanner scan;
	
	public PhoneBookManager(int num) {
		save = new HashSet<PhoneInfo>();
		menuCount = 1; //메뉴 공백을 위한 카운트
		tOrF = false;
	}
	
	//메뉴출력
	public void printMenu() {
		if(menuCount>=2)
			System.out.println();
		System.out.println("[메뉴선택]");
		System.out.println("[1]데이터 입력");
		System.out.println("[2]데이터 검색");
		System.out.println("[3]데이터 삭제");
		System.out.println("[4]주소록 출력");
		System.out.println("[5]프로그램 종료");
		System.out.print("번호입력:");
		menuCount++;
	}
	
	//1.입력
	public void dataInput() {
		PhoneInfo pI = null;//입력정보 담을 변수
		scan = new Scanner(System.in);
		System.out.println("==데이터 입력을 시작합니다.==");
		System.out.println("[1]일반, [2]동창, [3]회사");
		System.out.print("선택>>");
		int subChoice = scan.nextInt();
		scan.nextLine();//버퍼날림
		
		//공통사항 이름, 전화번호
		System.out.print("이름:");
		String name = scan.nextLine();
		System.out.print("전화번호:");
		String phoneNumber = scan.nextLine();
		
		//서브메뉴 선택에 따라 추가입력 및 DTO 생성자 호출
		if(subChoice == BASIC) {
			pI = new PhoneInfo(name, phoneNumber);
		}
		else if(subChoice == SCHOOL) {
			System.out.print("전공:");
			String major = scan.nextLine();
			System.out.print("학년:");
			int grade = scan.nextInt();
			scan.nextLine();//버퍼날림
			pI = new PhoneSchoolInfo(name, phoneNumber, major, grade);
		}
		else if(subChoice == COMPANY) {
			System.out.print("회사:");
			String company = scan.nextLine();
			pI = new PhoneCompanyInfo(name, phoneNumber, company);
		}
		if(save.add(pI) == false) {
			System.out.println("==아래 정보와 이름이 동일합니다.");
			System.out.println("덮어쓰기 하시겠습니까?==");
			//==================중복객체 보여주는 용도==================
			itr = save.iterator();
			while(itr.hasNext()) {
				PhoneInfo show = itr.next();
				if(show.getName().equals(name)) {
					System.out.println("========================");
					show.showPhoneInfo();
					System.out.println("========================");
					}
				}
			//===================================================
			System.out.print("[1]예, [2]아니오>>");
			int tOrFCh = scan.nextInt();
			scan.nextLine();//버퍼날림
			if(tOrFCh == 1) {
				save.remove(pI);
				save.add(pI);
				System.out.println("==덮어쓰기가 완료되었습니다.==");
			}
			else {
				return;
			}
		}
	}
	
	//2.검색
	public void dataSearch() {
		scan = new Scanner(System.in);
		System.out.println("==데이터 검색을 시작합니다.==");
		System.out.print("검색할 이름:");
		String searching = scan.nextLine();
		itr = save.iterator();
		while(itr.hasNext()) {
			PhoneInfo info = itr.next();
			if(info.getName().equals(searching)) {
				tOrF = true;
				info.showPhoneInfo();
				System.out.println("==데이터 검색이 완료되었습니다.==");
				break;
			}
		}
		if(tOrF == false) {
			System.out.println("==검색할 데이터가 존재하지 않습니다.==");
		}
	}
	
	//3.삭제
	public void dataDelete() {
		scan = new Scanner(System.in);
		System.out.println("==데이터 삭제를 시작합니다.==");
		System.out.println("삭제할 이름:");
		String delete = scan.nextLine();
		itr = save.iterator();
		while(itr.hasNext()) {
			PhoneInfo info = itr.next();
			if(info.getName().equals(delete)) {
				tOrF = true;
				save.remove(info);
				System.out.println("==데이터 삭제가 완료되었습니다.==");
				break;
			}
		}
		if(tOrF == false) {
			System.out.println("==삭제할 데이터가 존재하지 않습니다.==");
		}
	}
	
	//4.주소록전체출력
	public void dataAllShow() {
		itr = save.iterator();
		while(itr.hasNext()) {
			PhoneInfo info = itr.next();
			System.out.println("========================");
			info.showPhoneInfo();
		}
	}
}//end of class
