package code.model;
import java.util.*;
public class PrepaidPlan extends Plan
{
   
   public PrepaidPlan()
   {
   }
   public PrepaidPlan(String name,double LM2M,double LM2L,double STD,double SMS,boolean status)
   {
     super(name,LM2M,LM2L,STD,SMS,status);  
   
     
   }
   
   public boolean equals(Plan p) {
     if(p instanceof PostPaidPlan)
       return false;
     if(p.getLM2M() == getLM2M() &&
        p.getLM2L() == getLM2L() &&
        p.getSTD() == getSTD() &&
        p.getSMS() == getSMS())
       return true;
     return false;        
   }
}

   