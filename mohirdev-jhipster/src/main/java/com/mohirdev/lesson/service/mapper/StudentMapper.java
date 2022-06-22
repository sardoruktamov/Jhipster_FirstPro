package com.mohirdev.lesson.service.mapper;

import com.mohirdev.lesson.domain.Student;
import com.mohirdev.lesson.service.dto.StudentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {

    // address dan kelgan malumotni studentAddress ga o'tkazib qoyamiz
    @Mapping(source = "address", target = "studentAddress")
    StudentDTO toDto(Student student);


    // studentAddress dan kelgan malumotni address ga o'tkazib qoyamiz
    @Mapping(source = "studentAddress", target = "address")
    Student toEntity(StudentDTO studentDTO);


}
