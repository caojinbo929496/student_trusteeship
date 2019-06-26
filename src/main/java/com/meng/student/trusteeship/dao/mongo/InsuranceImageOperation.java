package com.meng.student.trusteeship.dao.mongo;


import com.meng.student.trusteeship.entity.mongo.InsuranceResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class InsuranceImageOperation {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public InsuranceImageOperation(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void save(InsuranceResources insuranceResources) {
        mongoTemplate.save(insuranceResources);
    }

    public void delete(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, InsuranceResources.class);
    }

    public InsuranceResources get(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, InsuranceResources.class);
    }

}
