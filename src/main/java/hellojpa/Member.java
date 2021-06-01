package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 테이블과 매핑한다고 JPA에게 알려줌, 엔티티클래스라고함.
@Table(name="MEMBER") // Table 정보를 알려줌.
public class Member {

    @Id // 기본키에 매핑하며 식별자 필드라고 부름.
    private long id;
    private String name;

    public Member() {}

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
