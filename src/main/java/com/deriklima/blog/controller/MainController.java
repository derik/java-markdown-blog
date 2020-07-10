package com.deriklima.blog.controller;

import static com.deriklima.blog.controller.ResourcePath.ABOUT_PAGE;
import static com.deriklima.blog.controller.ResourcePath.BLOG_POST_PAGE;
import static com.deriklima.blog.controller.ResourcePath.CONTACT_PAGE;
import static com.deriklima.blog.controller.ResourcePath.INDEX_PAGE;
import static com.deriklima.blog.controller.ResourcePath.PRIVACY_PAGE;

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
    return INDEX_PAGE;
  }

  @GetMapping("/blog/{handle}")
  public String viewBlogPost(@PathVariable("handle") String handle, Model model) {
    EditBlogPostDTO blogPost = blogPostService.findByHandlePublished(handle);
    model.addAttribute("post", blogPost);
    String htmlContent = MarkdownParser.parseToHtml(blogPost.getMarkdownContent());
    model.addAttribute("postHtml", htmlContent);
    return BLOG_POST_PAGE;
  }

  @GetMapping("/about")
  public String viewAboutPage() {
    return ABOUT_PAGE;
  }

  @GetMapping("/contact")
  public String viewContactPage() {
    return CONTACT_PAGE;
  }

  @GetMapping("/privacy")
  public String viewPrivacyPage() {
    return PRIVACY_PAGE;
  }

}
