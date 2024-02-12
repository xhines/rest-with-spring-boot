package com.example.mapper.custom;

import java.util.Date;

import com.example.model.Person;
import com.example.vo.v2.PersonVOV2;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {

    public PersonVOV2 converEntityToV2(Person person) {
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setBirthDay(new Date());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVoToEntity(PersonVOV2 personVOV2) {
        Person entity = new Person();
        entity.setId(personVOV2.getId());
        entity.setFirstName(personVOV2.getFirstName());
        entity.setLastName(personVOV2.getLastName());
        entity.setAddress(personVOV2.getAddress());
        entity.setGender(personVOV2.getGender());
        return entity;
    }
}
