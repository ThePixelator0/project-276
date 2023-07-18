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
    document.getElementById("page-info-container").style.display = "none"
    document.getElementById("vertical-navigation").style.display = "none"
}

function closeNutritionData(modalId){
    document.getElementById(modalId).style.display = "none"
    document.getElementById("page-info-container").style.display = "block"
    document.getElementById("vertical-navigation").style.display = "block"
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


//ajax request to get info
function getNutritionInfo(mealType, userId, modalId, modalData){
    var modal = document.getElementById(modalId)
    const apiUrl = `/api/nutrition/allNutrition/${mealType}/${userId}`
    $.ajax({
        url: apiUrl,
        type: 'GET',
        success: function(data){
            var nutrientData = document.getElementById(modalData)
            nutrientData.innerHTML = ""
            for(var i=0; i<data.length; i++){
                nutrientData.innerHTML += `<p>${data[i].id}</p>`
                nutrientData.innerHTML += `<p>${data[i].creationDateTime}</p>`
                nutrientData.innerHTML += `<p>${data[i].mealType}</p>`
                nutrientData.innerHTML += `<p>${data[i].product}</p>`
                nutrientData.innerHTML += `<p>${data[i].protein}</p>`
                nutrientData.innerHTML += `<p>${data[i].calorie}</p>`
                nutrientData.innerHTML += `<p>${data[i].fat}</p>`
            }
        },
        error: function(error){
            console.log("Error: ",error)
        }
    })
    document.getElementById("page-info-container").style.display = "none"
    document.getElementById("vertical-navigation").style.display = "none"
    modal.style.display = "block"
}