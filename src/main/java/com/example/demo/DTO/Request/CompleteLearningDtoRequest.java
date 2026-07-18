package com.example.demo.DTO.Request;

import com.example.demo.Entity.Enums.LearningStatus;
import com.example.demo.Entity.ServicePack.UserProgress;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class CompleteLearningDtoRequest {
    private Long moduleId;
    //long - cardId, LearningStatus - status: known/unknown
    private Map<Long, LearningStatus> result = new HashMap<>();


    public List<UserProgress> toEntities(String username){
        if(!result.isEmpty()){
            List<UserProgress> progresses = new ArrayList<>();
            for(Long cardId : result.keySet()){
                if(result.get(cardId) == LearningStatus.UNKNOWN) {
                    UserProgress userProgress = new UserProgress();
                    userProgress.setUserName(username);
                    userProgress.setModule(moduleId);
                    userProgress.setCard(cardId);
                    userProgress.setStatus(result.get(cardId));
                    progresses.add(userProgress);
                }
            }
            return progresses;
        } else
            return null;
    }
}
