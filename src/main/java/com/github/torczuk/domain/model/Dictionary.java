package com.github.torczuk.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "dictionary")
public class Dictionary extends AbstractEntity implements Comparable<Dictionary> {

    @Column(name = "name") private String name;
    @Column(name = "value") private String value;

    public Dictionary() {
    }

    public Dictionary(String name, String value) {
        this.name = name;
        this.value = value;
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

    @Override public int compareTo(Dictionary o) {
        if(name.equals(o.getName())) {
            return this.value.compareTo(o.getValue());
        } else {
            return name.compareTo(o.getName());
        }
    }
}
