package code.model;
public class PostPaidPlan extends Plan
{
   private double MR;
   public PostPaidPlan()
   {
   }
   public PostPaidPlan(String name,double LM2M,double LM2L,double STD,double SMS,double MR,boolean status)
   {
     super(name,LM2M,LM2L,STD,SMS,status); 
     this.MR = MR;
   }
   public double getMR() { return MR; }
   public void setMR(double myMR) { MR = myMR; }
   
   public boolean equals(Plan p) {
     if(p instanceof PrepaidPlan)
       return false;
     if(p.getLM2M() == getLM2M() &&
        p.getLM2L() == getLM2L() &&
        p.getSTD() == getSTD() &&
        p.getSMS() == getSMS() &&
        ((PostPaidPlan)p).getMR() == getMR())
       return true;
     return false;        
   }
}

   