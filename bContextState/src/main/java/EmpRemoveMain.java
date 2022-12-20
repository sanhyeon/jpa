import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpRemoveMain {

	public static void main(String[] args) {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");
	      EntityManager em = emf.createEntityManager();
	      EntityTransaction tx = em.getTransaction();
	      
	      try {
	    	  
	    	  EmpVO emp1 = em.find(EmpVO.class, 1297);
	    	  System.out.println("검색결과:"+emp1);
	    	  
	    	  // 엔티티 삭제
	    	  tx.begin();
	    	  em.remove(emp1);
	    	  tx.commit();
	    	  
	    	  EmpVO emp2 = em.find(EmpVO.class, 1297);
	    	  System.out.println("검색결과2:"+emp2);
	    	  
	      }catch (Exception e) {
	          System.out.println("예외 : " + e.getMessage());
	      }finally {
	    	  tx.commit();
	          em.close();
	      

	      }

	}
}