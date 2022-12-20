package com.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainApp {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("cReference");
		try {
			// [1] 연관관계를 이용한 데이터 검색
			//selectData(emf);
			
			// [2] 연관관계를 이용한 데이터 입력
			//insertData(emf);
			
			// [3] 연관관계를 이용한 데이터 수정
			//updateData(emf);
			
			// [4] 연관관계를 이용한 데이터 삭제
			deleteData(emf);
		}catch(Exception ex) {
			System.out.println("예외:" + ex.getMessage());
		}finally {
			emf.close();
			
		}
			
	}
	
	//[4]
	static void deleteData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// 40번 부서를 삭제
		Department dept = em.find(Department.class, 40);
		//System.out.println(dept);
		em.remove(dept);
		// [해결1] 사원테이블에서 40번부서의 내용을 null 수정
		// [해결2] 40번 부서의 사원 정보를 먼저 삭제
		tx.commit();
		em.close();
	}
	
	
	static void updateData(EntityManagerFactory emf) {
		// 사번이 7369 사원의 부서를 40번 부서로 변경
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Employee emp2 = em.find(Employee.class, 7369);
		Department dept = em.find(Department.class, 40);
		
		emp2.setDept(dept);
		tx.commit();
		em.close();
		
	}
	
	// [1] 연관관계를 이용한 데이터 검색
	static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		Employee e1 = em.find(Employee.class, 7788);
		System.out.println(e1);
		
		System.out.println(e1.getEname()+"님 정보");
		System.out.println("부서:" + e1.getDept().getDname());
		em.close();
	}
	
	//  [2] 연관관계를 이용한 데이터 입력
	static void insertData(EntityManagerFactory emf) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Employee emp1 = new Employee();
		emp1.setEname("홍씨3");
		
		//Department dept = em.find(Department.class, 40);
		Department dept = new Department();
		dept.setDeptno(50);
		dept.setDname("아이티");
		dept.setLoc("인천");
		em.persist(dept);
		
		emp1.setDept(dept);
		
		em.persist(emp1);
		
		tx.commit();
		em.close();
	}
	
}
