package code.ui;
import code.model.*;
import code.*;
import java.util.*;
import java.io.*;
import java.text.*;
public class PlanUI {
////////////////////////GENERAL FUNCTIONS/////////////////////////////////////////////////    
  public String getStringInput(String msg){
    System.out.println(msg);
    String inputStr = "";
    try{
      InputStreamReader reader = new InputStreamReader(System.in);
      BufferedReader bufferedReader = new BufferedReader(reader);
      inputStr = bufferedReader.readLine();      
    }
    catch(Exception e){
      System.out.println("Please give a valid input");
      return getStringInput(msg);      
    }
    return inputStr;
  }
  
  public double getAmount(String msg){
    String inputStr = "";
    double input = 0;
    System.out.println(msg);
    try{
      InputStreamReader reader = new InputStreamReader(System.in);
      BufferedReader bufferedReader = new BufferedReader(reader);
      inputStr = bufferedReader.readLine();
      input = Double.parseDouble(inputStr);
      if(input < 0){
        throw new Exception();
      }        
    }
    catch(Exception e){
      System.out.println("Please enter a valid amount.");
      return getAmount(msg);
    }
    return input;
  }
  
  public char getCharYN(String msg) {
    char c = 'a';
    System.out.println(msg);
    try {
      InputStreamReader reader = new InputStreamReader(System.in);
      BufferedReader bufferedReader = new BufferedReader(reader);
      c = (char)bufferedReader.read();
      if(c != 'Y' && c!='y' && c!='n' && c!='N')
        throw new Exception();
      return c;
    }    
    catch(Exception e){
      System.out.println("please enter a valid character.");
      return getCharYN(msg);
    }    
  }
  
  public Date getDate(String msg) {
    System.out.println(msg);
    Date input = new Date();
    try{
      InputStreamReader reader = new InputStreamReader(System.in);
      BufferedReader bufferedReader = new BufferedReader(reader);
      input = new SimpleDateFormat("dd-MM-yyyy").parse(bufferedReader.readLine());      
      return input;
    }
    catch(Exception e){
      System.out.println("please enter a valid date.");
      return getDate(msg);
    }    
  }
  
  public void displayMessage(String msg) {
    System.out.println(msg);
  }
  
  public boolean getConfirmation(String msg) {
    System.out.println(msg);
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    while(true)
    { 
      char c = getCharYN("");
      if(c=='Y' || c=='y')
        return true;
      if(c=='N' || c=='n')
        return false;
      else System.out.println("Enter either Y or N:");
    }
  }
  public void displayPlan(Plan p) {
    System.out.println("Plan Details: ");
    System.out.println("Name: "+p.getName());
    System.out.println("Type: "+((p instanceof PrepaidPlan)?"Prepaid":"PostPaid"));
    System.out.println("LM2M: "+p.getLM2M());
    System.out.println("LM2L: "+p.getLM2L());
    System.out.println("STD: "+p.getSTD());
    System.out.println("SMS: "+p.getSMS());
    if(p.getStatus()==false)
      System.out.println("Plan Status: Inactive");
  }
////////////////////////GET MOST POPULAR PLAN/////////////////////////////////////////////////  
public void displayPopularPlan(Plan p) {
  System.out.println("Popular plan is: ");
  displayPlan(p);
}

////////////////////DISCONTINUE PLAN/////////////////////////////////////////////  
public String getPlanName() {
  return getStringInput("Enter the plan name to be deleted:");   
}
/////////////////////////////GET BEST PLAN//////////////////////////////////////////////  
public UsageDetails getCustomerUsageDetails() {
  System.out.println("");
  System.out.println("Enter the customer usage details:");
  UsageDetails ud=new UsageDetails();  
  ud.setLM2M(getAmount("Enter LM2M min:"));
  ud.setLM2L(getAmount("Enter LM2L min:"));
  ud.setSTD(getAmount("Enter STD min:"));
  ud.setSMS(getAmount("Enter SMS min:"));  
  return ud;
}

public void displayBestPlan(Plan bestPlan) {
  System.out.println("The best plan is as follows:");
  displayPlan(bestPlan);
}

///////////////////////////VIEW AVAILABLE PLAN////////////////////////////////////////////
public void displayAvailablePlans() {
  System.out.println("List of plans:");
  for(int i=0;i<LetsTalkMobileServiceProvider.letsTalk.getPlanList().size();i++)
  { 
    System.out.println();
    System.out.println("Plan " + (i+1));
    displayPlan(LetsTalkMobileServiceProvider.letsTalk.getPlanList().get(i));
  } 
}
  
//////////////////////////UPDATE EXISTING PLAN////////////////////////////////////  
  public String getDesiredPlan() {
    String plan = getStringInput("Enter Plan name to be updated: ");
    return plan;      
  }  
  public boolean updatePlanDetails(Plan p) { //scan updated details and store them in plan p
    double dtemp;    
    try {
      dtemp = getAmount("Enter LM2M: ");
      p.setLM2M(dtemp);
    
      dtemp = getAmount("Enter LM2L: ");
      p.setLM2L(dtemp);
    
      dtemp = getAmount("Enter STD: ");
      p.setSTD(dtemp);
      
      dtemp = getAmount("Enter SMS: ");
      p.setSMS(dtemp);
      
      p.setStatus(true);
      
      return true;
    } catch(Exception e) { return false; }
  }  
  
///////////////////////////ADD NEW PLAN///////////////////////////////////////////////  
  public Plan getPlanDetails() {
    Plan p = (getConfirmation("Is new plan prepaid? (y/n): "))?new PrepaidPlan():new PostPaidPlan();
    p.setName(getStringInput("Enter plan name: "));
    updatePlanDetails(p);
    if(p instanceof PrepaidPlan) {
      //scan prepaid plan specific data
    } else {
      ((PostPaidPlan)p).setMR(getAmount("Enter Monthly Rental: "));
    }
    return p;
  }
  
}



