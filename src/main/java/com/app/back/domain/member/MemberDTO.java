package com.app.back.domain.member;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO{
    @EqualsAndHashCode.Include
    private Long id;
    private String kakaoEmail;
    private String kakaoProfile;
    private String kakaoNickName;
    private String memberEmail;
    private String memberName;
    private String memberPhone;
    private String memberPassword;
    private int memberType;
    private String memberNickName;
    private int memberJung;
    private int memberPoint;
    private int memberLoginType;
    private float memberStarRate;
    private String memberIntroduction;
    private String createdDate;
    private String updatedDate;

    public MemberVO toVO(){
        return new MemberVO(id,kakaoEmail,kakaoProfile,kakaoNickName,memberEmail,memberName,memberPhone,memberPassword,memberType,memberNickName,memberJung,memberPoint,memberLoginType,memberStarRate,memberIntroduction,createdDate,updatedDate);}
}