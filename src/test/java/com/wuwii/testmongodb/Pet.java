package com.wuwii.testmongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 注解表明转换方式
 *
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/3/3 12:25</pre>
 */
@Data
@Document(collection = "pet") // 标识要持久化到MongoDB的域对象。模型名是 pet
public class Pet implements Serializable {
    @Id
    //@Indexed(unique = true) // 使用MongoDB的索引特性标记一个字段
    private Long id;
    @Field("pet_name") //自定义设置对应MongoDB中的key
    private String name;
    private String species;
}
