package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.Division;
import framgia.co.edu.ftrr.common.TitleInterview;
import framgia.co.edu.ftrr.dto.request.InterviewDTO;
import framgia.co.edu.ftrr.entity.Interview;
import framgia.co.edu.ftrr.entity.TraineeForRequest;
import framgia.co.edu.ftrr.entity.User;
import framgia.co.edu.ftrr.repository.InterviewRepository;
import framgia.co.edu.ftrr.repository.RequestRepository;
import framgia.co.edu.ftrr.repository.TraineeForRequestRepository;
import framgia.co.edu.ftrr.repository.UserRepository;
import framgia.co.edu.ftrr.service.InterviewService;
import framgia.co.edu.ftrr.util.ImportInterviewExcelUtils;
import framgia.co.edu.ftrr.util.InterviewUtils;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.time.DateTimeException;
import java.util.*;

@Service
public class InterviewServiceImpl implements InterviewService {

    private static final Logger logger = LoggerFactory.getLogger(InterviewServiceImpl.class);

    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TraineeForRequestRepository traineeForRequestRepository;
    @Autowired
    RequestRepository requestRepository;

    @Override
    @Transactional
    public List<InterviewDTO> saveInterviews(Map<Integer, InterviewDTO> map) {
        try {
            List<Interview> interviews = new ArrayList<>();

            for (Map.Entry<Integer, InterviewDTO> entry : map.entrySet()) {

                User reviewer = userRepository.getOneByEmail(entry.getValue().getReviewer().getEmail())
                        .orElseThrow(() -> new EntityNotFoundException());

                Division division = Division.valueOf(reviewer.getDivision()).orElseThrow(() -> new EntityNotFoundException());

                TraineeForRequest traineeForRequest = traineeForRequestRepository.findById(entry.getKey())
                        .orElseThrow(() -> new EntityNotFoundException());

                Interview interview = InterviewUtils.interviewDTOToInterview(entry.getValue());
                interview.setReviewer(reviewer);
                interview.setTraineeForRequest(traineeForRequest);


                if (division.isHr()) {
                    interview.setTitleInterview(TitleInterview.HR_INTERVIEW.getCode());
                } else if (division.isDiv())
                    interview.setTitleInterview(TitleInterview.DIV_INTERVIEW.getCode());
                else if (division.isHd())
                    interview.setTitleInterview(TitleInterview.EDU_INTERVIEW.getCode());
                else throw new EntityNotFoundException();

                if (entry.getValue().getStartTime().after(entry.getValue().getEndTime()))
                    throw new DateTimeException("");

                traineeForRequest.getTrainee().getTraineeForRequests().stream().forEach(traineeForRequestToCheck -> {
                    Optional.ofNullable(traineeForRequestToCheck.getInterviews()).get().stream().forEach(interviewToCheck -> {
                        if (!checkDateTime(entry.getValue().getStartTime(), entry.getValue().getEndTime(), interviewToCheck.getStartTime(), interviewToCheck.getEndTime()))
                            throw new DateTimeException("");
                    });
                });

                interviews.add(interviewRepository.save(interview));
            }
            return InterviewUtils.listInterviewToListInterviewDTO(interviews);
        } catch (Exception e) {
            logger.error("Error in saveInterviews" + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public List<InterviewDTO> importFromExcel(MultipartFile multipartFile) {
        try {
            Iterator<Row> rows = ImportInterviewExcelUtils.readExcelMultipartFile(multipartFile);
            rows.next();
            List<Interview> interviews = new ArrayList<>();
            while (rows.hasNext()) {
                Interview interviewFromExcel = ImportInterviewExcelUtils.importInterviewFromRow(rows.next());
                Interview interview = interviewRepository.findById(interviewFromExcel.getId()).orElseThrow(() -> new EntityNotFoundException());
                interview.setContent(interviewFromExcel.getContent());
                interview.setResult(interviewFromExcel.getResult());
                interviews.add(interviewRepository.save(interview));
            }
            return InterviewUtils.listInterviewToListInterviewDTO(interviews);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    private Boolean checkDateTime(Date startTimeToCheck, Date endTimeToCheck, Date startTimeOrigin, Date endTimeOrigin) {
        return startTimeToCheck.after(endTimeOrigin) || startTimeOrigin.after(endTimeToCheck);
    }
}
