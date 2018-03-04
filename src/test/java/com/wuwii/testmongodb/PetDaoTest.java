package com.wuwii.testmongodb;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.DEFAULT)
public class PetDaoTest {

    @Autowired
    private PetDao petDao;

    private Pet pet;

    @Before
    public void befor() {
        pet = new Pet();
        pet.setId(1L);
        pet.setName("Tom");
        pet.setSpecies("cat");
    }


    @Test
    public void testAdd() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Tom");
        pet.setSpecies("cat");
        petDao.add(pet);
    }

    @Test
    public void testFind() {
        Assert.assertThat(pet, Matchers.equalTo(petDao.find(pet.getId())));
    }

    @Test
    public void testFindAll() {
        System.out.println(petDao.findAll());
    }

    @Test
    public void testDelete() {
        petDao.delete(pet.getId());
        Assert.assertThat(null, Matchers.equalTo(petDao.find(pet.getId())));
    }

    @Test
    public void testUpdate() {
        testAdd();
        pet.setName("KronChan");
        petDao.update(pet);
        Assert.assertThat(pet, Matchers.equalTo(petDao.find(pet.getId())));
    }
}
