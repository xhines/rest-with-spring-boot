package com.example.service;

import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.DozerMapper;
import com.example.mapper.custom.PersonMapper;
import com.example.model.Person;
import com.example.repositories.PersonRepository;
import com.example.vo.v1.PersonVO;
import com.example.vo.v2.PersonVOV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;
    @Autowired
    private PersonMapper personMapper;

    public List<PersonVO> findAll() {

        logger.info("finding all persons");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("finding one person");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {

        logger.info("creating a person");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("creating a person with V2 ");

        var entity = personMapper.convertVoToEntity(person);
        var vo = personMapper.converEntityToV2(repository.save(entity));

        return vo;
    }

    public PersonVO update(PersonVO person) {

        logger.info("updating a person");

        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {

        logger.info("deleting a person");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        repository.delete(entity);
    }
}
