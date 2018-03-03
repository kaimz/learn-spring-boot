package com.wuwii.testmongodb;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/3/3 17:14</pre>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PetDaoTest {

    @Autowired
    private PetDao petDao;

    @Test
    public void testFind() {
    }

    @Test
    public void testFindAll() {

    }

    @Test
    public void testAdd() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Tom");
        pet.setSpecies("cat");
        petDao.add(pet);
        Assert.assertThat(pet, Matchers.equalTo(petDao.find(pet.getId())));
    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testDelete() {

    }
}
