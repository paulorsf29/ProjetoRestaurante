package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.entity.Category;
import com.projetoRestaurante.Restaurante.entity.MenuItem;
import com.projetoRestaurante.Restaurante.entity.MenuItemFotos;
import com.projetoRestaurante.Restaurante.repository.CategoryRepository;
import com.projetoRestaurante.Restaurante.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;

    public MenuItem createMenuItem(MenuItem item, List<String> fotosUrl){
        if (!categoryRepository.existsById(item.getCategory().getId())){
            throw new RuntimeException("categoria não existe");
        }
        if (fotosUrl.size()>5){
            throw new RuntimeException("maximo 5 fotos");
        }
        if (item.getPreco()<0){
            throw new RuntimeException("preço deve ser mair que 0");
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
    public MenuItem UpdateMenuItem(UUID id, MenuItem item){
        MenuItem itemExistente = menuItemRepository.findById(id).orElseThrow(() -> new RuntimeException("item não encontrado"));
        itemExistente.setNome(item.getNome());
        itemExistente.setDescricao(item.getDescricao());
        itemExistente.setPreco(item.getPreco());
        itemExistente.setTempoPreparo(item.getTempoPreparo());
        itemExistente.setDisponibilidade(item.isDisponibilidade());
        itemExistente.setCategory(item.getCategory());

        return  menuItemRepository.save(itemExistente);
    }

    public void DeletarItem(UUID id){
        MenuItem item = menuItemRepository.findById(id).orElseThrow(() -> new RuntimeException("item não encontrado"));

        item.setDisponibilidade(false);
        menuItemRepository.save(item);

    }

    public List<MenuItem> findByCategory(UUID categoryId) {
        return menuItemRepository.findByCategoryIdAndDisponibilidadeTrue(categoryId);
    }

    public Category creaateCategory(Category category){
        return categoryRepository.save(category);
    }

    public Optional<MenuItem> buscarPorId(UUID id){return menuItemRepository.findById(id);}

    public List<MenuItem> listaDisponivelMenuItem(){
        return menuItemRepository.findByDisponibilidadeTrue();
    }


}
