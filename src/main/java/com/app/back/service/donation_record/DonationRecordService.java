package com.app.back.service.donation_record;

import com.app.back.domain.donation_record.DonationRecordDTO;

import java.util.List;
import java.util.Optional;

public interface DonationRecordService {
    public void save(DonationRecordDTO donationRecordDTO);
    public Optional<DonationRecordDTO> findById(Long id);
    public List<DonationRecordDTO> findAll();
    public void update(DonationRecordDTO donationRecordDTO);
    public void deleteById(Long id);
    public int getTotalDonationByMemberId(Long memberId);

}
