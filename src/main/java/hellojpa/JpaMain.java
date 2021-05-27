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

        // 스레드간에 공유 X (사용하고 버려야함)
        EntityManager em = emf.createEntityManager();

        // EntityTransaction : JPA의 모든 데이터 변경은 트랜잭션 안에서 실행됨.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // Member 저장
            Member member = new Member();
            member.setId(1);
            member.setName("doyeon");
            em.persist(member);

            // 조회, 조회할 엔티티 타입과 @Id로 데이터베이스 테이블의 기본키와 매핑한 식별자 값으로 엔티티 조회.
            Member findMember = em.find(Member.class, 1L);
            System.out.println(findMember.getId() + ", " + findMember.getName());

            // 수정, JPA는 어떤 엔티팉가 변경되었는지 추적하는 기능을 갖추고 있어서,
            // 엔티티의 값만 변경되면 다음과 같은 update sql을 생성해서 데이터베이스에서 값을 변경.
            findMember.setName("hello jpa");

            // 삭제
            em.remove(findMember);

            // JPA는 SQL을 추상화한 JPQL 이라는 객체지향 쿼리 언어 제공
            // JPQL 은 엔티티 객체를 대상으로 쿼리, SQL은 데이터베이스 테이블을 대상으로 쿼리
            // 방언을 바꾸거나 해도 JPQL을 변경할 필요 없음.
            // Paging 처리하는데 좋음.
            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

            List<Member> result2 = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
