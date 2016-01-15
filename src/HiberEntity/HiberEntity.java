package HiberEntity;

import javax.persistence.*;

/**
 * Created by n.litvyak on 14.01.2016.
 */
@Entity
@Table(name = "Hiber", schema = "dbo", catalog = "LitlleOne")
public class HiberEntity {
    private String age;
    private int count;
    private String name;

    @Basic
    @Column(name = "age", nullable = true, insertable = true, updatable = true)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Basic
    @Column(name = "count", nullable = false, insertable = true, updatable = true)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Id
    @Column(name = "name", nullable = false, insertable = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiberEntity that = (HiberEntity) o;

        if (count != that.count) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = age != null ? age.hashCode() : 0;
        result = 31 * result + count;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return age.trim() + ' ' + count +  ' ' + name.trim();
    }
}
