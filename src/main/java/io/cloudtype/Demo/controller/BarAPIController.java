package io.cloudtype.Demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import io.cloudtype.Demo.dto.BarDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.repository.BarRepository;
import io.cloudtype.Demo.security.CustomUserDetails;
import io.cloudtype.Demo.service.BarService;
import io.cloudtype.Demo.service.CocktailService;
import io.cloudtype.Demo.service.ImageS3Service;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api
@RestController
@Slf4j
@RequiredArgsConstructor
public class BarAPIController {

	private final BarRepository barRepository;
	
	private final CocktailService cocktailService;

	private final BarService barService;
	
	private final AmazonS3 amazonS3;
	
	private final ImageS3Service imageS3Service;
	
	
//	@Value("${cloud.aws.s3.bucket}")
	@Value("zanit")
	private String bucket;

	// 바 하나 클릭시 보여줄 바 정보
	@GetMapping("/barInfo")
	public BarDTO barInfo(
			// @RequestBody
			Long barId) {
		log.info("long barId= {}", barId);
		BarDTO barDTO = barService.barDetail(barId);
		return barDTO;
	}
	
	//메인화면 바 목록 랜덤.
	@GetMapping("/barListHome")
	public List<BarDTO> barListHome(){
		List<BarDTO> bars = barService.randomBarList();
		return bars;
	}
	
	//검색별 바 목록
	@GetMapping("/barList")
	public List<BarDTO> barList(
			@RequestParam(defaultValue = "", required = false) String barMood, 
			@RequestParam(defaultValue = "", required = false) String barName,
			@RequestParam(defaultValue = "", required = false) String barLocation) {
		
		List<BarDTO> bars = barService.barList();
		return bars;

	}
	
	@PostMapping("/registBar1")
    public Long registBar1(
            @RequestParam("barName") String barName,
            @RequestParam("barLocation") String barLocation,
            @RequestParam("barMood") String barMood,
            @RequestParam("coverCharge") String coverCharge,
            //TODO 바 쿠폰 사용가능시간...
            @RequestParam("barContents") String barDetail,
            @RequestParam("barPics") List<MultipartFile> barPics,
            @AuthenticationPrincipal CustomUserDetails user) throws Exception {
		

		BarDTO barDTO = BarDTO.builder()
						.barName(barName)
						.barMood(barMood)
						.barLocation(barLocation)
						.coverCharge(coverCharge)
						.barDetail(barDetail)
						.barOwner(user.getUser().getUserUid())
						.barPics(barPics)
						.build();
		
		Long barId = barService.registBar(barDTO);
		
		
//		Bar bar = Bar.builder()
//        		.barName(barName)
//        		.barLocation(barLocation)
//        		.barMood(barMood)
//        		.coverCharge(coverCharge)
//        		.barDetail(barDetail)
//        		.build();
//        Long barId = barRepository.save(bar).getBarUid();
        

        return barId;
    }
	
	
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			fileName = imageS3Service.changedImageName(fileName);
			//S3 버킷 + 폴더명
			String buckets = bucket + "/barPics";
			String fileUrl = "https://" + buckets + "s3." + "https://zanit.s3.ap-northeast-2.amazonaws.com/" + fileName;
			
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			metadata.setContentLength(file.getSize());
			
			amazonS3.putObject(buckets, fileName, file.getInputStream(), metadata);
			String result = amazonS3.getUrl(buckets, fileName).toString();
			return ResponseEntity.ok(result);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}