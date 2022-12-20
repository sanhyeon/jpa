package com.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="DEPT_A")
public class Department {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int deptno;
	private String dname;
	private String loc;
	
	@OneToMany(mappedBy="dept", cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	private List<Employee> employeeList = new ArrayList<Employee>();
}
