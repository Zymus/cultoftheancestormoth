resource "azuredevops_project" "cult_of_the_ancestor_moth" {
  description = "Managed by Terraform"
  features = {
    artifacts    = "disabled"
    boards       = "enabled"
    pipelines    = "enabled"
    repositories = "enabled"
    testplans    = "disabled"
  }
  name               = "Cult of the Ancestor Moth"
  visibility         = "private"
  version_control    = "Git"
  work_item_template = "Agile"
}

resource "azuredevops_git_repository" "github" {
  name       = "cultoftheancestormoth"
  project_id = azuredevops_project.cult_of_the_ancestor_moth.id
  initialization {
    init_type   = "Import"
    source_type = "Git"
    source_url  = "https://github.com/Zymus/cultoftheancestormoth.git"
  }
}

resource "azuredevops_build_definition" "terraform" {
  name       = "terraform"
  project_id = azuredevops_project.cult_of_the_ancestor_moth.id

  repository {
    repo_id   = "Zymus/cultoftheancestormoth"
    repo_type = "GitHub"
    yml_path  = "terraform/azure-devops-project/azure-pipelines.yaml"
  }
}