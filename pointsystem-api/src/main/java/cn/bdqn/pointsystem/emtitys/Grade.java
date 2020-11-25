package cn.bdqn.pointsystem.emtitys;

/**
 * @Classname Grade
 * @Description 班级表
 * @Date 2019/12/27 18:22
 * @Created by wxz
 */
public class Grade {
    private Integer id;
    private String name;
    private Integer point;


    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", point=" + point +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
