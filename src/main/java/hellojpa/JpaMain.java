package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        // 데이터베이스 연결
        // EntityManagerFactory : 하나만 생성해서 어플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 스레드간에 공유 X (사용하고 버려야함), 보통 하나의 트랜잭션에서 하나 사용됨.
        EntityManager em = emf.createEntityManager();

        // EntityTransaction : JPA의 모든 데이터 변경은 트랜잭션 안에서 실행됨.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 비영속 상태, 객체를 생성한 상태
            Member member = new Member();
            member.setId(2);
            member.setName("doyeon");

            // 영속 상태 : EntityManager를 통해 관리되며, 객체를 저장한 상태, 캐시에 저장됨.
            em.persist(member);

            // 준영속 상태, 영속성 컨텍스트에서 분리
            em.detach(member);

            // 엔티티를 영속성 컨텍스트와 데이터베이스에서 삭제
            em.remove(member);


            // 동일한거 두번 조회되면, 첫번째 쿼리만 실행됨.
            // 1차 캐시에 저장됨.
            Member findMember = em.find(Member.class, 2);
            Member findMember2 = em.find(Member.class, 2);

            // 1차 캐시로 반복가능한 읽기 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공.
            System.out.println(findMember == findMember2);  // 동일성 비교. true

            tx.commit();    // 커밋하는 순간 쿼리 실행됨(쿼리들이 쓰기지연 SQL 저장소에 저장되어있다가 한번에 커밋시점에 한번에 실행됨)
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
