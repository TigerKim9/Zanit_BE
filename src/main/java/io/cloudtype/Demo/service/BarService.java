package io.cloudtype.Demo.service;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.cloudtype.Demo.dto.BarDTO;
import io.cloudtype.Demo.dto.CocktailDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.BarPicDTO;
import io.cloudtype.Demo.entity.Cocktail;
import io.cloudtype.Demo.repository.BarRepository;
import io.cloudtype.Demo.repository.CocktailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class BarService {

	private final BarRepository barRepository;

	private final CocktailRepository cocktailRepository;

	// 바 전체 조회
	public List<BarDTO> barList() {
		List<Bar> BarAllList = barRepository.findAll();
		List<BarDTO> BarList = new ArrayList<>();

//		if(BarAllList.isEmpty()) return null;

		for (Bar Bars : BarAllList) {
			BarList.add(Bars.toDto());
		}

		return BarList;
	}

	// 바 검색
	public List<BarDTO> searchBars(String barName) {
		List<Bar> BarSearchList = barRepository.findByBarNameContaining(barName);
		
		List<BarDTO> BarList = new ArrayList<>();

		for (Bar Bars : BarSearchList) {
			BarList.add(Bars.toDto());
		}

		return BarList;
	}
	
	//하나의 바 정보(관리자단)
	public BarDTO oneBar(Long barUid) {
		BarDTO barDTO = barRepository.findById(barUid).get().toDto();
		
		List<Cocktail> cocktailbyBarList = cocktailRepository.findByBarUid(barUid);
		List<CocktailDTO> cocktailList = new ArrayList<>();
		for (Cocktail cocktails : cocktailbyBarList) {
			cocktailList.add(cocktails.toDto());
		}
		barDTO.setBarsCocktail(cocktailList);
		return barDTO;
	}

	//
	// 칵테일 내용이 포함 된 특정 바(유저)
	public BarDTO barDetail(Long barUid) {
		log.info("barUid = {}",barUid);
		Bar bar = barRepository.findById(barUid).get();
		log.info("bar = {}",bar);
//		List<Cocktail> cocktailbyBarList = cocktailRepository.findTop5ByBarUidAndActivatedTrue(barUid);
//		List<CocktailDTO> cocktailList = new ArrayList<>();
//		for (Cocktail cocktails : cocktailbyBarList) {
//			cocktailList.add(cocktails.toDto());
//		}
		BarDTO barDTO = bar.toDto();
//		barDTO.setBarsCocktail(cocktailList);
		return barDTO;
	}
	//admin 바 등록 
	public Bar barRegist(BarDTO barDTO) throws FileNotFoundException,IOException {
		String uploadPath = "/src/main/resources/img/";
		
		
		List<MultipartFile> barpics = barDTO.getBarPics();
		
		
		for(MultipartFile barpic : barpics) {
			
			String originalName = barpic.getOriginalFilename();
			
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            String uuid = UUID.randomUUID().toString();

            String savefileName = uploadPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(savefileName);

            try {
                barpic.transferTo(savePath);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
			
//			InputStream inputStream = barpic.getInputStream();
//			int read = inputStream.read();
//			log.info("read = {}",read);
//			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//			Reader reader = new InputStreamReader(inputStream);
//			BufferedReader bufferedReader = new BufferedReader(reader);
			
		}
		
		
		return barRepository.save(barDTO.toEntity());

	}
}









