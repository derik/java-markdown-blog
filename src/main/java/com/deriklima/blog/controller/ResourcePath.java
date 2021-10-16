package com.deriklima.blog.controller;

public class ResourcePath {

  private ResourcePath() {
  }

  // Paths
  public static final String ADMIN = "admin";
  public static final String ADMIN_BLOG_PATH = ADMIN + "/blog";
  public static final String ADMIN_TAG_PATH = ADMIN + "/tag";

  // Pages
  public static final String ABOUT_PAGE = "about";
  public static final String CONTACT_PAGE = "contact";
  public static final String PRIVACY_PAGE = "privacy";
  public static final String BLOG_POST_PAGE = "blog/post";
  public static final String INDEX_PAGE = "index";

  public static final String ADMIN_BLOG_LIST_POSTS_PAGE = ADMIN + "/blog/list-posts";
  public static final String ADMIN_BLOG_EDIT_POST_PAGE = ADMIN + "/blog/edit-post";

  public static final String ADMIN_TAG_LIST_PAGE = ADMIN + "/tag/list-tag";
}
