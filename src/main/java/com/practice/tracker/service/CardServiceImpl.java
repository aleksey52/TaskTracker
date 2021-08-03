package com.practice.tracker.service;

import com.practice.tracker.entity.Card;
import com.practice.tracker.entity.Task;
import com.practice.tracker.exception.ResourceNotFoundException;
import com.practice.tracker.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService{
  private CardRepository cardRepository;

  public CardServiceImpl(CardRepository cardRepository){
    this.cardRepository = cardRepository;
  }

  @Override
  public List<Card> findAllByTask(Task task){
    return cardRepository.findAllByTask(task);
  }

  @Override
  public Optional<Card> findById(long id){
    return Optional.ofNullable(cardRepository.findById(id));
  }

  @Override
  public Card save(Card card) {
    card.setName(card.getName().trim());
    if(!card.getName().equals("")) {
      return cardRepository.save(card);
    }
    return card;
  }

  @Override
  public void deleteById(long id) {
    findById(id).orElseThrow(ResourceNotFoundException::new);
    cardRepository.deleteById(id);
  }
}
