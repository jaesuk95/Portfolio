package com.portfolio.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUtils {

    public static String getThumbnailVersion(String imagePath) {
        Assert.hasText(imagePath, "이미지경로는 필수입니다.");

        String ext = FilenameUtils.getExtension(imagePath);
        Assert.hasText(ext, "이미지 '" + imagePath + "' 확장자가 없습니다.");

        return FilenameUtils.removeExtension(imagePath) + ".thumbnail." + ext;
    }

    /***
     * \
     * @param contentType 이미지 파일은 "image/jpeg"로 나옴
     * @return 이미지면 true
     */
    public static boolean isImage(String contentType) {
        if (contentType == null) {
            return false;
        }
        if (contentType.startsWith("image/svg")){
            return false;
        }
        return contentType.startsWith("image/");
    }

}

