package com.wuwii.testmongodb;

import org.hamcrest.Matchers;
import org.junit.*;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetDaoTest {

    @Autowired
    private PetDao petDao;

    private Pet pet;

    @Before
    public void before() {
        pet = new Pet();
        pet.setId(1L);
        pet.setName("Tom");
        pet.setSpecies("cat");
    }

    @After
    public void after() {
    }

    @Test
    public void test01Add() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Tom");
        pet.setSpecies("cat");
        petDao.add(pet);
    }

    @Test
    public void test02Find() {
        Assert.assertThat(pet, Matchers.equalTo(petDao.find(pet.getId())));
    }

    @Test
    public void test03FindAll() {
        System.out.println(petDao.findAll());
    }

    @Test
    public void test04Update() {
        pet.setName("KronChan");
        petDao.update(pet);
        Assert.assertThat(pet, Matchers.equalTo(petDao.find(pet.getId())));
    }

    @Test
    public void test05Delete() {
        petDao.delete(pet.getId());
        Assert.assertThat(null, Matchers.equalTo(petDao.find(pet.getId())));
    }

}
