package com.pawan.test;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="EMP_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer empId;
	
	@Column(name="EMP_NAME")
	private String empName;
	
	@ManyToOne
	@JoinColumn(name="MANAGER_ID")
	private Employee manager;
	
	@OneToMany(mappedBy="manager",fetch=FetchType.LAZY)
	private Set<Employee> employeeList=new HashSet<>(0);
	
	
	public Set<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(Set<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
}
