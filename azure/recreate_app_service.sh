appServicePlan=dictionary-service-plan
resourceGroup=$1
subscriptionId=$2
webAppName=dictionary-rest-api
imageName=$3
if [ "$(az webapp list)" != "[]" ]; then
  echo "delete the Web App"
  az webapp delete --name $webAppName --resource-group $resourceGroup
fi
echo "Create App Service Plan"
az appservice plan create \
  --name $appServicePlan \
  --resource-group $resourceGroup --subscription $subscriptionId \
  --sku F1 \
  --is-linux

echo "Create Web Application"
az webapp create \
  --name $webAppName \
  --plan $appServicePlan \
  --resource-group $resourceGroup \
  --deployment-container-image-name $imageName

get_value() { 
  az keyvault secret show --vault-name dictionary-rest-api --name "${1}" --query value --output tsv
}

echo "Add Web Application Settings"
az webapp config appsettings set \
  --resource-group $resourceGroup \
  --name $webAppName \
  --settings QUARKUS_DATASOURCE_USERNAME=$(get_value databaseUser) \
             QUARKUS_DATASOURCE_PASSWORD=$(get_value databaseAdminRuiPassword) \
             QUARKUS_DATASOURCE_JDBC_URL=$(get_value jdbcUrl)
