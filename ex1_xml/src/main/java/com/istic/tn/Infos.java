package com.istic.tn;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService(targetNamespace="http://InfosClass.tn",serviceName="MyService")
public class Infos {
	
	@WebMethod(operationName = "hello")
	@WebResult(name="message")
   public String hello(){
        return "Hello ISTIC";
   }
	
	@WebMethod(operationName = "helloYou")
	@WebResult(name="message")
   public String helloYou(@WebParam(name="yourName") String name){
        return " Welcome " + name;
   }
}