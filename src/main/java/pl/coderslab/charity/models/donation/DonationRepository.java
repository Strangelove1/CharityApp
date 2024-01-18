package pl.coderslab.charity.models.donation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findDonationByUser_Id(Long userId);
}
