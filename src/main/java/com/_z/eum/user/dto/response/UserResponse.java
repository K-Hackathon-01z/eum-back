package com._z.eum.user.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 정보 응답 DTO")
public record UserResponse(

         String email,

         String name,

         int age,

         String gender,

         String address
)
{ }
