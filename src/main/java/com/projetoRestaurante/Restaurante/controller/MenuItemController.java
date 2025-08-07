package com.projetoRestaurante.Restaurante.controller;

import com.projetoRestaurante.Restaurante.entity.MenuItem;
import com.projetoRestaurante.Restaurante.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllDisponiveis(){
        return ResponseEntity.ok(menuItemService.listaDisponivelMenuItem());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MenuItem>> getById(@PathVariable UUID id){
        return ResponseEntity.ok(menuItemService.buscarPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuItem> criar(
            @RequestBody MenuItem item,
            @RequestParam List<String> fotosUrl){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuItemService.createMenuItem(item, fotosUrl));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuItem> atualizar(@PathVariable UUID id, @RequestBody MenuItem item) {
        return ResponseEntity.ok(menuItemService.UpdateMenuItem(id, item));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        menuItemService.DeletarItem(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/categoria/{categoryId}")
    public ResponseEntity<List<MenuItem>> buscarPorCategoria(@PathVariable UUID categoryId){
        return ResponseEntity.ok(menuItemService.findByCategory(categoryId));
    }
}
