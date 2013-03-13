package com.ntnu.eit.common.service;

public class ServiceFactory {

	//Instance
	private static ServiceFactory instance;
	
	//Services
	private PasientService pasientService;
	
	public PasientService getPasientService(){
		if(pasientService == null){
			pasientService = new PasientServiceTestImpl();
		}
		return pasientService;
	}
	
	public static ServiceFactory getInstance(){
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}
}