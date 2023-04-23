package toik.foodApp.delivery.domain;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import toik.foodApp.food.domain.Food;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;


class InMemoryDeliveryRepository implements DeliveryRepository {
  private final Map<Long, Delivery> values = new ConcurrentHashMap<>();
  @Override
  public Collection<Delivery> findAllByDeliveryIdIn(Collection<Long> deliveryIds) {

    return values.values()
        .stream()
        .filter(delivery -> deliveryIds.stream().anyMatch(id -> delivery.getDeliveryId().equals(id)))
        .collect(Collectors.toList());
  }

  @Override
  public List<Delivery> findDeliveryByUserId(long clientId) {

    return values.values()
        .stream()
        .filter(delivery -> delivery.getUserId() == clientId)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Delivery> findByDeliveryId(Long deliveryId) {

    return values.values()
        .stream()
        .filter(delivery -> deliveryId.equals(delivery.getDeliveryId()))
        .findFirst();
  }

  @Override
  public Delivery findOneOrThrow(Long deliveryId) {
    return DeliveryRepository.super.findOneOrThrow(deliveryId);
  }

  @Override
  public List<Delivery> findAll() {

    return new ArrayList<>(values.values());
  }

  @Override
  public List<Delivery> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<Delivery> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public List<Delivery> findAllById(Iterable<Long> longs) {
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
  public void delete(Delivery entity) {

  }

  @Override
  public void deleteAllById(Iterable<? extends Long> longs) {

  }

  @Override
  public void deleteAll(Iterable<? extends Delivery> entities) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public Delivery save(Delivery delivery) {
    if (delivery.getDeliveryId() == null || delivery.getDeliveryId() == 0L) {
      long deliveryId = new Random().nextLong();
      delivery = delivery.toBuilder().deliveryId(deliveryId).build();
    } else {
      values.put(delivery.getDeliveryId(), delivery);
    }
    return delivery;
  }


  @Override
  public <S extends Delivery> List<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<Delivery> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public void flush() {

  }

  @Override
  public <S extends Delivery> S saveAndFlush(S entity) {
    return null;
  }

  @Override
  public <S extends Delivery> List<S> saveAllAndFlush(Iterable<S> entities) {
    return null;
  }

  @Override
  public void deleteAllInBatch(Iterable<Delivery> entities) {

  }

  @Override
  public void deleteAllByIdInBatch(Iterable<Long> longs) {

  }

  @Override
  public void deleteAllInBatch() {

  }

  @Override
  public Delivery getOne(Long aLong) {
    return null;
  }

  @Override
  public Delivery getById(Long aLong) {
    return null;
  }

  @Override
  public Delivery getReferenceById(Long aLong) {
    return null;
  }

  @Override
  public <S extends Delivery> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends Delivery> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends Delivery> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends Delivery> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Delivery> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends Delivery> boolean exists(Example<S> example) {
    return false;
  }

  @Override
  public <S extends Delivery, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
    return null;
  }
}
