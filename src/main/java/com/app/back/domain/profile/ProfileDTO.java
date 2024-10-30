package com.app.back.domain.profile;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Long id;
    private String profileFileName;
    private String profileFilePath;
    private String profileFileSize;
    private String profileFileType;
    private String memberId;
    private String createdDate;

    public ProfileVO toVO(){
        return new ProfileVO(id, profileFileName, profileFilePath, profileFileSize, profileFileType, memberId, createdDate);
    }
}