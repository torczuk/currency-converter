package com.github.torczuk.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import java.util.Date;

@Entity(name = "dictionary")
public class Dictionary extends AbstractEntity implements Comparable<Dictionary>, CreationDateAware {

    @Column(name = "name") private String name;
    @Column(name = "value") private String value;
    @Column(name = "code") private String code;
    private Date created;

    public Dictionary() {
    }

    public Dictionary(String name, String code, String value) {
        this.name = name;
        this.value = value;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @PostLoad
    protected void onCreate() {
        created = new Date();
    }

    public Date creationDate() {
        return created;
    }

    @Override public int compareTo(Dictionary o) {
        if (name.equals(o.getName())) {
            return this.value.compareTo(o.getValue());
        } else {
            return value.compareTo(o.getName());
        }
    }
}
