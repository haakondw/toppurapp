package com.ntnu.eit.common.service;

public class ServiceFactory {

	//Instance
	private static ServiceFactory instance;
	
	//Services
	private PasientService pasientService;
	private DepartmentService departmentService;
	
	public PasientService getPasientService(){
		if(pasientService == null){
			pasientService = new PasientServiceTestImpl();
		}
		return pasientService;
	}
	
	public DepartmentService getDepartmentService(){
		if(departmentService == null){
			departmentService = new DepartmentServiceTestImpl();
		}
		return departmentService;
	}
	
	public static ServiceFactory getInstance(){
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}
}