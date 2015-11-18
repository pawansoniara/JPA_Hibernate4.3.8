package com.pawan.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class MainClass {
	
	 private static final SessionFactory sessionFactory = buildSessionFactory();

	    private static SessionFactory buildSessionFactory() {
	    	 SessionFactory sessionFactory=null;
	    	try {
	        	
	        	sessionFactory=new Configuration().configure().buildSessionFactory();
	        }
	        catch (Throwable ex) {
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	        
	        return sessionFactory ;
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	    
	public static void main(String[] args) {
		try{
			/*if(JOptionPane.showConfirmDialog(null, "Want to create new Manager", "Confirm!!", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				String managerName= JOptionPane.showInputDialog(null, "Entee Manager Name");
				Employee manager  =createManager(managerName);
				while(JOptionPane.showConfirmDialog(null, "do you want add Employee for Manager :"+manager.getEmpName(), "Confirm!!", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					String EmployeeName= JOptionPane.showInputDialog(null, "Entee Employee Name");
					addEmployee(manager,EmployeeName);
				}
				//sdd
			}*/
			
			Employee manager =getManager("Dheeraj");
			if(manager!=null){
				System.out.println("Manager :"+manager.getEmpName());
				if(manager.getEmployeeList()!=null && manager.getEmployeeList().size()>0){
					for(Employee emp:manager.getEmployeeList()){
						System.out.println("Employee :"+emp.getEmpName());
					}
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				getSessionFactory().close();
			}catch(Exception dd){}
			
			System.exit(0);
		}
		
		
	}

	private static  Employee  createManager(String name) {
		Session session = getSessionFactory().openSession();
        session.beginTransaction();
        
        Employee manager = new Employee();
        manager.setEmpName(name);
        
        session.save(manager);
        
        session.getTransaction().commit();
        
        return  manager;
	}
	
	private static void addEmployee(Employee manager,String EmployeeName) {
		Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Employee emp = new Employee();
        emp.setEmpName(EmployeeName);
        emp.setManager(manager);
        session.save(emp);
        session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	private static Employee getManager(String employeeName) {
		Session session = getSessionFactory().openSession();
        //List<Employee> manager =session.createQuery("from Employee where empName='"+employeeName+"'").list();
		List<Employee> manager= session.createCriteria(Employee.class).add(Restrictions.eq("empName",employeeName)).list();
		return  (manager!=null && manager.size()>0)?manager.get(0):null ;
	}

}
