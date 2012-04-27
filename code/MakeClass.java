public class MakeClass
{
  private String accessSp;
  private String dataType;
  private String varName;
  private String finalOutput;
  private String attList;
  private String className;
  private String imports = "";
  private int tabSize = 3;
  /**
   * This constructor generates (prints on console) the java code with class name as <code>className</code>,
   * class access specifier as <code>classAccessSp</code>, Class attributes present in <code>listOfAttributes</code> and <code>tabsize</code> as number of spaces.
   * 
   * @param classAccessSp This can take valid values as "public" or a null string. Anything other than this will be treated as public.
   * @param className The name of the class to be generated.
   * @param listOfAttributes This contains list of attributes. Each attribute consists of 2 or 3 parts - each a String - first being the access specifier (which is optional),  
   * second being the data type and third the name of the attribute. Multiple attributes must be seperated by semi-colon(;), <br>Eg: "private String var1;private int var2;Double var1;"
   * @param tabSize It is the number of spaces per indentation level.
   */
  public MakeClass(String classAccessSp,String className,String listOfAttributes,int tabSize )
  {
    this("",classAccessSp,className,listOfAttributes,tabSize);
  }
  
  /**
   * This constructor generates (prints on console) the java code with class name as <code>className</code>,
   * class access specifier as <code>classAccessSp</code>, Class attributes present in <code>listOfAttributes</code> and <code>tabsize</code> as number of spaces.
   * 
   * @param imports All the import which should be used in the class. Multiple imports must be seperated be semi-colon(;)
   * @param classAccessSp This can take valid values as "public" or a null string. Anything other than this will be treated as public.
   * @param className The name of the class to be generated.
   * @param listOfAttributes This contains list of attributes. Each attribute consists of 2 or 3 parts - each a String - first being the access specifier (which is optional),  
   * second being the data type and third the name of the attribute. Multiple attributes must be seperated by semi-colon(;), <br>Eg: "private String var1;private int var2;Double var1;"
   * @param tabSize It is the number of spaces per indentation level.
   */
  public MakeClass(String imports,String classAccessSp,String className,String listOfAttributes,int tabSize)
  {
    this.imports = imports;
    this.tabSize = tabSize;
    this.attList = listOfAttributes;
    this.className = className;
    
    if(!classAccessSp.equals("public") && !classAccessSp.equals("")) 
      classAccessSp = "public";
    switch(validate(listOfAttributes))
    {
      case 0:System.out.println("Not valid Input.");return;
      case 1:
      case 2:this.generateClassAndFunctions(classAccessSp,className,listOfAttributes);
      
    }
  }
  public MakeClass(String classAccessSp,String className,String listOfAttributes)
  {
    this("",classAccessSp,className,listOfAttributes,3);
  }
  public MakeClass(String imports,String classAccessSp,String className,String listOfAttributes)
  {
    this(imports,classAccessSp,className,listOfAttributes,3);
  }
  
  private String putSpaces(int x)
  {
   StringBuffer sb = new StringBuffer();
   for(int i=0;i<x;i++)
     sb.append(" ");
   return sb.toString();
  }
  public String generateGetter(String inputAttribute)
  {
    String[] temp = inputAttribute.split(" ");
    if(temp.length == 3)
    {
      accessSp = "public";
      dataType = temp[1];
      varName = temp[2];
      
    }
    else if(temp.length == 2)
    {
      accessSp = "public";
      dataType = temp[0];
      varName = temp[1];
      
    }
    else
      System.out.println("Error in input Attributes");
    
    String newVarName = varName.substring(0,1).toUpperCase() + varName.substring(1);
    
    finalOutput = putSpaces(tabSize)+  accessSp + " " + dataType + " get" + newVarName + "() ";
    finalOutput += "{ ";
    finalOutput += "return " + varName + "; ";
    finalOutput += "}\n";
    return finalOutput;
  }
  public String generateSetter(String inputAttribute)
  {
    String[] temp = inputAttribute.split(" ");
    if(temp.length == 3)
    {
      accessSp = temp[0];
      dataType = temp[1];
      varName = temp[2];
      
    }
    else if(temp.length == 2)
    {
      accessSp = "public";
      dataType = temp[0];
      varName = temp[1];
      
    }
    else
      System.out.println("Error in input Attributes");
    
    String newVarName = varName.substring(0,1).toUpperCase() + varName.substring(1);
    
    finalOutput = putSpaces(tabSize)+  accessSp + " " + "void" + " set" + newVarName + "(" + dataType + " my" + newVarName + ") ";
    finalOutput += "{ ";
    finalOutput += varName + " = my" + newVarName + "; ";
    finalOutput += "}\n";
    return finalOutput;
  }
  public String generateGetAndSet(String listOfAttributes)
  {
    String[] temp = listOfAttributes.split(";");
    String finalOutput = "";
    for(int i=0; i<temp.length; i++)
    {
      finalOutput += this.generateGetter(temp[i]);
      finalOutput += this.generateSetter(temp[i]);
    }
    return finalOutput;
  }
  public String generateAttributes(String listOfAttributes)
  {
    String[] temp = listOfAttributes.split(";");
    String finalOutput = "";
    for(int i=0; i<temp.length; i++)
    {
      if(temp[i].split(" ").length == 2)
        finalOutput += putSpaces(tabSize)+"private " + temp[i] + ";\n";
      else
        finalOutput += putSpaces(tabSize) + temp[i] + ";\n";
    }
    return finalOutput;
  }
  public void generateParaConst(String varNames)
  {
    String[] attArray = this.generateAttributes(this.attList).replace("\n","").split(";");
    String[] t = varNames.split(",");
    String params = "";
    String finalOutput = "";
    boolean flag = false;
    for(int i=0 ;i<t.length;i++)
      for(int j=0;j<attArray.length;j++)
      {
         String[] te = attArray[j].split(" ");
         int off = this.tabSize;
         if(te[2+off].equals(t[i]))
         {
           params += attArray[j].split(" ")[1+off] + " " + attArray[j].split(" ")[2+off] + ",";
           flag = true;
         }
      }
    if(params!="")
      params = params.substring(0,(params.length() - 1) );
    if(flag)
    {
    finalOutput += putSpaces(tabSize)+"public " + this.className + "(" + params + ")\n";
    finalOutput += putSpaces(tabSize)+"{\n";
    for(int i=0;i<params.split(",").length;i++)
       finalOutput += putSpaces(tabSize)+putSpaces(tabSize)+"this." + params.split(",")[i].split(" ")[1] + " = " + params.split(",")[i].split(" ")[1] + ";\n";
    finalOutput += putSpaces(tabSize)+"}\n";
    System.out.println(finalOutput);
    }
    else
      System.out.println("No such Data Member Present");
  }
  /** 
   *
   */
  public int validate(String listOfAttributes)
  {
    
    String[] temp = listOfAttributes.split(";");
    String finalOutput = "";
    if(listOfAttributes.length() == 0)
      return 2;
    
    for(int i=0; i<temp.length; i++)
      if(temp[i].split(" ").length != 2 && temp[i].split(" ").length != 3)
        return 0;
    
    return 1;
  }
  public void generateClassAndFunctions(String classAccessSp,String className,String listOfAttributes)
  {
    String finalOutput = this.imports + ";\n";
    finalOutput += classAccessSp + " class " + className + "\n";
    finalOutput += "{\n";
    if(!listOfAttributes.equals(""))
       finalOutput += this.generateAttributes(listOfAttributes);
    finalOutput += putSpaces(tabSize)+"public " + className + "()\n";
    finalOutput += putSpaces(tabSize)+"{\n";
    finalOutput += putSpaces(tabSize)+"}\n";
    if(!listOfAttributes.equals(""))
       finalOutput += this.generateGetAndSet(listOfAttributes);
    finalOutput += "}";
    System.out.println(finalOutput);
  }
}

