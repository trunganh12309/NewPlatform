package com.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{


    private static final long serialVersionUID = -2326365995797050386L;
    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String code;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private List<UserEntity> users = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
