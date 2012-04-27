package code.controller;
import code.model.*;
import code.ui.*;
import code.*;

public class PlanController {
  public void create() {
    
  }
//////////////////////////////////////////////////////////////////////////////////////////////  
  public void getMostPopularPlans() {
    PlanUI planUI = new PlanUI();
    boolean confirmation = planUI.getConfirmation("Are you sure you want the most popular plan:(Y/N)?");
    if(!confirmation) {
      planUI.displayMessage("The most popular plan could not be retrieved:");
      return;
    }
    Plan popularPrepaid = code.LetsTalkMobileServiceProvider.letsTalk.getMostPopularPrepaidPlan();
    Plan popularPostpaid = code.LetsTalkMobileServiceProvider.letsTalk.getMostPopularPostPaidPlan();
    planUI.displayPopularPlan(popularPrepaid);
    planUI.displayPopularPlan(popularPostpaid);
  }

//////////////////////////////////////////////////////////////////////////////////////////////  
  public void discontinuePlan() {
    PlanUI planUI = new PlanUI();
    String planName = planUI.getPlanName();
    if(!LetsTalkMobileServiceProvider.letsTalk.isPlanValid(planName)) {
      planUI.displayMessage("Plan name is not valid.");      
    }
    boolean noSub = LetsTalkMobileServiceProvider.letsTalk.checkForNoSubscriber(planName);
    if(!noSub) {
      planUI.displayMessage("Specified plan has at least one subscriber. Hence it cannot be discontinued.");
      return;
    }    
    boolean confirmation = planUI.getConfirmation("Are you sure you want to discontinue the plan:(Y/N)?");
    if(!confirmation) {
      planUI.displayMessage("Cancelled by admin.");
      return;
    }
    
    boolean status = LetsTalkMobileServiceProvider.letsTalk.discontinuePlan(planName);    
    
    if(!status) {
      planUI.displayMessage("Could not discontinue plan");
      return;
    } else {
      planUI.displayMessage("Operation Completed Successfully.");
    }
  }

//////////////////////////////////////////////////////////////////////////////////////////////  
  public void updateExistingPlans() {
    PlanUI planUI = new PlanUI();
    String planName = planUI.getDesiredPlan();
    int i=0;
    while(!LetsTalkMobileServiceProvider.letsTalk.isPlanValid(planName)) {
      i++;
      planUI.displayMessage("Given plan doesnot exist in the system. Enter a valid plan name.");
      if(i==3) {
        planUI.displayMessage("You will now be redirected to main menu.");
        return;
      }         
      planName = planUI.getDesiredPlan();
    }
    boolean confirmation = planUI.getConfirmation("Are you sure you want to update plan? ");
    if(!confirmation) {
      planUI.displayMessage("Update plan details operation has been cancelled by user.");
      return;
    }
    Plan p = LetsTalkMobileServiceProvider.letsTalk.getPlan(planName);
    
    planUI.displayPlan(p);
    
    boolean status = planUI.updatePlanDetails(p);
    if(!status) {
      planUI.displayMessage("There has been an error in updating plan details. The operation has not been completed successfully.");
      return;
    } else {
      planUI.displayMessage("Operation Completed Successfully.");
    }
  }

//////////////////////////////////////////////////////////////////////////////////////////////  
  public void addNewPlan() {
    PlanUI planUI = new PlanUI();
    Plan p = planUI.getPlanDetails();
    boolean confirmation = planUI.getConfirmation("Are you sure you want to add new plan? ");
    if(!confirmation) {
      planUI.displayMessage("Add plan operation has been cancelled by user.");
      return;
    }
    String dupPlan = new String();
    int dup = LetsTalkMobileServiceProvider.letsTalk.checkForDuplicatePlan(p,dupPlan);
    if(dup==LetsTalkMobileServiceProvider.letsTalk.DUPLICATE_PLAN_NAME) {
      planUI.displayMessage("A plan with same name already exists.");
    } else if(dup==LetsTalkMobileServiceProvider.letsTalk.PLAN_EXISTS_AS_INACTIVE) {
      planUI.displayMessage("An inactive plan with same details already exists. Plan name: "+dupPlan);
    } else if(dup==LetsTalkMobileServiceProvider.letsTalk.PLAN_EXISTS_AS_ACTIVE) {
      planUI.displayMessage("An active plan with same details already exists. Plan name: "+dupPlan);
    }
      
    boolean status = LetsTalkMobileServiceProvider.letsTalk.addPlan(p);    
    if(!status) {
      planUI.displayMessage("There has been an error in adding plan. The operation has not been completed successfully.");
      return;
    } else {
      planUI.displayMessage("Operation Completed Successfully.");
    }
  }
  
//////////////////////////////////////////////////////////////////////////////////////////////  
  public void determineBestPlan() {
    PlanUI planUI = new PlanUI();
    UsageDetails ud = planUI.getCustomerUsageDetails();
    Plan bestPlan = LetsTalkMobileServiceProvider.letsTalk.getBestPlan(ud);
    planUI.displayBestPlan(bestPlan);
  }

//////////////////////////////////////////////////////////////////////////////////////////////
  public void viewAvailablePlans() {
    PlanUI planUI = new PlanUI();
    planUI.displayAvailablePlans();
  }
  
//////////////////////////////////////////////////////////////////////////////////////////////  
}