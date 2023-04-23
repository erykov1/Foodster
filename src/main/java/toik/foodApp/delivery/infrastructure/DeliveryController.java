package toik.foodApp.delivery.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toik.foodApp.delivery.domain.DeliveryFacade;
import toik.foodApp.delivery.dto.DeliveryDto;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
class DeliveryController {
  DeliveryFacade deliveryFacade;

  @Autowired
  DeliveryController(DeliveryFacade deliveryFacade) {
    this.deliveryFacade = deliveryFacade;
  }

  @PostMapping("/deliveryids")
  public ResponseEntity<List<DeliveryDto>> responseEntity(@RequestBody List<Long> deliveryIds) {
    return ResponseEntity.ok(deliveryFacade.getAllDeliveryById(deliveryIds));
  }

  @GetMapping("/store")
  public ResponseEntity<List<DeliveryDto>> responseEntity() {
    return ResponseEntity.ok(deliveryFacade.getAllDelivery());
  }

  @PostMapping("/newDel")
  public ResponseEntity<DeliveryDto> responseEntity(@RequestBody DeliveryDto deliveryDto) {
    return ResponseEntity.ok(deliveryFacade.addNewDelivery(deliveryDto));
  }

  @GetMapping("/new/{deliveryId}")
  public ResponseEntity<?> responseEntity(@PathVariable long deliveryId) {
    deliveryFacade.changeDeliveryStatus(deliveryId);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping("/get/{userId}")
  public ResponseEntity<List<DeliveryDto>> test(@PathVariable long userId) {
    return ResponseEntity.ok(deliveryFacade.findDeliveryByUserId(userId));
  }
}
