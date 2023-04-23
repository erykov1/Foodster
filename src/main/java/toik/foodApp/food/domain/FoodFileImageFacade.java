package toik.foodApp.food.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import toik.foodApp.food.dto.FoodFileImageDto;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
public class FoodFileImageFacade {

    FoodFileImageRepository foodFileImageRepository;

    public FoodFileImageDto addFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        FoodFileImage fileImage = FoodFileImage
                .builder()
                .name(fileName)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();

        return foodFileImageRepository.save(fileImage).dto();
    }

    public List<FoodFileImageDto> getFoodImgByIdIn(Collection<Long> foodImgIds) {
        return foodFileImageRepository.findAllByIdIn(foodImgIds)
                .stream()
                .map(FoodFileImage::dto)
                .collect(Collectors.toList());
    }

    public Optional<FoodFileImageDto> getFoodImgById(Long foodImgId) {

        return foodFileImageRepository.findFoodFileImageById(foodImgId)
            .map(FoodFileImage::dto);
    }
}
