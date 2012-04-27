package code.model;
import java.util.*;
import code.*;

public class Customer
{
   private long customerId;
   private String name;
   private Date dateOfBirth;
   private char gender;
   private String address;
   private double monthlySalary;
   private long mobileNumber;
   private Plan plan;
   public Customer()
   {
   }
    public Customer(long customerId,String name,Date dateOfBirth,char gender,double monthlySalary,long mobileNumber)
   {
      this.customerId = customerId;
      this.name = name;
      this.dateOfBirth = dateOfBirth;
      this.gender = gender;
      this.monthlySalary = monthlySalary;
      this.mobileNumber = mobileNumber;
   }
    
   public Plan getPlan() { return plan; }
   public void setPlan(Plan plan) { this.plan = plan; }
   public boolean setNewPlan(Plan plan) { 
     try { 
       this.plan = plan; return true;
     } catch (Exception e) { return false; }
   }
   public long getCustomerId() { return customerId; }
   public void setCustomerId(long myCustomerId) { customerId = myCustomerId; }
   public String getName() { return name; }
   public void setName(String myName) { name = myName; }
   public Date getDateOfBirth() { return dateOfBirth; }
   public void setDateOfBirth(Date myDateOfBirth) { dateOfBirth = myDateOfBirth; }
   public char getGender() { return gender; }
   public void setGender(char myGender) { gender = myGender; }
   public String getAddress() { return address; }
   public void setAddress(String myAddress) { address = myAddress; }
   public double getMonthlySalary() { return monthlySalary; }
   public void setMonthlySalary(double myMonthlySalary) { monthlySalary = myMonthlySalary; }
   public long getMobileNumber() { return mobileNumber; }
   public void setMobileNumber(long myMobileNumber) { mobileNumber = myMobileNumber; }
   
   public void enrolPlan(String planName)
   {
     for(int i=0; i<LetsTalkMobileServiceProvider.letsTalk.getPlanList().size(); i++)
     {
       if(LetsTalkMobileServiceProvider.letsTalk.getPlanList().get(i).getName().equals(planName))
       {
         this.setPlan(LetsTalkMobileServiceProvider.letsTalk.getPlanList().get(i));
         return;
       }       
     }
     System.out.println("Invalid Plan name.");
   }
}

  