package com.meng.student.trusteeship.entity.mongo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "insuranceImage")
public class InsuranceResources {

    @Id
    private String id;

    private List imageResources;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getImageResources() {
        return imageResources;
    }

    public void setImageResources(List imageResources) {
        this.imageResources = imageResources;
    }
}
