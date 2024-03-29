package com.hqh.quizserver.mapper;

import com.hqh.quizserver.entity.User;
import com.hqh.quizserver.entity.UserReport;
import com.hqh.quizserver.dto.UserReportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserReportMapper {
    UserReportMapper INSTANCE = Mappers.getMapper(UserReportMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "comment", ignore = true)
    @Mapping(target = "user", source = "user")
    UserReport map(UserReportDto userReportDto, User user);

    @Mapping(target = "userId", expression = "java(userReport.getUser().getId())")
    UserReportDto mapToDto(UserReport userReport);
}
