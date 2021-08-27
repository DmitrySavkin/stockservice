package ru.savkin.model.stocks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

//@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class TestDocPerson {


    @Id
    private String id;

    private String name;
}
