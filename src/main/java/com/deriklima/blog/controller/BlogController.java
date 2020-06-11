package com.deriklima.blog.controller;

import com.deriklima.blog.dto.EditBlogPostDTO;
import com.deriklima.blog.dto.TagDTO;
import com.deriklima.blog.model.PostStatus;
import com.deriklima.blog.service.BlogPostService;
import com.deriklima.blog.service.TagService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/blog")
@RequiredArgsConstructor
public class BlogController {

  private final BlogPostService blogPostService;
  private final TagService tagService;

  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");
  }

  @GetMapping("/list")
  public String viewBlogPosts(Model model) {
    model.addAttribute("posts", blogPostService.findAll());
    return "admin/blog/list-posts";
  }

  @GetMapping("/new")
  public String addBlogPost(@ModelAttribute("post") EditBlogPostDTO post) {
    return "admin/blog/edit-post";
  }

  @PostMapping("/new")
  public String saveNewBlogPost(
      @Valid @ModelAttribute("post") EditBlogPostDTO post,
      BindingResult result,
      RedirectAttributes attr) {
    if (result.hasErrors()) {
      return "admin/blog/edit-post";
    }
    EditBlogPostDTO savedBlogPost = blogPostService.save(post);

    attr.addAttribute("id", savedBlogPost.getId())
        .addFlashAttribute("success", true);
    return "redirect:/admin/blog/{id}/edit";
  }

  @GetMapping("/{blogPostId}/edit")
  public String editBlogPost(@PathVariable("blogPostId") Long blogPostId, Model model) {
    EditBlogPostDTO post = blogPostService.findById(blogPostId);
    model.addAttribute("post", post);
    return "admin/blog/edit-post";
  }

  @PostMapping("/{blogPostId}/edit")
  public String saveBlogPost(
      @PathVariable("blogPostId") Long blogPostId,
      @Valid EditBlogPostDTO editBlogPostDTO,
      BindingResult result,
      RedirectAttributes attr) {

    if (result.hasErrors()) {
      return "admin/blog/edit-post";
    }

    editBlogPostDTO.setId(blogPostId);
    EditBlogPostDTO savedBlogPost = blogPostService.save(editBlogPostDTO);

    attr.addAttribute("id", savedBlogPost.getId())
        .addFlashAttribute("success", true);
    return "redirect:/admin/blog/{id}/edit";
  }

  @ModelAttribute("tags")
  public List<TagDTO> tagsList() {
    return tagService.findAll();
  }

  @ModelAttribute("statuses")
  public List<String> statusesList() {
    return Arrays.asList(PostStatus.values()).stream().map(PostStatus::name)
        .collect(Collectors.toList());
  }
}

