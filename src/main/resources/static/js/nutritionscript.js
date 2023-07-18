const apiParams = {
    apiKey: 'e1Qeo8EhfrLVg7E8hvBXp4eOYhnaOudpgeXLkGml',
    dataType: ["Survey (FNDDS)", "Branded"],
    pageSize: 3
}

//date id
const currDate = Date.now()
const dt = new Date(currDate)
document.getElementById("nutrition-date").innerHTML = dt.toDateString()

/*get and add data*/
function openNutritionData(modalId){
    document.getElementById(modalId).style.display = "block"
}

function closeNutritionData(modalId){
    document.getElementById(modalId).style.display = "none"
}



//api request
function searchFoods(foodInput, dataListId, foodCodeId){
    const apiUrl = `https://api.nal.usda.gov/fdc/v1/foods/search?api_key=${apiParams.apiKey}&query=${foodInput}&dataType=${apiParams.dataType}`
    
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            const datalist = document.getElementById(dataListId);
            datalist.innerHTML = '';
            var foodCode = document.getElementById(foodCodeId)
            
            var foods = data.foods
            foods.forEach(element => {
                foodCode.value = element.fdcId
                const option = document.createElement('option')
                option.value = element.description
                datalist.appendChild(option)
            });
        })
}