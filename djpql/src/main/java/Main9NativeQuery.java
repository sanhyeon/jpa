import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.domain.Employee;


public class Main9NativeQuery {

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
		
		//-------------------------
		// 기본의 SQL 사용 ( jpql 아님)
		String sql = "SELECT * FROM emp_a where deptno=:dept_no";
		Query query =  em.createNativeQuery(sql, Employee.class);
		query.setParameter("dept_no", 20);
		List<Employee> list = query.getResultList();
		for(Employee result : list) {
			System.out.println(">>" + result );
		}
		em.close();
	}
}
