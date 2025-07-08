package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.entity.Category;
import com.projetoRestaurante.Restaurante.entity.MenuItem;
import com.projetoRestaurante.Restaurante.entity.MenuItemFotos;
import com.projetoRestaurante.Restaurante.repository.CategoryRepository;
import com.projetoRestaurante.Restaurante.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;

    public MenuItem createMenuItem(MenuItem item, List<String> fotosUrl){
        if (fotosUrl.size()>5){
            throw new RuntimeException("maximo 5 fotos");
        }
        MenuItem savedItem = menuItemRepository.save(item);

        fotosUrl.forEach(url -> {
            savedItem.getFotos().add(new MenuItemFotos(url,false,savedItem)
            );
        });
        return menuItemRepository.save(savedItem);
    }
    public MenuItem createMenuItemWithMockPhotos(MenuItem item) {
        List<String> mockUrls = List.of(
                "https://example.com/mock-photo1.jpg",
                "https://example.com/mock-photo2.jpg"
        );
        return createMenuItem(item, mockUrls);
    }

    public Category creaateCategory(Category category){
        return categoryRepository.save(category);
    }
    List<MenuItem> listaDisponivelMenuItem(){
        return menuItemRepository.findByDisponibilidadeTrue();
    }


}
