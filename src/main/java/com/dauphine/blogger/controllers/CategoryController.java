package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/categories")
@Tag(name = "Categories", description = "Endpoints pour gérer les catégories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Récupérer toutes les catégories", description = "Retourne la liste de toutes les catégories. Un paramètre optionnel 'name' permet de filtrer par nom.")
    public List<Category> getAll(
            @Parameter(description = "Filtrer par nom (optionnel)") @RequestParam(required = false) String name) {
        return categoryService.getAllByName(name);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une catégorie par ID", description = "Retourne la catégorie correspondant à l'ID fourni.")
    public Category getById(
            @Parameter(description = "ID de la catégorie") @PathVariable UUID id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle catégorie", description = "Crée une catégorie avec le nom fourni et retourne la catégorie créée.")
    public Category create(
            @Parameter(description = "Nom de la nouvelle catégorie") @RequestBody String name) {
        return categoryService.create(name);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier le nom d'une catégorie", description = "Met à jour le nom de la catégorie correspondant à l'ID fourni.")
    public Category update(
            @Parameter(description = "ID de la catégorie à modifier") @PathVariable UUID id,
            @Parameter(description = "Nouveau nom de la catégorie") @RequestBody String name) {
        return categoryService.update(id, name);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une catégorie", description = "Supprime la catégorie correspondant à l'ID fourni.")
    public void delete(
            @Parameter(description = "ID de la catégorie à supprimer") @PathVariable UUID id) {
        categoryService.deleteById(id);
    }
}
