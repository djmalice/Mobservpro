package code;
import java.util.*;
import code.model.*;
import code.controller.*;
import code.ui.*;

public class LetsTalkMobileServiceProvider {
  public static LetsTalk letsTalk = new LetsTalk();
  private static CustomerController custCtrl = new CustomerController();
  private static PlanController planCtrl = new PlanController();  
  
  public static void main(String[] args) {
    initData();
    while(true) {
      switch(MainUI.displayMenu()) {
        case 1: planCtrl.addNewPlan(); break;
        case 2: planCtrl.viewAvailablePlans(); break;
        case 3: planCtrl.getMostPopularPlans(); break;    
        case 4: planCtrl.determineBestPlan(); break;
        case 5: custCtrl.registerCustomer(); break;
        case 6: custCtrl.changePlan(); break;
        case 7: planCtrl.updateExistingPlans(); break;
        case 8: planCtrl.discontinuePlan(); break;        
        case 9: System.out.println("Have a good day! :)"); return;
      }
    }
  }
  
  private static void initData() {
    letsTalk.getPlanList().add(new PrepaidPlan("pre1",0.5,0.5,0.5,0.5,true));
    letsTalk.getPlanList().add(new PrepaidPlan("pre2",0.3,0.5,0.4,0.5,true));
    letsTalk.getPlanList().add(new PostPaidPlan("post1",0.3,0.5,0.4,0.5,199,true));
    letsTalk.getPlanList().add(new PostPaidPlan("post2",0.1,0.1,0.2,0.2,399,true));
  }
}