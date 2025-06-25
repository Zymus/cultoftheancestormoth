terraform {
  backend "azurerm" {
    resource_group_name  = "cultoftheancestormoth"
    storage_account_name = "cultoftheancestormoth"
    container_name       = "azurerm_backend"
    key                  = "tfstate"
  }
}
