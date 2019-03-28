package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.*;
import framgia.co.edu.ftrr.dto.request.InterviewDTO;
import framgia.co.edu.ftrr.entity.*;
import framgia.co.edu.ftrr.repository.*;
import framgia.co.edu.ftrr.service.InterviewService;
import framgia.co.edu.ftrr.util.ImportInterviewExcelUtils;
import framgia.co.edu.ftrr.util.InterviewUtils;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
    @Autowired
    NotificationRepository notificationRepository;

    @Value("notification to sm/dm")
    String notificationToSmDm;

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
            Integer requestId = interviews.get(0).getTraineeForRequest().getRequest().getId();
            Request request = requestRepository.findById(requestId).get();
            if (isCompletedResultsInterviewForRequest(request)) {
                request.setStatus(RequestStatus.WAITING_FINAL_RESULT.getCode());
                requestRepository.save(request);
                sendNotificationToSMDMToConfirmRequest(request);
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

    private Boolean isCompletedResultsInterviewForRequest(Request request) {
        try {
            if (request.getStatus().equals(RequestStatus.INTERVIEW.getCode())) {
                return request.getTraineeForRequests().stream()
                        .filter(traineeForRequest -> traineeForRequest.getStatus().equals(TraineeRequestStatus.FINISH.getValue()))
                        .allMatch(traineeForRequest -> isInterviewDone(traineeForRequest.getInterviews()));
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean isInterviewDone(List<Interview> interviews) {
        return interviews.stream().allMatch(interview -> interview.getResult() != null && interview.getContent() != null);
    }

    private List<User> getListSmDmToNotify(Request request) {
        Integer[] rolesSmDm = {Roles.DM.getCode(), Roles.SM.getCode()};
        return userRepository.findAllByDivisionAndRoleIn(request.getDivision(), rolesSmDm);
    }

    private void sendNotificationToSMDMToConfirmRequest(Request request) {
        List<User> listUser = getListSmDmToNotify(request);
        List<Notification> listNotification = new ArrayList<>();
        for (User user : listUser) {
            Notification notification = new Notification();
            notification.setContent(notificationToSmDm);
            notification.setStatus(NotificationStatus.UNSEEN.getCode());
            notification.setUser(user);
            listNotification.add(notification);
        }
        notificationRepository.saveAll(listNotification);
    }
}
