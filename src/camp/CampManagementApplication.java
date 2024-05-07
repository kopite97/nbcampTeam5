package camp;

import camp.Controller.InitializeManager;
import camp.View.Display;

import java.util.InputMismatchException;


/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {

    private static final Display display = new Display();
    public static void main(String[] args) {
        InitializeManager.getInstance().setInitData();

        try {
            display.displayMainView();
        } catch (InterruptedException i) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        } catch (NumberFormatException n) {
            System.out.println("숫자만 입력가능합니다.\n프로그램을 종료합니다.");
        }


    }
}
