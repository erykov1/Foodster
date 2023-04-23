package toik.foodApp.food.infrastructure;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import toik.foodApp.food.domain.FoodFacade;
import toik.foodApp.food.domain.FoodFileImageFacade;
import toik.foodApp.food.dto.FoodDto;
import toik.foodApp.food.dto.FoodFileImageDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/food")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class FoodController {

  FoodFacade foodFacade;

  FoodFileImageFacade foodFileImageFacade;

  static Logger LOGGER = LoggerFactory.getLogger(FoodFacade.class);

  @Autowired
  FoodController(FoodFacade foodFacade, FoodFileImageFacade foodFileImageFacade) {
    this.foodFacade = foodFacade;
    this.foodFileImageFacade = foodFileImageFacade;
  }

  @PostMapping("/foodids")
  public ResponseEntity<List<FoodDto>> responseEntity(@RequestBody List<Long> foodIds) {
    return ResponseEntity.ok(foodFacade.getFoodById(foodIds));
  }

  @GetMapping("/food/store")
  public ResponseEntity<List<FoodDto>> responseEntity() {
    return ResponseEntity.ok(foodFacade.findAllFood());
  }

  @PostMapping("/addfood")
  public ResponseEntity<FoodDto> responseEntity(@RequestBody FoodDto addFood) {
    LOGGER.info("Food has been uploaded");
    return ResponseEntity.ok(foodFacade.addNewFood(addFood));
  }

  @PostMapping("/addfoodimg")
  public ResponseEntity<FoodFileImageDto> responseEntity(@RequestParam("image") MultipartFile file) {
    try {
      return ResponseEntity.ok(foodFileImageFacade.addFile(file));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/foodimg/{foodImgId}")
  public ResponseEntity<Optional<FoodFileImageDto>> getFoodImg(@PathVariable Long foodImgId) {
    return ResponseEntity.ok(foodFileImageFacade.getFoodImgById(foodImgId));
  }
}
