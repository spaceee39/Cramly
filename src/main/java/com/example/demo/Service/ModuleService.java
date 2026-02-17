package com.example.demo.Service;

import com.example.demo.Model.Card;
import com.example.demo.Model.User;
import com.example.demo.Model.Module;
import com.example.demo.Repository.CardRepository;
import com.example.demo.Repository.ModuleRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

    public void createModule(Module module, Map<String , String> allParams, UserDetails userDetails){
        List<Card> cards = new ArrayList<>();
        int i = 0;

        while (allParams.containsKey("cards[" + i + "].theme")) {
            Card card = new Card();
            card.setTheme(allParams.get("cards[" + i + "].theme"));
            card.setText(allParams.get("cards[" + i + "].text"));

            // Сохраняем карточку в БД
            Card savedCard = cardRepository.save(card);
            cards.add(savedCard);
            i++;
        }

        module.setCardList(cards);
        module.setAuthor(userDetails.getUsername());
        module.setCardCount(cards.size());
        moduleRepository.save(module);
        User user = userRepository.findByUsername(userDetails.getUsername());
        user.addOneModule(module);
        userRepository.save(user);
    }



}
