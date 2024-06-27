package com.ddf.ingestion_ddf.repository;

import com.ddf.ingestion_ddf.entity.IngestionRequestDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link IngestionRequestDetails} entities.
 */
public interface IngestionRequestDetailsRepository extends JpaRepository<IngestionRequestDetails, Long> {

    /**
     * Retrieves a paginated list of {@link IngestionRequestDetails} entities where the status name is in the provided list
     * and the active flag matches the given value.
     *
     * @param statusNames the list of status names to filter by
     * @param activeFlag the active flag to filter by
     * @param pageable the pagination information
     * @return a paginated list of matching {@link IngestionRequestDetails} entities
     */
    Page<IngestionRequestDetails> findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(List<String> statusNames, Boolean activeFlag, Pageable pageable);

    /**
     * Counts the number of {@link IngestionRequestDetails} entities where the status name is in the provided list
     * and the active flag matches the given value.
     *
     * @param statusNames the list of status names to filter by
     * @param activeFlag the active flag to filter by
     * @return the count of matching {@link IngestionRequestDetails} entities
     */
    int countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlag(List<String> statusNames, Boolean activeFlag);

    //We can use below methods instead of above methods for the Logged-in User ID as it is My-Submission
//    Page<IngestionRequestDetails> findByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlagAndCreatedBy(List<String> statusNames, Boolean ActiveFlag,String createdBy, Pageable pageable);
//    int countByRequestStatusDetailsStatusStatusNameInAndRequestStatusDetailsActiveFlagAndCreatedBy(List<String> statusNames,String createdBy, Boolean ActiveFlag);


}
