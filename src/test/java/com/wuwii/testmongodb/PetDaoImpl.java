package com.wuwii.testmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/3/3 12:30</pre>
 */
@Repository
public class PetDaoImpl implements PetDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Pet find(Long id) {
        return mongoTemplate.findById(id, Pet.class);
    }

    @Override
    public List<Pet> findAll() {
        return mongoTemplate.findAll(Pet.class);
    }

    @Override
    public void add(Pet pet) {
        mongoTemplate.insert(pet);
    }

    @Override
    public void update(Pet pet) {
        Query query = new Query();
        Criteria criteria = new Criteria("id");
        criteria.is(pet.getId());
        query.addCriteria(criteria);
        Update update = new Update();
        update.set("pet_name", pet.getName())
                .set("species", pet.getSpecies());
        mongoTemplate.updateFirst(query, update, Pet.class); // 条件，更新的数据，更新的类型
    }

    @Override
    public void delete(Long id) {
        Criteria criteria = new Criteria("id");
        criteria.is(id);
        Query query = new Query();
        query.addCriteria(criteria);
        mongoTemplate.remove(query, Pet.class); // 删除的条件、删除的类型
    }
}
