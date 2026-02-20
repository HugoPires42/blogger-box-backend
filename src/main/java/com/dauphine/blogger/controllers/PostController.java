package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/posts")
@Tag(name = "Posts", description = "Endpoints pour gérer les posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les posts", description = "Retourne la liste de tous les posts triés par date de création (du plus récent au plus ancien).")
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Récupérer les posts par catégorie", description = "Retourne tous les posts associés à la catégorie donnée.")
    public List<Post> getAllByCategoryId(
            @Parameter(description = "ID de la catégorie") @PathVariable UUID categoryId) {
        return postService.getAllByCategoryId(categoryId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un post par ID", description = "Retourne le post correspondant à l'ID fourni.")
    public Post getById(
            @Parameter(description = "ID du post") @PathVariable UUID id) {
        return postService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau post", description = "Crée un post avec le titre, contenu et catégorie fournis.")
    public Post create(
            @Parameter(description = "Titre du post") @RequestParam String title,
            @Parameter(description = "Contenu du post") @RequestParam String content,
            @Parameter(description = "ID de la catégorie") @RequestParam UUID categoryId) {
        return postService.create(title, content, categoryId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un post", description = "Met à jour le titre et le contenu du post correspondant à l'ID fourni.")
    public Post update(
            @Parameter(description = "ID du post à modifier") @PathVariable UUID id,
            @Parameter(description = "Nouveau titre") @RequestParam String title,
            @Parameter(description = "Nouveau contenu") @RequestParam String content) {
        return postService.update(id, title, content);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un post", description = "Supprime le post correspondant à l'ID fourni.")
    public void delete(
            @Parameter(description = "ID du post à supprimer") @PathVariable UUID id) {
        postService.deleteById(id);
    }
}
