package camp.View;

import camp.Controller.StudentManager;
import java.util.Scanner;


public class DisplayManager {
    StudentManager studentManager = new StudentManager();
    // 스캐너
    private static Scanner sc = new Scanner(System.in);

// 메인
    public void displayMainView() throws InterruptedException {
        boolean flag = true;
        final boolean exception;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = 0;
            String input1 = sc.nextLine();

            try {
                input = Integer.parseInt(input1);
            } catch (NumberFormatException n) {
                System.out.println("올바른 숫자의 형식이 아닙니다.");
                continue;
            }

            switch (input) {
                case 1 -> { displayStudentView(); break; }
                case 2 -> { displayScoreView(); break; }
                case 3 -> { flag = false; break; }
                default -> {
                    System.out.println("잘못 입력하셨습니다. \n 1~3번 중 입력해주세요.");
                    Thread.sleep(1000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

// 수강생 관리
    private void displayStudentView() throws InterruptedException {

        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = 0;
            String input1 = sc.nextLine();

            try {
                input = Integer.parseInt(input1);
            } catch (NumberFormatException n) {
                System.out.println("올바른 숫자의 형식이 아닙니다.");
                continue;
            }

           switch (input) {
                case 1 -> { studentManager.createStudent(); break; }
                case 2 -> { studentManager.inquireStudent(); break; }
                case 3 -> { flag = false; Thread.sleep(1000); break; }
                default -> {
                    System.out.println("잘못입력하셨습니다. \n1~3번 중 숫자를 입력해주세요.");
                    Thread.sleep(1000);
                }
            }
            System.out.println("메인화면으로 돌아갑니다.");
        }
    }

// 점수 관리
    private void displayScoreView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = 0;
            String input1 = sc.nextLine();

            try {
                input = Integer.parseInt(input1);
            } catch (NumberFormatException n) {
                System.out.println("올바른 숫자의 형식이 아닙니다.");
                continue;
            }

            switch (input) {
                case 1 -> studentManager.allStudentsListForRegistScore();
                case 2 -> studentManager.allStudentsListForChangeScore();
                case 3 -> studentManager.allStudentsListForInquireRoundGradeBySubject();
                case 4 -> { flag = false; Thread.sleep(1000); }
                default -> {
                    System.out.println("잘못입력하셨습니다. \n1~4번 중 선택해주세요.");
                    Thread.sleep(1000);
                }
            }
                System.out.println("메인화면으로 돌아갑니다.");
        }
    }
}
