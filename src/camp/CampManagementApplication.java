package camp;

import camp.Controller.InitializeManager;
import camp.View.DisplayManager;

public class CampManagementApplication {

    private static final DisplayManager display = new DisplayManager();
    public static void main(String[] args) {
        InitializeManager.getInstance().setInitData();

        try {
            display.displayMainView();
        } catch (InterruptedException i) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }
}
