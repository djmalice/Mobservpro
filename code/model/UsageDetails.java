package code.model;

public class UsageDetails
{
   private double LM2M;
   private double LM2L;
   private double STD;
   private double SMS;
   public UsageDetails()
   {
   }
   public UsageDetails(double LM2M,double LM2L,double STD,double SMS)
   {
      this.LM2M = LM2M;
      this.LM2L = LM2L;
      this.STD = STD;
      this.SMS = SMS;
   }
   public double getLM2M() { return LM2M; }
   public void setLM2M(double myLM2M) { LM2M = myLM2M; }
   public double getLM2L() { return LM2L; }
   public void setLM2L(double myLM2L) { LM2L = myLM2L; }
   public double getSTD() { return STD; }
   public void setSTD(double mySTD) { STD = mySTD; }
   public double getSMS() { return SMS; }
   public void setSMS(double mySMS) { SMS = mySMS; }
}