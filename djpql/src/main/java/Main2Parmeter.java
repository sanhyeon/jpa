import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.domain.Board;


public class Main2Parmeter {

	public static void main(String[] args) {
		
		// board 테이블에서 1번 레코드를 화면에 출력
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("djpql");
		try {
			
			//selectData(emf);
			//deleteData(emf);
			
			// 7788 사원의 월급을 2000 변경
			updateData(emf);
		}catch(Exception ex) {
			System.out.println("예외:" + ex.getMessage());
		}finally {
			emf.close();
		}
	}
	static void updateData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		//JPQL
		String jpql = "UPDATE Employee e SET e.sal=:sal WHERE empno=:empkw";
		Query query = em.createQuery(jpql);
		query.setParameter("empkw", 7788);
		query.setParameter("sal", 2000);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		int result = query.executeUpdate();
		
		tx.commit();
		em.close();
	}
	
	static void deleteData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		// JPQL
		String jpql = "DELETE Employee e WHERE e.empno=:emp_no";	//sql 문장 아님
		Query query = em.createQuery(jpql);
		query.setParameter("emp_no", 7934);
		
		// 실행
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		int result = query.executeUpdate();
		System.out.println(result + "행을 수행");
		tx.commit();
		em.close();
	}
	
	static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		// ############## JPQL
		// [1] 검색결과를 특정할 수 있는 경우 : TypedQuery
//		String jpql = "SELECT b FROM Board AS b";
//		TypedQuery<Board> query = em.createQuery(jpql, Board.class);
//		List<Board> list=query.getResultList();
//		for(Board result : list) {
//			System.out.println(">>" + result );
//		}
		// [1] 파라메터 바인딩 (위치인자)
//		String jpql = "SELECT seq,write,title FROM Board  " + " WHERE seq=?1 AND title=?2";
//		Query query = em.createQuery(jpql);
//		query.setParameter(1, 1);
//		query.setParameter(2, "spring mybatis");
//		List<Object[]> list = query.getResultList();
//		for(Board result : list) {
//			System.out.println(">>" + Arrays.toString(result) );
//		}
		
		// [1] 파라메터 바인딩 (위치인자)
		String jpql = "SELECT seq,write,title FROM Board  " + " WHERE seq=:seqkw AND title=:titlekw";
		Query query = em.createQuery(jpql);
		query.setParameter("seqkw", 1);
		query.setParameter("titlekw", "spring mybatis");
		List<Object[]> list = query.getResultList();
		for(Object[] result : list) {
			System.out.println(">>" + Arrays.toString(result) );
		}
		List<Object[]> list2 = query.getResultList();
		for(Object[] result : list2) {
			System.out.println(">>" + Arrays.toString(result) );
		}
		
		if(list==list2)System.out.println("동일객체");
		else System.out.println("다른객체");
		
		em.close();
	}
}
