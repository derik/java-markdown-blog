package com.deriklima.blog.controller;

import com.deriklima.blog.dto.EditBlogPostDTO;
import com.deriklima.blog.service.BlogPostService;
import com.deriklima.blog.util.MarkdownParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MainController {

  private final BlogPostService blogPostService;

  @GetMapping("/")
  public String viewBlogIndex(Model model) {
    model.addAttribute("posts", blogPostService.findAllPublished());
    return "index";
  }

  @GetMapping("/blog/{handle}")
  public String viewBlogPost(@PathVariable("handle") String handle, Model model) {
    EditBlogPostDTO blogPost = blogPostService.findByHandlePublished(handle);
    model.addAttribute("post", blogPost);
    String htmlContent = MarkdownParser.parseToHtml(blogPost.getMarkdownContent());
    model.addAttribute("postHtml", htmlContent);
    return "blog/post";
  }

  @GetMapping("/about")
  public String viewAboutPage() {
    return "about";
  }

  @GetMapping("/contact")
  public String viewContactPage() {
    return "contact";
  }

  @GetMapping("/privacy")
  public String viewPrivacyPage() {
    return "privacy";
  }

}
