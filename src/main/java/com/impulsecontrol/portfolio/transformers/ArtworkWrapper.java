package com.impulsecontrol.portfolio.transformers;

import com.impulsecontrol.portfolio.core.Artwork;

import java.util.List;
import java.util.stream.Collectors;

public class ArtworkWrapper {
    public Long id;

    public String category;

    public Long categoryId;

    public String description;

    public String path;

    public ArtworkWrapper(Artwork artwork) {
        this.id = artwork.getId();
        this.category = artwork.getCategory().getName();
        this.categoryId = artwork.getCategory().getId();
        this.description = artwork.getDescription();
        this.path = artwork.getPath();
    }

    public static ArtworkWrapper transform(Artwork artwork) {
        return new ArtworkWrapper(artwork);
    }

    public static List<ArtworkWrapper> transform(List<Artwork> artwork) {
        return artwork.stream()
                .map(ArtworkWrapper::transform).collect(Collectors.toList());
    }


}
