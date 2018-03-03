package com.wuwii.testmongodb;

import java.util.List;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/3/3 12:28</pre>
 */
public interface PetDao {
    Pet find(Long id);

    List<Pet> findAll();

    void add(Pet pet);

    void update(Pet pet);

    void delete(Long id);
}
