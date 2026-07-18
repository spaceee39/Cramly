package com.example.demo.Service;

import com.example.demo.DTO.Request.CompleteLearningDtoRequest;
import com.example.demo.Entity.ServicePack.Card;
import com.example.demo.Entity.ServicePack.UserProgress;
import com.example.demo.Entity.ServicePack.Module;
import com.example.demo.Repository.CardRepository;
import com.example.demo.Repository.ModuleRepository;
import com.example.demo.Repository.UserProgressRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LearningService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    UserProgressRepository userProgressRepository;


    public Module moduleForLearning(String username, Long moduleId){

        Optional<Module> moduleOptional = moduleRepository.findById(moduleId);
        if(moduleOptional.isPresent()) {
            Module module = moduleOptional.get();
            List<UserProgress> userProgressList = userProgressRepository.findByUserNameAndModule(username, moduleId);
            List<Long> cardsId = new ArrayList<>(); // из прогресса
            List<Card> cardsForLearning = new ArrayList<>(); // уйдет на фронтенд

            if (!userProgressList.isEmpty()) {
                // cобираем все id карточек из базы прогресса
                for (UserProgress userProgress : userProgressList) {
                    cardsId.add(userProgress.getCard());
                }
                // запускаем для каждого нужного id
                for (Long cardId : cardsId) {
                    // проверяем каждую карточку на совпадение с прогрессом
                    for (Card card : module.getCardList()) {

                        if (cardId.equals(card.getId())) {
                            cardsForLearning.add(card);
                        }
                    }
                }
                module.setCardList(cardsForLearning);
            }

            return module;
        }else {
            return null;
        }
    }


    public void UserProgressEdit (String username, CompleteLearningDtoRequest completeLearningDtoRequest){
        userProgressRepository.deleteByUserNameAndModule(username, completeLearningDtoRequest.getModuleId());
        userProgressRepository.saveAll(completeLearningDtoRequest.toEntities(username));
    }



}
