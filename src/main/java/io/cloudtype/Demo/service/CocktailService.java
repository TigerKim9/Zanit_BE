package io.cloudtype.Demo.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import io.cloudtype.Demo.dto.CocktailDTO;
import io.cloudtype.Demo.entity.Cocktail;
import io.cloudtype.Demo.repository.CocktailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CocktailService {
	
	private final CocktailRepository cocktailRepository;
	

	private final AmazonS3 amazonS3;
	
	private final ImageS3Service imageS3Service;
	
//	@Value("${cloud.aws.s3.bucket}")
	@Value("zanit")
	private String bucket;
	
	
	

	//칵테일 전체 조회
	public List<CocktailDTO> cocktailList() {
		List<Cocktail> cocktailAllList = cocktailRepository.findAll();
		List<CocktailDTO> cocktailList = new ArrayList<>();
		
//		if(cocktailAllList.isEmpty()) return null;
		
		for(Cocktail cocktails : cocktailAllList) {
			cocktailList.add(cocktails.toDto());
		}
		
		return cocktailList;
	}
	
	// 칵테일 검색
	public List<CocktailDTO> searchCocktails(String cocktailName) {
		List<Cocktail> cocktailSearchList = cocktailRepository.findByCocktailNameContaining(cocktailName);
		List<CocktailDTO> cocktailList = new ArrayList<>();
		
		for(Cocktail cocktails : cocktailSearchList) {
			cocktailList.add(cocktails.toDto());
		}
		
		return cocktailList;
	}
	
	//바에 있는 칵테일 목록
	public List<CocktailDTO> cocktailsByBar(Long barUid) {
		List<Cocktail> cocktailbyBarList = cocktailRepository.findByBarUid(barUid);
		List<CocktailDTO> cocktailList = new ArrayList<>();
		for(Cocktail cocktails : cocktailbyBarList) {
			cocktailList.add(cocktails.toDto());
		}
		return cocktailList;
	}
	
	public Long registCocktail(CocktailDTO cocktailDTO) {
		Cocktail cocktail = new Cocktail();
		try {
			MultipartFile file = cocktailDTO.getCocktailPic();
			
			String fileName = file.getOriginalFilename();
			//S3 버킷 + 폴더명
			fileName = imageS3Service.changedImageName(fileName);
			String buckets = bucket + "/barPics";
			String fileUrl = "https://" + buckets + "s3." + "https://zanit.s3.ap-northeast-2.amazonaws.com/" + fileName;
			
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			metadata.setContentLength(file.getSize());
			
			amazonS3.putObject(buckets, fileName, file.getInputStream(), metadata);
			String result = amazonS3.getUrl(buckets, fileName).toString();
			cocktailDTO.setCocktailPicPath(result);
			cocktail = cocktailDTO.toEntity();
			cocktailRepository.save(cocktail);
			Long cocktailId = cocktailRepository.save(cocktail).getCocktailUid();
			return cocktailId;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0l;
	}
}








