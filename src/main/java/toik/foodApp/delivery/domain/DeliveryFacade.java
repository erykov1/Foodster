package toik.foodApp.delivery.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.hibernate.usertype.UserType;
import toik.foodApp.delivery.dto.AddDeliveryDto;
import toik.foodApp.delivery.dto.DeliveryDto;
import toik.foodApp.delivery.dto.DeliveryNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DeliveryFacade {

  DeliveryRepository deliveryRepository;

  public DeliveryDto addNewDelivery(DeliveryDto delivery) {

    Delivery add = Delivery.builder()
        .deliveryId(delivery.getDeliveryId())
        .totalCost(delivery.getTotalCost())
        .products(delivery.getProducts())
        .userId(delivery.getUserId())
        .deliveryType("ACCEPTED")
        .build();

    return deliveryRepository.save(add).dto();
  }

  public List<DeliveryDto> getAllDeliveryById(Collection<Long> deliveryIds) {

    return deliveryRepository.findAllByDeliveryIdIn(deliveryIds)
        .stream()
        .map(Delivery::dto)
        .collect(Collectors.toList());
  }

  public List<DeliveryDto> getAllDelivery() {
    return deliveryRepository.findAll()
        .stream()
        .map(Delivery::dto)
        .collect(Collectors.toList());
  }

  public void changeDeliveryStatus(Long deliveryId) {

    if(deliveryRepository.findByDeliveryId(deliveryId).isPresent()) {
      deliveryRepository.findById(deliveryId).get().setDeliveryType("READY");
    } else {
      throw new DeliveryNotFoundException("Delivery not found");
    }
  }

  public List<DeliveryDto> findDeliveryByUserId(long userId) {
    return deliveryRepository.findDeliveryByUserId(userId)
        .stream()
        .map(Delivery::dto)
        .collect(Collectors.toList());
  }

  public Optional<DeliveryDto> findByDeliveryId(Long deliveryId) {

    return deliveryRepository.findByDeliveryId(deliveryId)
        .stream()
        .map(Delivery::dto).findFirst();
  }
}
