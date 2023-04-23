package toik.foodApp.food.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import toik.foodApp.food.dto.FoodDto;
import toik.foodApp.user.domain.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

class InMemoryFoodRepository implements FoodRepository {

  private final Map<Long, Food> values = new ConcurrentHashMap<>();

  @Override
  public Optional<Food> findByFoodName(String foodName) {

    return values.values()
        .stream()
        .filter(food -> food.getFoodName().equals(foodName))
        .findFirst();
  }

  @Override
  public Collection<Food> findAllByFoodIdIn(Collection<Long> foodIds) {

    return values.values()
        .stream()
        .filter(food -> foodIds.stream().anyMatch(id -> food.getFoodId().equals(id)))
        .collect(Collectors.toList());
  }

  @Override
  public List<Food> findAll() {

    return values.values()
        .stream()
        .toList();
  }

  @Override
  public List<Food> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<Food> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public List<Food> findAllById(Iterable<Long> longs) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(Long aLong) {

  }

  @Override
  public void delete(Food entity) {

  }

  @Override
  public void deleteAllById(Iterable<? extends Long> longs) {

  }

  @Override
  public void deleteAll(Iterable<? extends Food> entities) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public Food save(Food food) {
    if(food.getFoodId() == null || food.getFoodId() == 0L) {
      long foodId = new Random().nextLong();
      food = food.toBuilder()
          .foodId(foodId)
          .build();
    }

    values.put(food.getFoodId(), food);
    return food;
  }

  @Override
  public <S extends Food> List<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<Food> findById(Long foodId) {

    return values.values()
        .stream()
        .filter(food -> food.getFoodId().equals(foodId))
        .findFirst();
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public void flush() {

  }

  @Override
  public <S extends Food> S saveAndFlush(S entity) {
    return null;
  }

  @Override
  public <S extends Food> List<S> saveAllAndFlush(Iterable<S> entities) {
    return null;
  }

  @Override
  public void deleteAllInBatch(Iterable<Food> entities) {

  }

  @Override
  public void deleteAllByIdInBatch(Iterable<Long> longs) {

  }

  @Override
  public void deleteAllInBatch() {

  }

  @Override
  public Food getOne(Long aLong) {
    return null;
  }

  @Override
  public Food getById(Long aLong) {
    return null;
  }

  @Override
  public Food getReferenceById(Long aLong) {
    return null;
  }

  @Override
  public <S extends Food> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends Food> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends Food> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends Food> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Food> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends Food> boolean exists(Example<S> example) {
    return false;
  }

  @Override
  public <S extends Food, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
    return null;
  }
}
