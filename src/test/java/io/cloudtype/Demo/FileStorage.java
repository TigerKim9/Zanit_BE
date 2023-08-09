package io.cloudtype.Demo;

import java.awt.Image;
import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

public class FileStorage {

	@Test
	public void 넘어온_파일을_저장한다(MultipartFile file) {
		
//		try {
//			String sourceFileName = file.getOriginalFilename();
//			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
//			String fileUrl = "이미지 저장 주소";
//			String destinationFileName = RandomStringUtils.randomAlphabetic(32) + "." + sourceFileNameExtension;
//			File destinationFile = new File(fileUrl + destinationFileName);
//			destinationFile.getParentFile().mkdirs();
//			file.transferTo(destinationFile);
//			Image image = new Image(destinationFileName, sourceFileName, fileUrl);
//			return ImageDto.builder().map(getDto("isFileInserted", true, "uploadStatus", "AllSuccess")).content(image)
//					.build();
//		} catch (Exception e) {
//			return ImageDto.builder().map(getDto("isFileInserted", false, "uploadStatus", "FileIsNotUploaded"))
//					.content(null).build();
//		}
//
	}
}
