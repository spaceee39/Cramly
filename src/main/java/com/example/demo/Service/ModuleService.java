package com.example.demo.Service;

import com.example.demo.DTO.CardDto;
import com.example.demo.DTO.ModuleDto;
import com.example.demo.Entity.ServicePack.Card;
import com.example.demo.Entity.UserPack.User;
import com.example.demo.Entity.ServicePack.Module;
import com.example.demo.Repository.CardRepository;
import com.example.demo.Repository.ModuleRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Transactional
    public void editModuleServiceTransaction(ModuleDto moduleDto) throws EntityNotFoundException{
            Module editedModule = moduleMapper(moduleDto);
            Module moduleDb = moduleRepository.findById(editedModule.getId()).orElseThrow(EntityNotFoundException::new);
            moduleDb.editModuleinfo(editedModule);
            moduleRepository.save(moduleDb);
    }

    //creation new module
    public void createModule(Module module, Map<String , String> allParams, UserDetails userDetails){
        module.setAuthor(userDetails.getUsername());
        List<Card> cards = new ArrayList<>();
        int i = 0;

        while (allParams.containsKey("cards[" + i + "].theme")) {
            Card card = new Card();
            card.setTheme(allParams.get("cards[" + i + "].theme"));
            card.setText(allParams.get("cards[" + i + "].text"));
            card.setOwner(module.getAuthor());

            // Сохраняем карточку в БД
            Card savedCard = cardRepository.save(card);
            cards.add(savedCard);
            i++;
        }

        module.setCardList(cards);

        module.setCardCount(cards.size());
        moduleRepository.save(module);
        User user = userRepository.findByUsername(userDetails.getUsername());
        user.addOneModule(module);
        userRepository.save(user);
    }

    // mapping cards and modules from frontend (edit)
        //in mapper second
    public static Card cardMapper(CardDto cardDto){
        Card card = new Card();
        card.setId(cardDto.getId());
        card.setTheme(cardDto.getTheme());
        card.setText(cardDto.getText());
        card.setCreatedAt(cardDto.getCreatedAt());
        card.setCardStatus(cardDto.getCardStatus());
        return card;
    }
        //in mapper third
    public List<Card> cardListMapper (ModuleDto moduleDto) {
        List<Card> cardList = new ArrayList<>();
        for (CardDto cardDto : moduleDto.getCards()){

            cardList.add(cardMapper(cardDto));
        }
        return cardList;
    }

    // first
    public Module moduleMapper (ModuleDto moduleDto){
        Module module = new Module();
        module.setId(moduleDto.getId());
        module.setName(moduleDto.getName());
        module.setDescription(moduleDto.getDescription());
        module.setStatus(moduleDto.getStatus());
        module.setCardList(cardListMapper(moduleDto));
        return module;
    }




}
