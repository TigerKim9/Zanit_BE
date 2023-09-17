package io.cloudtype.Demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import io.cloudtype.Demo.dto.BarDTO;
import io.cloudtype.Demo.dto.CocktailDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.BarPic;
import io.cloudtype.Demo.entity.BarPicDTO;
import io.cloudtype.Demo.entity.Cocktail;
import io.cloudtype.Demo.entity.User;
import io.cloudtype.Demo.repository.BarPicRepository;
import io.cloudtype.Demo.repository.BarRepository;
import io.cloudtype.Demo.repository.CocktailRepository;
import io.cloudtype.Demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BarService {

	private final BarRepository barRepository;

	private final CocktailRepository cocktailRepository;

	private final BarPicRepository barPicRepository;
	
	private final UserRepository userRepository;

	private final FileHandler fileHandler;

	private final ImageS3Service imageS3Service;

	private final AmazonS3 amazonS3;

//	@Value("${cloud.aws.s3.bucket}")
	@Value("zanit")
	private String bucket;

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

	public List<BarDTO> randomBarList() {
		List<Bar> bars = barRepository.randomBars();

		List<BarDTO> barList = new ArrayList<>();

		for (Bar Bars : bars) {
			barList.add(Bars.toDto());
		}
		return barList;

	}

	// 바 검색
	public List<BarDTO> searchBars(String barName) {
		List<Bar> barSearchList = barRepository.findByBarNameContaining(barName);

		List<BarDTO> barList = new ArrayList<>();

		for (Bar bars : barSearchList) {
			barList.add(bars.toDto());
		}

		return barList;
	}

	// 하나의 바 정보(관리자단)
	public BarDTO adminsBar(Long userId) {
		BarDTO barDTO = barRepository.findByBarOwner(userId).get().toDto();

		List<Cocktail> cocktailbyBarList = cocktailRepository.findByBarUid(userId);
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
		log.info("barUid = {}", barUid);
		Bar bar = barRepository.findById(barUid).get();
		log.info("bar = {}", bar);
//		List<Cocktail> cocktailbyBarList = cocktailRepository.findTop5ByBarUidAndActivatedTrue(barUid);
//		List<CocktailDTO> cocktailList = new ArrayList<>();
//		for (Cocktail cocktails : cocktailbyBarList) {
//			cocktailList.add(cocktails.toDto());
//		}
		BarDTO barDTO = bar.toDto();
//		barDTO.setBarsCocktail(cocktailList);
		return barDTO;
	}
//	//admin 바 등록 
//	public Bar barRegist(BarDTO barDTO) throws FileNotFoundException,IOException {
//		String uploadPath = "/src/main/resources/img/";
//		
//		
//		List<MultipartFile> barpics = barDTO.getBarPics();
//		
//		
//		for(MultipartFile barpic : barpics) {
//			
//			String originalName = barpic.getOriginalFilename();
//			
//            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
//
//            String uuid = UUID.randomUUID().toString();
//
//            String savefileName = uploadPath + File.separator + uuid + "_" + fileName;
//
//            Path savePath = Paths.get(savefileName);
//
//            try {
//                barpic.transferTo(savePath);
//                
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//			
////			InputStream inputStream = barpic.getInputStream();
////			int read = inputStream.read();
////			log.info("read = {}",read);
////			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
////			Reader reader = new InputStreamReader(inputStream);
////			BufferedReader bufferedReader = new BufferedReader(reader);
//			
//		}

//		return barRepository.save(barDTO.toEntity());
//
//	}

	public Long registBar(BarDTO barDTO) {
		BarPicDTO barPicDTO = new BarPicDTO();
		try {
			
			List<MultipartFile> files = barDTO.getBarPics();
			
			if(!files.isEmpty()) {
				for (MultipartFile file : files) {
					String fileName = file.getOriginalFilename();
					// S3 버킷 + 폴더명
					fileName = imageS3Service.changedImageName(fileName);
					String buckets = bucket + "/barPics";

					ObjectMetadata metadata = new ObjectMetadata();
					metadata.setContentType(file.getContentType());
					metadata.setContentLength(file.getSize());

					amazonS3.putObject(buckets, fileName, file.getInputStream(), metadata);
					String result = amazonS3.getUrl(buckets, fileName).toString();

					barPicDTO.setBarPicture(result);
					BarPic barPic = barPicDTO.toEntity();
					barPicRepository.save(barPic);
				}
				User user = userRepository.findById(barDTO.getBarOwner()).get();
				Bar bar = barDTO.toEntity();
				
				Long barUid = barRepository.save(bar).getBarUid();
				return barUid;
			}else {
				Bar bar = barDTO.toEntity();
				
				Long barUid = barRepository.save(bar).getBarUid();
				return barUid;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0l;
	}
}
