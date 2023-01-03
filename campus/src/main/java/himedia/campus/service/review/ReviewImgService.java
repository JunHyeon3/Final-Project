package himedia.campus.service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.entity.review.ReviewImg;
import himedia.campus.repository.review.ReviewImgRepository;
import himedia.campus.service.FileService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewImgService {

	@Value("${reviewImgLocation}")
	private String reviewImgLocation;

	private final ReviewImgRepository reviewImgRepository;

	private final FileService fileService;

	public void saveReviewImg(ReviewImg reviewImg, MultipartFile reviewImgFile) throws Exception  {
		String reviewImgOriginal = reviewImgFile.getOriginalFilename();
		String reviewImgName = "";
		String reviewImgPath = "";
		
		if (!reviewImgOriginal.isEmpty()) {
			reviewImgName = fileService.uploadFile(reviewImgLocation, reviewImgOriginal, reviewImgFile.getBytes());
			reviewImgPath = "/campus/review/" + reviewImgName;
		}

		reviewImg.updateReviewImg(reviewImgOriginal, reviewImgName, reviewImgPath);
		reviewImgRepository.save(reviewImg);
	}
	
	public void updateReviewImg(ReviewImg reviewImg, MultipartFile reviewImgFile) throws Exception  {
		
		if(!reviewImgFile.isEmpty()) {
            if (!reviewImg.getReviewImgName().isEmpty()) {
                fileService.deleteFile(reviewImgLocation + "/" + reviewImg.getReviewImgName());
            }

            String reviewImgOriginal = reviewImgFile.getOriginalFilename();
            String reviewImgName = fileService.uploadFile(reviewImgLocation, reviewImgOriginal, reviewImgFile.getBytes());
            String reviewImgPath = "/campus/review/" + reviewImgName;
            reviewImg.updateReviewImg(reviewImgName, reviewImgOriginal, reviewImgPath);
        }
	}

	public void deleteCampsiteImg(ReviewImg reviewImg) throws Exception {
		reviewImgRepository.delete(reviewImg);
		fileService.deleteFile(reviewImgLocation + "/" + reviewImg.getReviewImgName());
	}

	public List<ReviewImg> findByReviewId(Long reviewId) {
		return reviewImgRepository.findByReview_ReviewId(reviewId);
	}
	
}
