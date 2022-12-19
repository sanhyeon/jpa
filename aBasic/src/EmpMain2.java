import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO2;

public class EmpMain2 {

	public static void main(String[] args) {
		
		// 1. 엔티티매니저팩토리 생성
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("aBasic");
		
		// 2. 엔티티매니저
		EntityManager em = emf.createEntityManager();
		
		// 4. 엔티티트랜잭션 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			// 3. 엔티티 생성
			EmpVO2 vo =  new EmpVO2();
			//vo.setEmpno(9995);
			vo.setEname("홍파이");
			vo.setDeptno(87);
			
			// 4. 트랜잭션 처리
			tx.begin();
			em.persist(vo);
			tx.commit();
		}catch(Exception ex) {
				System.out.println("실패:"+ ex.getMessage());
				tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}

}