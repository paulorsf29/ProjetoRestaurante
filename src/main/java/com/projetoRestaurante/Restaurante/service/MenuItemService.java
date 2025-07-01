package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.entity.Category;
import com.projetoRestaurante.Restaurante.entity.MenuItem;
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

    public Category creaateCategory(Category category){
        return categoryRepository.save(category);
    }
    List<MenuItem> listaDisponivelMenuItem(){
        return menuItemRepository.findByAvaibleTrue();
    }
}
