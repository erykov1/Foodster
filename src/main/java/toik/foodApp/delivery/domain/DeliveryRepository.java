package toik.foodApp.delivery.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import toik.foodApp.delivery.dto.DeliveryNotFoundException;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional
interface DeliveryRepository extends JpaRepository<Delivery, Long> {

  Collection<Delivery> findAllByDeliveryIdIn(Collection<Long> deliveryIds);

  @Query(value = "SELECT * FROM delivery d WHERE d.client_id = ?1",
  nativeQuery = true)
  List<Delivery> findDeliveryByUserId(long clientId);

  @Query(value = "SELECT d FROM delivery d WHERE d.deliveryId = :deliveryId")
  Optional<Delivery> findByDeliveryId(@Param("deliveryId") Long deliveryId);


  default Delivery findOneOrThrow(Long deliveryId) {
    Optional<Delivery> delivery = findById(deliveryId);
    if(delivery.isEmpty()) {
      throw new DeliveryNotFoundException("Delivery with id : " + deliveryId + " not found");
    }
    return delivery.get();
  }
}
