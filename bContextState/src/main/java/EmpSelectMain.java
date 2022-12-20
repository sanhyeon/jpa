import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpSelectMain {

	public static void main(String[] args) {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");
	      EntityManager em = emf.createEntityManager();
	      EntityTransaction tx = em.getTransaction();
	      
	      try {
	    	  // [1] find() 실행할 때 select 사용
	    	  //EmpVO emp1 = em.find(EmpVO.class, 1299);
	    	  //System.out.println("검색결과:"+emp1);
	    	
	    	  // [2] getReference() 예외 값이 뜸 
	    	  //EmpVO emp2 = em.getReference(EmpVO.class, 1299);
	    	  //System.out.println("검색결과:"+emp2);
	    	  
	    	  // [3] JPQL (Java Persistence Query Language)
	    	  //	CreateQuery()
	    	  //	===> 테이블명이 아니라 엔티티명임 ( 대소문자 구별 )
	    	  String jpql = "SELECT e FROM EmpVO e ORDER By e.empno DESC";		//sql 문장이 아니라 JPQL이다
	    	  List<EmpVO> list = em.createQuery(jpql, EmpVO.class).getResultList();
	    	  for(EmpVO vo : list) {
	    		  System.out.println(">>>>"+ vo);
	    	  }
	    	  
	      }catch (Exception e) {
	          System.out.println("예외 : " + e.getMessage());
	      }finally {
	    	  tx.commit();
	          em.close();
	      

	      }

	}
}