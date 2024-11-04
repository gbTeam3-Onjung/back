package com.app.back.service.donation;

import com.app.back.domain.donation.DonationDTO;
import com.app.back.domain.post.Pagination;
import com.app.back.domain.post.PostVO;
import com.app.back.mapper.donation.DonationMapper;
import com.app.back.mapper.post.PostMapper;
import com.app.back.repository.attachment.AttachmentDAO;
import com.app.back.repository.donation.DonationDAO;
import com.app.back.repository.post.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service // 이 클래스가 서비스임을 나타냄
@Primary // 우선 순위가 높은 서비스
@RequiredArgsConstructor // 생성자 자동 생성
@Transactional(rollbackFor = Exception.class) // 예외 발생 시 롤백 처리
public class DonationServiceImpl implements DonationService {
    private final DonationDAO donationDAO;
    private final PostDAO postDAO; // 게시글 DAO
    private final AttachmentDAO attachmentDAO;

    @Override
    public void write(DonationDTO donationDTO, List<String> uuids, List<String> paths, List<String> sizes, List<MultipartFile> files) throws IOException {
        postDAO.save(donationDTO.toPostVO());
        Long id = postDAO.selectCurrentId();
        donationDTO.setId(id);
        donationDTO.setPostId(id);
        donationDAO.save(donationDTO.toVO());
        if(files != null) {
            for(int i=0; i<files.size(); i++){
                donationDTO.setAttachmentFileName(uuids.get(i) + "_" + files.get(i).getOriginalFilename());
                donationDTO.setAttachmentFilePath(paths.get(i));
                donationDTO.setAttachmentFileSize(String.valueOf(sizes.get(i)));
                donationDTO.setAttachmentFileType(files.get(i).getContentType());
                attachmentDAO.save(donationDTO.toAttachmentVO());
            }
        }
//        if(donationDTO.getAttachmentFileName() != null && donationDTO.getAttachmentFilePath() != null && donationDTO.getAttachmentFileType() != null && donationDTO.getAttachmentFileSize() != null) {
//            attachmentDAO.save(donationDTO.toAttachmentVO());
//        }
    }

    @Override
    public Optional<DonationDTO> getById(Long id) {
        return donationDAO.findById(id);
    }

    @Override
    public List<DonationDTO> getList(Pagination pagination) {
        return donationDAO.findAll(pagination);
    }

    @Override
    public List<DonationDTO> getFilterList(Pagination pagination) { return donationDAO.findFilterAll(pagination);}

    @Override
    public int getTotal() {
        return donationDAO.findCount();
    }

    @Override
    public void update(DonationDTO donationDTO) {
        donationDAO.update(donationDTO);
    }

    @Override
    public void delete(Long id) {
        donationDAO.delete(id);
    }

    @Override
    public List<DonationDTO> findByMemberId(Long memberId) {
        return donationDAO.findByMemberId(memberId);
    }

    @Override
    public List<DonationDTO> findByMemberIdAndDateRange(Long memberId, String startDate, String endDate) {
        return donationDAO.findByMemberIdAndDateRange(memberId, startDate, endDate);
    }
}
