import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.domain.Board;


public class Main1Basic {

	public static void main(String[] args) {
		
		// board 테이블에서 1번 레코드를 화면에 출력
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("djpql");
		try {
			// [1] 연관관계를 이용한 데이터 검색
			selectData(emf);
			
			// [2] 연관관계를 이용한 데이터 입력
			//insertData(emf);
			
			// [3] 연관관계를 이용한 데이터 수정
			//updateData(emf);
			
			// [4] 연관관계를 이용한 데이터 삭제
			//deleteData(emf);
		}catch(Exception ex) {
			System.out.println("예외:" + ex.getMessage());
		}finally {
			emf.close();
			
		}
		
	}

	static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		// ############## JPQL
		// [1] 검색결과를 특정할 수 있는 경우 : TypedQuery
		//String jpql = "SELECT b FROM Board AS b";
		//TypedQuery<Board> query = em.createQuery(jpql, Board.class);
		//List<Board> list=query.getResultList();
		//for(Board result : list) {
		//	System.out.println(">>" + result );
		//}
		// [1] 검색결과를 특정할 수 없는 경우 : Query
		String jpql = "SELECT seq,write,title FROM Board AS b";
		Query query = em.createQuery(jpql);
		List<Object[]> list = query.getResultList();
		for(Object[] result : list) {
			System.out.println(">>" + Arrays.toString(result) );
		}
		em.close();
	}
}
