package com;

import com.XMLOutputFormat.XMLOutFormatDriver;
import com.CustomFormat.TitanicCustomFormatDriver;

import java.io.IOException;

public class Application {
  public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
    if(Integer.parseInt(args[0])==1){
      //1 /titanic/input /titanic/output
      TitanicCustomFormatDriver.run(args[1],args[2]);
    }
    if(Integer.parseInt(args[0])==2){
      //2 /xmlOutFormat/input /xmlOutFormat/output
      XMLOutFormatDriver.run(args[1],args[2]);
    }
  }
}
