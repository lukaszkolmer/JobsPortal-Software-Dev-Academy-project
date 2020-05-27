package com.lukaszkolmer.jobsportal.jobs.repository;

import com.lukaszkolmer.jobsportal.jobs.Exceptions.NoOfferOfGivenID;
import com.lukaszkolmer.jobsportal.jobs.model.JobDetails;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class JobDetailsRepositoryImpl {

    private JobDetailsRepository jobDetailsRepository;

    @Autowired
    public JobDetailsRepositoryImpl(JobDetailsRepository jobDetailsRepository) {
        this.jobDetailsRepository = jobDetailsRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setDatabase() {
        JobDetails testJobDetails = new JobDetails("Test offer", "Test descritpion", "Test resposibilities", "Test qualifications",
                "Test benefits", "Test location",
                "Test 0 - 9001 salary", "Test vacancy", LocalDate.of(2000, 1, 1), "Test job nature");
        JobDetails testJobDetails2 = new JobDetails("Test2 offer", "Test2 descritpion", "Test2 resposibilities", "Test2 qualifications",
                "Test2 benefits", "Test2 location",
                "Test2 0 - 9001 salary", "Test2 vacancy", LocalDate.of(1995, 11, 11), "Test2 job nature");
        JobDetails testJobDetails3 = new JobDetails("Test offer", "Test3 descritpion", "Test3 resposibilities", "Test3 qualifications",
                "Test3 benefits", "Test3 location",
                "Test3 0 - 9001 salary", "Test3 vacancy", LocalDate.of(1889, 12, 31), "Test3 job nature");
        jobDetailsRepository.save(testJobDetails);
        jobDetailsRepository.save(testJobDetails2);
        jobDetailsRepository.save(testJobDetails3);
    }

    public JobDetails findOfferById(Long id) {
        return jobDetailsRepository.findById(id).orElseThrow(() -> new NoOfferOfGivenID(id));
    }

    public JobDetails addNewJobOffer(JobDetails jobOfferToAdd) {
        jobDetailsRepository.save(jobOfferToAdd);
        return jobOfferToAdd;
    }

    public JobDetails removeJobOffer(JobDetails offerToRemove) {
        jobDetailsRepository.delete(offerToRemove);
        return offerToRemove;
    }
}
