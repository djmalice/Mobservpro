package code.ui;
import code.model.*;
import java.io.*;
import java.util.*;
import code.*;
import java.text.*;

public class CustomerUI {
////////////////////////GENERAL FUNCTIONS/////////////////////////////////////////////////    
  public String getStringInput(String msg){
    System.out.println(msg);
    String inputStr = "";
    try{
      InputStreamReader reader = new InputStreamReader(System.in);
      BufferedReader bufferedReader = new BufferedReader(reader);
      inputStr = bufferedReader.readLine();
      return inputStr;
    }
    catch(Exception e){
      System.out.println("Please give a valid input");
      return getStringInput(msg);      
    }    
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
      return input;
    }
    catch(Exception e){
      System.out.println("Please enter a valid amount.");
      return getAmount(msg);
    }    
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
  
  public char getCharMF(String msg) {
    char c = 'a';
    System.out.println(msg);
    try {
      InputStreamReader reader = new InputStreamReader(System.in);
      BufferedReader bufferedReader = new BufferedReader(reader);
      c = (char)bufferedReader.read();
      if(c != 'M' && c!='m' && c!='f' && c!='F')
        throw new Exception();
      return c;
    }    
    catch(Exception e){
      System.out.println("please enter a valid character.");
      return getCharMF(msg);
    }    
  }
  
  public void displayMessage(String msg) {
    System.out.println(msg);
  }
  
  public boolean getConfirmation(String msg) {
    System.out.println(msg);
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    char c;
    while(true)
    { 
      c = getCharYN("");
      if(c=='Y' || c=='y')
        return true;
      if(c=='N' || c=='n')
        return false;
      else System.out.println("Enter either Y or N:");
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
  
  public long getMobileNumber(String msg) {
    String inputStr = "";
    long input = 0;
    System.out.println(msg);
    try{
      InputStreamReader reader = new InputStreamReader(System.in);
      BufferedReader bufferedReader = new BufferedReader(reader);
      inputStr = bufferedReader.readLine();
      input = Long.parseLong(inputStr);
      if(Long.toString(input).length()!=10){
        throw new Exception();
      
      }
      return input;
    }
    catch(Exception e){
      System.out.println("please enter a valid mobile number.");
      return getMobileNumber(msg);
    }    
  }
  
  public long getLong(String msg) {
    String inputStr = "";
    long input = 0;
    System.out.println(msg);
    try{
      InputStreamReader reader = new InputStreamReader(System.in);
      BufferedReader bufferedReader = new BufferedReader(reader);
      inputStr = bufferedReader.readLine();
      input = Long.parseLong(inputStr);
      if(input < 0){
        throw new Exception();
      }
      return input;
    }
    catch(Exception e){
      System.out.println("Please enter a valid number.");
      return getLong(msg);
    }    
  } 
/////////////////////////REGISTER CUSTOMER////////////////////////////////////////////////////  
  
  public Customer getCustomerDetails() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter Customer Details:");
    String name = getStringInput("Name: ");
    long customerId = LetsTalk.getCustomerId();    
    Date dateOfBirth = getDate("Date Of Birth (dd-mm-yyyy): ");
    char gender = getCharMF("Gender (M/F): ");
    String address = getStringInput("Address: ");
    double monthlySalary = getAmount("Monthly Salary: ");
    long mobileNumber = getMobileNumber("Mobile Number: ");
    Customer cust = new Customer();
    cust.setName(name);
    cust.setCustomerId(customerId);
    cust.setDateOfBirth(dateOfBirth);
    cust.setGender(gender);
    cust.setAddress(address);
    cust.setMonthlySalary(monthlySalary);
    cust.setMobileNumber(mobileNumber);
    return cust;
  }  
  
  public String getDesiredPlan() {
    String planName = getStringInput("Enter Plan Name: ");
    return planName;
  }  
////////////////////////CHANGE PLAN///////////////////////////////////////////////////////////  
  public long getCustomerId() {
    long customerId = getLong("Enter Customer Id: ");
    return customerId;
  }
  public String getPlanName() {
    String planName = getStringInput("Enter Plan Name: ");
    return planName;
  }    
}
