import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.domain.Board;


public class Main3Join {

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
		// [1] 묵시적인 조인
		//String jpql = "SELECT e, e.dept FROM Employee e";
		// [2] 명시적인 조인
		String jpql = "SELECT e, d FROM Employee e INNER JOIN e.dept d";
		Query query = em.createQuery(jpql);
		// [3] 페이징 처리
		int pageNumber = 2;
		int pageSize = 3;
		int startNum = pageNumber * pageSize - pageSize;
		query.setFirstResult(startNum);
		query.setMaxResults(pageSize);
		
		List<Object[]> list = query.getResultList();
		for(Object[] result : list) {
			System.out.println(">>" + Arrays.toString(result) );
		}
		em.close();
	}
}
