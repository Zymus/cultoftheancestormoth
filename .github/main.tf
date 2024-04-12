terraform {
  required_providers {
    github = {
      source  = "integrations/github"
      version = "6.2.1"
    }
  }
}

provider "github" {}

resource "github_repository" "cult_of_the_ancestor_moth" {
  name        = "cultoftheancestormoth"
  description = "Build tools for Kotlin-based Skyrim Plugins."
  visibility  = "public"

  allow_merge_commit = false

  has_discussions      = true
  has_downloads        = true
  has_issues           = true
  has_projects         = true
  has_wiki             = true
  vulnerability_alerts = true
}

resource "github_branch_default" "master" {
  branch     = "master"
  repository = github_repository.cult_of_the_ancestor_moth.name
}

resource "github_branch_protection" "master" {
  pattern                 = github_branch_default.master.branch
  repository_id           = github_repository.cult_of_the_ancestor_moth.name
  allows_deletions        = false
  allows_force_pushes     = true
  enforce_admins          = false
  require_signed_commits  = true
  required_linear_history = true

  required_status_checks {
    strict = true
  }

  required_pull_request_reviews {
    require_code_owner_reviews = true
  }
}
