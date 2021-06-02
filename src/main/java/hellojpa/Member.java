package hellojpa;

import javax.persistence.*;

@Entity
@Table(name="Member")
public class Member {

    @Id
    @GeneratedValue // 식별자 자동생성해주는 annotation으로 기본생성 전략은 AUTO, 선택한 방언에 따라 IDENTIFY, SEQUENCE, TABLE  중 하나 선택
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
